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
import eu.xamence.hyfaction.components.player.MemberFactionComponent;
import eu.xamence.hyfaction.components.world.ChunkLocationComponent;
import eu.xamence.hyfaction.components.world.OwnershipComponent;
import eu.xamence.hyfaction.resources.ClaimRegistryResource;
import eu.xamence.hyfaction.resources.FactionRegistryResource;
import eu.xamence.hyfaction.systems.ClaimSyncSystem;
import eu.xamence.hyfaction.systems.OverclaimSystem;
import eu.xamence.hyfaction.systems.PowerLossSystem;
import eu.xamence.hyfaction.systems.PowerRegenSystem;

import javax.annotation.Nonnull;

public class HyFaction extends JavaPlugin {

    private final HytaleLogger LOGGER;

    private ComponentType<EntityStore, FactionIdentityComponent> factionIdentityComponentComponentType;
    private ComponentType<EntityStore, HomeComponent> homeComponentComponentType;
    private ComponentType<EntityStore, PowerComponent> powerComponentComponentType;
    private ComponentType<EntityStore, VulnerableComponent> vulnerableComponentComponentType;

    private ComponentType<EntityStore, MemberFactionComponent> memberFactionComponentComponentType;

    private ComponentType<EntityStore, OwnershipComponent> ownershipComponentComponentType;
    private ComponentType<EntityStore, ChunkLocationComponent> chunkLocationComponentComponentType;

    private ResourceType<EntityStore, ClaimRegistryResource> claimRegistryResourceResourceType;
    private ResourceType<EntityStore, FactionRegistryResource> factionRegistryResourceResourceType;

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

        this.memberFactionComponentComponentType = getEntityStoreRegistry().registerComponent(MemberFactionComponent.class, "MemberFaction", MemberFactionComponent.CODEC);

        this.ownershipComponentComponentType = getEntityStoreRegistry().registerComponent(OwnershipComponent.class, "Ownership", OwnershipComponent.CODEC);
        this.chunkLocationComponentComponentType = getEntityStoreRegistry().registerComponent(ChunkLocationComponent.class, "ChunkLocation", ChunkLocationComponent.CODEC);
    }

    private void loadResources() {
        this.claimRegistryResourceResourceType = getEntityStoreRegistry().registerResource(ClaimRegistryResource.class, ClaimRegistryResource::new);
        this.factionRegistryResourceResourceType = getEntityStoreRegistry().registerResource(FactionRegistryResource.class, FactionRegistryResource::new);
    }

    private void loadSystems() {
        getEntityStoreRegistry().registerSystem(new OverclaimSystem(this));
        getEntityStoreRegistry().registerSystem(new PowerRegenSystem(this));
        getEntityStoreRegistry().registerSystem(new PowerLossSystem(this));
        getEntityStoreRegistry().registerSystem(new ClaimSyncSystem(this));
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

    public ComponentType<EntityStore, MemberFactionComponent> getMemberFactionComponentComponentType() {
        return memberFactionComponentComponentType;
    }

    public ComponentType<EntityStore, OwnershipComponent> getOwnershipComponentComponentType() {
        return ownershipComponentComponentType;
    }

    public ComponentType<EntityStore, ChunkLocationComponent> getChunkLocationComponentComponentType() {
        return chunkLocationComponentComponentType;
    }

    public ResourceType<EntityStore, ClaimRegistryResource> getClaimRegistryResourceResourceType() {
        return claimRegistryResourceResourceType;
    }

    public ResourceType<EntityStore, FactionRegistryResource> getFactionRegistryResourceResourceType() {
        return factionRegistryResourceResourceType;
    }
}
