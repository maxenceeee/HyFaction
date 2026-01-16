package eu.xamence.hyfaction;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.ResourceType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.components.faction.FactionIdentityComponent;
import eu.xamence.hyfaction.components.faction.HomeComponent;
import eu.xamence.hyfaction.components.faction.PowerComponent;
import eu.xamence.hyfaction.components.faction.VulnerableComponent;
import eu.xamence.hyfaction.resources.ClaimRegistryResource;
import eu.xamence.hyfaction.systems.OverclaimSystem;

import javax.annotation.Nonnull;

public class HyFaction extends JavaPlugin {

    private final HytaleLogger LOGGER;

    private ComponentType<EntityStore, FactionIdentityComponent> factionIdentityComponentComponentType;
    private ComponentType<EntityStore, HomeComponent> homeComponentComponentType;
    private ComponentType<EntityStore, PowerComponent> powerComponentComponentType;
    private ComponentType<EntityStore, VulnerableComponent> vulnerableComponentComponentType;

    private ResourceType<EntityStore, ClaimRegistryResource> claimRegistryResourceResourceType;

    public HyFaction(@Nonnull JavaPluginInit init) {
        super(init);

        this.LOGGER = HytaleLogger.forEnclosingClass();
    }

    @Override
    protected void setup() {
        this.loadComponents();
        this.loadResources();
        this.loadSystems();
    }



    private void loadComponents() {
        this.factionIdentityComponentComponentType = getEntityStoreRegistry().registerComponent(FactionIdentityComponent.class, "FactionIdentity", FactionIdentityComponent.CODEC);
        this.homeComponentComponentType = getEntityStoreRegistry().registerComponent(HomeComponent.class, "Home", HomeComponent.CODEC);
        this.powerComponentComponentType = getEntityStoreRegistry().registerComponent(PowerComponent.class, "Power", PowerComponent.CODEC);
        this.vulnerableComponentComponentType = getEntityStoreRegistry().registerComponent(VulnerableComponent.class, "Vulnerable", VulnerableComponent.CODEC);
    }

    private void loadResources() {
        this.claimRegistryResourceResourceType = getEntityStoreRegistry().registerResource(ClaimRegistryResource.class, ClaimRegistryResource::new);
    }

    private void loadSystems() {
        getEntityStoreRegistry().registerSystem(new OverclaimSystem(this));
    }



    public ComponentType<EntityStore, FactionIdentityComponent> getFactionIdentityComponentComponentType() {
        return factionIdentityComponentComponentType;
    }

    public ComponentType<EntityStore, HomeComponent> getHomeComponentComponentType() {
        return homeComponentComponentType;
    }

    public ComponentType<EntityStore, PowerComponent> getPowerComponentComponentType() {
        return powerComponentComponentType;
    }

    public ComponentType<EntityStore, VulnerableComponent> getVulnerableComponentComponentType() {
        return vulnerableComponentComponentType;
    }

    public ResourceType<EntityStore, ClaimRegistryResource> getClaimRegistryResourceResourceType() {
        return claimRegistryResourceResourceType;
    }
}
