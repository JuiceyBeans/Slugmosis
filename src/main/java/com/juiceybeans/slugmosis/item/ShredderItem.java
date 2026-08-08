package com.juiceybeans.slugmosis.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class ShredderItem extends SwordItem {
    public ShredderItem() {
        super(Tiers.IRON, 3, 1, new Item.Properties());
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.getArmorSlots().forEach(armorSlot -> armorSlot.hurt(4, pAttacker.level().getRandom(), null));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
