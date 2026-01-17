package eu.xamence.hyfaction.resources;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Resource;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionRegistryResource implements Resource<EntityStore> {

    private Map<UUID, Ref<EntityStore>> uuidToEntityRef = new HashMap<>();

    public void register(UUID factionId, Ref<EntityStore> entityId) {
        uuidToEntityRef.put(factionId, entityId);
    }

    public void unregister(UUID factionId) {
        uuidToEntityRef.remove(factionId);
    }

    public Ref<EntityStore> getEntityId(UUID factionId) {
        return uuidToEntityRef.get(factionId);
    }


    @Nullable
    @Override
    public Resource<EntityStore> clone() {
        FactionRegistryResource copy = new FactionRegistryResource();
        copy.uuidToEntityRef = this.uuidToEntityRef;

        return copy;
    }
}
