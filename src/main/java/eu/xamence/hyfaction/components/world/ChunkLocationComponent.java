package eu.xamence.hyfaction.components.world;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class ChunkLocationComponent implements Component<EntityStore> {

    private int x;
    private int z;
    private String worldName;

    public static final BuilderCodec<ChunkLocationComponent> CODEC = BuilderCodec.builder(ChunkLocationComponent.class, ChunkLocationComponent::new)
            .append(new KeyedCodec<>("X", BuilderCodec.INTEGER),
                    (c, v) -> c.x = v, c -> c.x)
            .add()
            .append(new KeyedCodec<>("Z", BuilderCodec.INTEGER),
                    (c, v) -> c.z = v, c -> c.z)
            .add()
            .append(new KeyedCodec<>("WorldName", BuilderCodec.STRING),
                    (c, v) -> c.worldName = v, c -> c.worldName)
            .add()
            .build();

    public ChunkLocationComponent() {
    }

    public ChunkLocationComponent(int x, int z, String worldName) {
        this.x = x;
        this.z = z;
        this.worldName = worldName;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public String getWorldName() {
        return worldName;
    }

    public static long getChunkKey(int x, int z) {
        return ((long) x << 32) | (z & 0xFFFFFFFFL);
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new ChunkLocationComponent(x, z, worldName);
    }
}
