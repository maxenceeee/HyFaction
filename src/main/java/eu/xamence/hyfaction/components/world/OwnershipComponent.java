package eu.xamence.hyfaction.components.world;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;
import java.util.UUID;

public class OwnershipComponent implements Component<EntityStore> {

    private UUID ownerFactionID;


    public static final BuilderCodec<OwnershipComponent> CODEC = BuilderCodec.builder(OwnershipComponent.class, OwnershipComponent::new)
            .append(new KeyedCodec<>("OwnerFactionID", BuilderCodec.UUID_BINARY),
                    (c, v) -> c.ownerFactionID = v, c -> c.ownerFactionID)
            .add()
            .build();

    public OwnershipComponent() {
    }

    public OwnershipComponent(UUID ownerFactionID) {
        this.ownerFactionID = ownerFactionID;
    }

    public UUID getOwnerFactionID() {
        return ownerFactionID;
    }

    public void setOwnerFactionID(UUID ownerFactionID) {
        this.ownerFactionID = ownerFactionID;
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new OwnershipComponent(ownerFactionID);
    }
}
