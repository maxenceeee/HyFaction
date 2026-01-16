package eu.xamence.hyfaction;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.components.faction.FactionIdentityComponent;
import eu.xamence.hyfaction.components.faction.HomeComponent;
import eu.xamence.hyfaction.components.faction.PowerComponent;

import javax.annotation.Nonnull;

public class HyFaction extends JavaPlugin {

    private final HytaleLogger LOGGER;

    private ComponentType<EntityStore, FactionIdentityComponent> factionIdentityComponentComponentType;
    private ComponentType<EntityStore, HomeComponent> homeComponentComponentType;
    private ComponentType<EntityStore, PowerComponent> powerComponentComponentType;

    public HyFaction(@Nonnull JavaPluginInit init) {
        super(init);

        this.LOGGER = HytaleLogger.forEnclosingClass();
    }

    @Override
    protected void setup() {
        this.loadComponent();
    }

    private void loadComponent() {
        this.factionIdentityComponentComponentType = getEntityStoreRegistry().registerComponent(FactionIdentityComponent.class, "FactionIdentity", FactionIdentityComponent.CODEC);
        this.homeComponentComponentType = getEntityStoreRegistry().registerComponent(HomeComponent.class, "Home", HomeComponent.CODEC);
        this.powerComponentComponentType = getEntityStoreRegistry().registerComponent(PowerComponent.class, "Power", PowerComponent.CODEC);
    }
}
