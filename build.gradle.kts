import org.gradle.internal.os.OperatingSystem
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

plugins {
    java
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.3"
}

// Récupération des propriétés (gradle.properties)
val javaVersion: String by project
val patchline: String by project
val includesPack: String by project
val loadUserMods: String by project

var hytaleHome: String = ""

// Logique de détection du dossier Hytale
if (project.hasProperty("hytale_home")) {
    hytaleHome = project.findProperty("hytale_home") as String
} else {
    val os = OperatingSystem.current()
    val userHome = System.getProperty("user.home")
    hytaleHome = when {
        os.isWindows -> "$userHome/AppData/Roaming/Hytale"
        os.isMacOsX -> "$userHome/Library/Application Support/Hytale"
        os.isLinux -> {
            val flatpakPath = "$userHome/.var/app/com.hypixel.HytaleLauncher/data/Hytale"
            if (file(flatpakPath).exists()) flatpakPath else "$userHome/.local/share/Hytale"
        }
        else -> ""
    }
}

// Validation
if (hytaleHome.isEmpty() || !file(hytaleHome).exists()) {
    throw GradleException("Failed to find Hytale at: $hytaleHome. Define it via 'hytale_home' property.")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:-missing", "-quiet")
}

dependencies {
    implementation(files("$hytaleHome/install/$patchline/package/game/latest/Server/HytaleServer.jar"))
}

val serverRunDir = file("$projectDir/run")
if (!serverRunDir.exists()) {
    serverRunDir.mkdirs()
}

// Fonction utilitaire pour les arguments
fun createServerRunArguments(srcDir: String): String {
    var params = "--allow-op --disable-sentry --assets=\"$hytaleHome/install/$patchline/package/game/latest/Assets.zip\""
    val modPaths = mutableListOf<String>()

    if (includesPack.toBoolean()) modPaths.add(srcDir)
    if (loadUserMods.toBoolean()) modPaths.add("$hytaleHome/UserData/Mods")

    if (modPaths.isNotEmpty()) {
        params += " --mods=\"${modPaths.joinToString(",")}\""
    }
    return params
}

tasks.register("updatePluginManifest") {
    val manifestFile = file("src/main/resources/manifest.json")
    doLast {
        if (!manifestFile.exists()) throw GradleException("Manifest not found!")

        val slurper = JsonSlurper()
        val manifest = slurper.parseText(manifestFile.readText()) as MutableMap<String, Any>

        manifest["Version"] = project.version
        manifest["IncludesAssetPack"] = includesPack.toBoolean()

        manifestFile.writeText(JsonOutput.prettyPrint(JsonOutput.toJson(manifest)))
    }
}

tasks.named("processResources") {
    dependsOn("updatePluginManifest")
}


tasks.register("generateVSCodeLaunch") {
    val vscodeDir = file("$projectDir/.vscode")
    val launchFile = file("$vscodeDir/launch.json")
    doLast {
        if (!vscodeDir.exists()) vscodeDir.mkdirs()

        val programParams = createServerRunArguments("\${workspaceFolder}")
        val launchConfig = mapOf(
            "version" to "0.2.0",
            "configurations" to listOf(
                mapOf(
                    "type" to "java",
                    "name" to "HytaleServer",
                    "request" to "launch",
                    "mainClass" to "com.hypixel.hytale.Main",
                    "args" to programParams,
                    "cwd" to "\${workspaceFolder}/run"
                )
            )
        )
        launchFile.writeText(JsonOutput.prettyPrint(JsonOutput.toJson(launchConfig)))
    }
}