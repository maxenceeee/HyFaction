package eu.xamence.hyfaction.systems;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.HyFaction;
import eu.xamence.hyfaction.components.faction.PowerComponent;
import eu.xamence.hyfaction.components.player.MemberFactionComponent;
import eu.xamence.hyfaction.resources.FactionRegistryResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class PowerLossSystem extends DeathSystems.OnDeathSystem {

    private final HyFaction hyFaction;

    public PowerLossSystem(HyFaction hyFaction) {
        this.hyFaction = hyFaction;
    }

    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref, @Nonnull DeathComponent deathComponent, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {

        MemberFactionComponent member = store.getComponent(ref, hyFaction.getMemberFactionComponentComponentType());

        assert member != null : "MemberFactionComponent is null";

        UUID factionID = member.getFactionID();

        FactionRegistryResource registryResource = store.getResource(hyFaction.getFactionRegistryResourceResourceType());
        Ref<EntityStore> factionEntity = registryResource.getEntityId(factionID);

        if (factionEntity.isValid()) {
            PowerComponent power = store.ensureAndGetComponent(factionEntity, hyFaction.getPowerComponentComponentType());
            power.setCurrent(power.getCurrent() - 2);
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return hyFaction.getMemberFactionComponentComponentType();
    }
}
