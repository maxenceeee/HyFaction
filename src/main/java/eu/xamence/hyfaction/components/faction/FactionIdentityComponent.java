package eu.xamence.hyfaction.components.faction;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class FactionIdentityComponent implements Component<EntityStore> {

    private String displayName;
    private String tag;

    public static final BuilderCodec<FactionIdentityComponent> CODEC =
            BuilderCodec.builder(FactionIdentityComponent.class, FactionIdentityComponent::new)
                    .append(new KeyedCodec<>("DisplayName", Codec.STRING),
                            (c, v) -> c.displayName = v, c -> c.displayName)
                    .add()
                    .append(new KeyedCodec<>("Tag", Codec.STRING),
                            (c, v) -> c.tag = v, c -> c.tag)
                    .add()
                    .build();




    public FactionIdentityComponent() {}

    public FactionIdentityComponent(String displayName, String tag) {
        this.displayName = displayName;
        this.tag = tag;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTag() {
        return tag;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Nullable
    @Override
    public Component clone() {
        return new FactionIdentityComponent(this.displayName, this.tag);
    }
}
