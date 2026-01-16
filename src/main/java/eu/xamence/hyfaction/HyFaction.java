package eu.xamence.hyfaction;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class HyFaction extends JavaPlugin {

    private final HytaleLogger LOGGER;

    public HyFaction(@Nonnull JavaPluginInit init) {
        super(init);

        this.LOGGER = HytaleLogger.forEnclosingClass();
    }
}
