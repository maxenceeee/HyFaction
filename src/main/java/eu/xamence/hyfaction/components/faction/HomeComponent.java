package eu.xamence.hyfaction.components.faction;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class HomeComponent implements Component<EntityStore> {

    private String worldName;
    private double x;
    private double y;
    private double z;

    public static final BuilderCodec<HomeComponent> CODEC =
            BuilderCodec.builder(HomeComponent.class, HomeComponent::new)
                    .append(new KeyedCodec<>("WorldName", Codec.STRING),
                            (c, v) -> c.worldName = v, c -> c.worldName)
                    .add()
                    .append(new KeyedCodec<>("X", Codec.DOUBLE),
                            (c, v) -> c.x = v, c -> c.x)
                    .add()
                    .append(new KeyedCodec<>("Y", Codec.DOUBLE),
                            (c, v) -> c.y = v, c -> c.y)
                    .add()
                    .append(new KeyedCodec<>("Z", Codec.DOUBLE),
                            (c, v) -> c.z = v, c -> c.z)
                    .add()
                    .build();


    public HomeComponent() {
    }

    public HomeComponent(String worldName, double x, double y, double z) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new HomeComponent(worldName, x, y ,z);
    }
}
