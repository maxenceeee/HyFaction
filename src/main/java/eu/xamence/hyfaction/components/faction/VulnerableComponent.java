package eu.xamence.hyfaction.components.faction;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class VulnerableComponent implements Component<EntityStore> {

    public static final VulnerableComponent INSTANCE = new VulnerableComponent();

    public static final BuilderCodec<VulnerableComponent> CODEC =
            BuilderCodec.builder(VulnerableComponent.class, () -> INSTANCE).build();

    private VulnerableComponent() {}

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return INSTANCE;
    }
}
