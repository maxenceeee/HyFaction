package eu.xamence.hyfaction.systems;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.HyFaction;
import eu.xamence.hyfaction.components.faction.PowerComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PowerRegenSystem extends EntityTickingSystem<EntityStore> {

    private static final double POWER_REGEN_TICK = 1.0 / (5.0 * 60.0 * 30.0); // 1 power every 5 minutes

    private final HyFaction hyFaction;

    public PowerRegenSystem(HyFaction hyFaction) {
        this.hyFaction = hyFaction;
    }


    @Override
    public void tick(float v, int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        PowerComponent powerComponent = archetypeChunk.getComponent(i, hyFaction.getPowerComponentComponentType());

        assert powerComponent != null : "Power component is null";


        if (powerComponent.getCurrent() < powerComponent.getMax()) {
            powerComponent.setCurrent(Math.min(powerComponent.getMax(), powerComponent.getCurrent() + POWER_REGEN_TICK));
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(hyFaction.getFactionIdentityComponentComponentType(), hyFaction.getPowerComponentComponentType());
    }
}
