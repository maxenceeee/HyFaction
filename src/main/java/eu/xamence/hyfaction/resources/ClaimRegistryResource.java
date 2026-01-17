package eu.xamence.hyfaction.resources;

import com.hypixel.hytale.component.Resource;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClaimRegistryResource implements Resource<EntityStore> {

    // Chunk key to entity ID
    private  Map<Long, Integer> chunkToEntityMap = new HashMap<>();

    // Faction entity ID to claim counts
    private  Map<UUID, Integer> factionClaimCount = new HashMap<>();


    public void registerClaim(long chunkKey, int entityId, UUID factionId) {
        chunkToEntityMap.put(chunkKey, entityId);
        factionClaimCount.put(factionId, factionClaimCount.getOrDefault(factionId, 0) + 1);
    }

    public void unregisterClaim(long chunkKey, UUID factionId) {
        chunkToEntityMap.remove(chunkKey);
        int count = factionClaimCount.getOrDefault(factionId, 0);

        if (count > 0) {
            factionClaimCount.put(factionId, count - 1);
        }
    }

    public Integer getClaimEntityAt(long chunkKey) {
        return chunkToEntityMap.get(chunkKey);
    }

    public int getClaimCount(UUID factionId) {
        return factionClaimCount.getOrDefault(factionId, 0);
    }


    @Nullable
    @Override
    public Resource<EntityStore> clone() {
        ClaimRegistryResource copy = new ClaimRegistryResource();
        copy.chunkToEntityMap = this.chunkToEntityMap;
        copy.factionClaimCount = this.factionClaimCount;

        return copy;
    }
}
