package eu.xamence.hyfaction.components.faction;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;
import java.util.UUID;

public class FactionIdentityComponent implements Component<EntityStore> {

    private UUID factionID;
    private String displayName;
    private String tag;

    public static final BuilderCodec<FactionIdentityComponent> CODEC =
            BuilderCodec.builder(FactionIdentityComponent.class, FactionIdentityComponent::new)
                    .append(new KeyedCodec<>("FactionID", Codec.UUID_BINARY),
                            (c, v) -> c.factionID = v, c -> c.factionID)
                    .add()
                    .append(new KeyedCodec<>("DisplayName", Codec.STRING),
                            (c, v) -> c.displayName = v, c -> c.displayName)
                    .add()
                    .append(new KeyedCodec<>("Tag", Codec.STRING),
                            (c, v) -> c.tag = v, c -> c.tag)
                    .add()
                    .build();




    public FactionIdentityComponent() {}

    public FactionIdentityComponent(UUID factionID, String displayName, String tag) {
        this.factionID = factionID;
        this.displayName = displayName;
        this.tag = tag;
    }

    public UUID getFactionID() {
        return factionID;
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

    public void setFactionID(UUID factionID) {
        this.factionID = factionID;
    }

    @Nullable
    @Override
    public Component clone() {
        return new FactionIdentityComponent(this.factionID, this.displayName, this.tag);
    }
}
