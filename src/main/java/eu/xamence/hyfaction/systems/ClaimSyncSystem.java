package eu.xamence.hyfaction.systems;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import eu.xamence.hyfaction.HyFaction;
import eu.xamence.hyfaction.components.world.ChunkLocationComponent;
import eu.xamence.hyfaction.components.world.OwnershipComponent;
import eu.xamence.hyfaction.resources.ClaimRegistryResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class ClaimSyncSystem extends RefChangeSystem<EntityStore, OwnershipComponent> {

    private final HyFaction hyFaction;

    public ClaimSyncSystem(HyFaction hyFaction) {
        this.hyFaction = hyFaction;
    }

    @Nonnull
    @Override
    public ComponentType<EntityStore, OwnershipComponent> componentType() {
        return hyFaction.getOwnershipComponentComponentType();
    }

    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> ref, @Nonnull OwnershipComponent ownershipComponent, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        ClaimRegistryResource registry = store.getResource(hyFaction.getClaimRegistryResourceResourceType());

        ChunkLocationComponent loc = store.getComponent(ref, hyFaction.getChunkLocationComponentComponentType());

        assert loc != null;

        registry.registerClaim(
                ChunkLocationComponent.getChunkKey(loc.getX(), loc.getZ()),
                ref,
                ownershipComponent.getOwnerFactionID());
    }

    @Override
    public void onComponentSet(@Nonnull Ref<EntityStore> ref, @Nullable OwnershipComponent ownershipComponent, @Nonnull OwnershipComponent t1, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {}

    @Override
    public void onComponentRemoved(@Nonnull Ref<EntityStore> ref, @Nonnull OwnershipComponent ownershipComponent, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        ClaimRegistryResource registry = store.getResource(hyFaction.getClaimRegistryResourceResourceType());

        ChunkLocationComponent loc = store.getComponent(ref, hyFaction.getChunkLocationComponentComponentType());

        assert loc != null;

        registry.unregisterClaim(ChunkLocationComponent.getChunkKey(loc.getX(), loc.getZ()), ownershipComponent.getOwnerFactionID());
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {

        return Query.and(hyFaction.getOwnershipComponentComponentType(), hyFaction.getChunkLocationComponentComponentType());
    }
}
