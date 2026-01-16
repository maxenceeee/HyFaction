package eu.xamence.hyfaction.components.faction;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class PowerComponent implements Component<EntityStore> {


    private double current;
    private double max;

    public static final BuilderCodec<PowerComponent> CODEC =
            BuilderCodec.builder(PowerComponent.class, PowerComponent::new)
                    .append(new KeyedCodec<>("Current", Codec.DOUBLE),
                            (c, v) -> c.current = v, c -> c.current)
                    .add()
                    .append(new KeyedCodec<>("Tag", Codec.DOUBLE),
                            (c, v) -> c.max = v, c -> c.max)
                    .add()
                    .build();

    public PowerComponent() {
    }

    public PowerComponent(double current, double max) {
        this.current = current;
        this.max = max;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return null;
    }
}
