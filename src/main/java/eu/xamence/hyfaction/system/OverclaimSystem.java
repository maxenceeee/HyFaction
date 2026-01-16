package eu.xamence.hyfaction.system;


import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EcsEvent;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.system.EntitySystems;
import eu.xamence.hyfaction.components.faction.FactionIdentityComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OverclaimSystem extends EntityEventSystem {


    @Override
    public void handle(int i, @Nonnull ArchetypeChunk archetypeChunk, @Nonnull Store store, @Nonnull CommandBuffer commandBuffer, @Nonnull EcsEvent ecsEvent) {

    }

    @Nullable
    @Override
    public Query getQuery() {
        return Query.and(FactionIdentityComponent.class, );
    }
}
