package eu.xamence.hyfaction.systems;


import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.HyFaction;
import eu.xamence.hyfaction.components.faction.FactionIdentityComponent;
import eu.xamence.hyfaction.components.faction.PowerComponent;
import eu.xamence.hyfaction.components.faction.VulnerableComponent;
import eu.xamence.hyfaction.resources.ClaimRegistryResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OverclaimSystem extends EntityTickingSystem<EntityStore> {



    private final HyFaction hyFaction;

    public OverclaimSystem(HyFaction hyFaction) {
        this.hyFaction = hyFaction;
    }


    @Override
    public void tick(float v, int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        PowerComponent powerComponent = archetypeChunk.getComponent(index, hyFaction.getPowerComponentComponentType());
        FactionIdentityComponent factionIdentityComponent = archetypeChunk.getComponent(index, hyFaction.getFactionIdentityComponentComponentType());

        assert powerComponent != null : "PowerComponent is null";
        assert factionIdentityComponent != null : "FactionIdentityComponent is null";

        ClaimRegistryResource registry = store.getResource(hyFaction.getClaimRegistryResourceResourceType());
        int currentClaims = registry.getClaimCount(factionIdentityComponent.getFactionID());

        if (currentClaims > powerComponent.getCurrent()) {
            if (archetypeChunk.getComponent(index, hyFaction.getVulnerableComponentComponentType()) == null) {
                commandBuffer.addComponent(archetypeChunk.getReferenceTo(index), hyFaction.getVulnerableComponentComponentType());
            }
        } else {
            if (archetypeChunk.getComponent(index, hyFaction.getVulnerableComponentComponentType()) == null) {
                commandBuffer.removeComponent(archetypeChunk.getReferenceTo(index), hyFaction.getVulnerableComponentComponentType());
            }
        }
    }


    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(hyFaction.getFactionIdentityComponentComponentType(), hyFaction.getPowerComponentComponentType());
    }
}
