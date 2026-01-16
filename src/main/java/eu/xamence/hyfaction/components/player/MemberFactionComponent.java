package eu.xamence.hyfaction.components.player;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;
import java.util.UUID;

public class MemberFactionComponent implements Component<EntityStore> {

    private UUID factionID;
    private String factionRank;

    public static final BuilderCodec<MemberFactionComponent> CODEC =
            BuilderCodec.builder(MemberFactionComponent.class, MemberFactionComponent::new)
                    .append(new KeyedCodec<>("FactionID", Codec.UUID_BINARY),
                            (c, v) -> c.factionID = v, c -> c.factionID)
                    .add()
                    .append(new KeyedCodec<>("FactionRank", Codec.STRING),
                            (c, v) -> c.factionRank = v, c -> c.factionRank)
                    .add()
                    .build();

    public MemberFactionComponent() {
    }

    public MemberFactionComponent(UUID factionID, String factionRank) {
        this.factionID = factionID;
        this.factionRank = factionRank;
    }

    public UUID getFactionID() {
        return factionID;
    }

    public String getFactionRank() {
        return factionRank;
    }

    public void setFactionID(UUID factionID) {
        this.factionID = factionID;
    }

    public void setFactionRank(String factionRank) {
        this.factionRank = factionRank;
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new MemberFactionComponent(factionID, factionRank);
    }
}
