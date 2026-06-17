package com.juiceybeans.slugmosis.entity.client.armor;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.item.VespertineArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class VespertineArmorRenderer extends GeoArmorRenderer<VespertineArmorItem> {
    public VespertineArmorRenderer() {
        super(new DefaultedItemGeoModel(Slugmosis.id("armor/vespertine_armor")));
    }
}

// And so she put her hand through the cracks of the universe
// And reached through into the heart of the cosmos
// Wresting Her power, Her birthright
// From the hands of the gods themeslves