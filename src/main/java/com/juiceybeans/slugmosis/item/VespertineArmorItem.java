package com.juiceybeans.slugmosis.item;

import com.juiceybeans.slugmosis.entity.client.armor.VespertineArmorRenderer;
import com.juiceybeans.slugmosis.util.EquipmentUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public final class VespertineArmorItem extends ArmorItem implements GeoItem {
    private static boolean isInSunlight = false;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final UUID HELMET_ARMOR_UUID = UUID.fromString("d04f985c-b9fa-406e-a1d3-3ee45dde81dc");
    private static final UUID CHESTPLATE_ARMOR_UUID = UUID.fromString("7ca74c80-1607-4245-8219-d452457abe43");
    private static final UUID LEGGINGS_ARMOR_UUID = UUID.fromString("51e776cc-846d-4864-b36b-01a17d752d4f");
    private static final UUID BOOTS_ARMOR_UUID = UUID.fromString("1314f5b6-1e2f-4087-aa53-f6993dbef510");

    private static final UUID HELMET_TOUGHNESS_UUID = UUID.fromString("600ae3d6-6a0a-4a6e-ae3b-db2df517e37b");
    private static final UUID CHESTPLATE_TOUGHNESS_UUID = UUID.fromString("974dacd8-ae59-4367-b703-978307764e68");
    private static final UUID LEGGINGS_TOUGHNESS_UUID = UUID.fromString("854fea92-6c9d-4801-b82f-ffedeea8f9eb");
    private static final UUID BOOTS_TOUGHNESS_UUID = UUID.fromString("0c6dbc2d-206f-4e95-9bdd-3e7c50854b75");

    public VespertineArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity.tickCount % 10 != 0) return;
        if (level.isClientSide()) return;

        isInSunlight = level.canSeeSky(entity.blockPosition()) && level.isDay();

        if (entity instanceof LivingEntity guy) {
            EquipmentSlot slot = EquipmentUtils.getEquipmentSlotForItem(this.type);
            ItemStack equippedStack = guy.getItemBySlot(slot);

            boolean isEquipped = equippedStack.getItem() == this;

            var armor = guy.getAttribute(Attributes.ARMOR);
            var armorModifier = new AttributeModifier(getArmorUUIDForSlot(this.type), "Vespertine Armor Bonus",
                    1.0f, AttributeModifier.Operation.ADDITION);

            var toughness = guy.getAttribute(Attributes.ARMOR_TOUGHNESS);
            var toughnessModifier = new AttributeModifier(getToughnessUUIDForSlot(this.type), "Vespertine Armor Toughness Bonus",
                    1.0f, AttributeModifier.Operation.ADDITION);

            if (!isInSunlight || !isEquipped) {
                if (armor.hasModifier(armorModifier)) {
                    armor.removeModifier(armorModifier);
                }
                if (toughness.hasModifier(toughnessModifier)) {
                    toughness.removeModifier(toughnessModifier);
                }
            } else {
                if (!armor.hasModifier(armorModifier) && isEquipped) {
                    armor.addTransientModifier(armorModifier);
                }
                if (!toughness.hasModifier(toughnessModifier) && isEquipped) {
                    toughness.addTransientModifier(toughnessModifier);
                }
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    private UUID getArmorUUIDForSlot(ArmorItem.Type type) {
        return switch (type.getSlot()) {
            case HEAD -> HELMET_ARMOR_UUID;
            case CHEST -> CHESTPLATE_ARMOR_UUID;
            case LEGS -> LEGGINGS_ARMOR_UUID;
            case FEET -> BOOTS_ARMOR_UUID;
            default -> null;
        };
    }

    private UUID getToughnessUUIDForSlot(ArmorItem.Type type) {
        return switch (type.getSlot()) {
            case HEAD -> HELMET_TOUGHNESS_UUID;
            case CHEST -> CHESTPLATE_TOUGHNESS_UUID;
            case LEGS -> LEGGINGS_TOUGHNESS_UUID;
            case FEET -> BOOTS_TOUGHNESS_UUID;
            default -> null;
        };
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (isInSunlight) {
            pTooltipComponents.add(Component.translatable("item.slugmosis.vespertine_armor.tooltip.sunlight.true"));
        } else {
            pTooltipComponents.add(Component.translatable("item.slugmosis.vespertine_armor.tooltip.sunlight.false"));
        }

        pTooltipComponents.add(Component.translatable("item.slugmosis.vespertine_armor.tooltip.attribute.0"));
        pTooltipComponents.add(Component.translatable("item.slugmosis.vespertine_armor.tooltip.attribute.1"));
        pTooltipComponents.add(Component.translatable("item.slugmosis.vespertine_armor.tooltip.attribute.2"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new VespertineArmorRenderer();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, 20, (state) -> {
            state.setAnimation(DefaultAnimations.IDLE);
            Entity entity = (Entity)state.getData(DataTickets.ENTITY);
            if (entity instanceof ArmorStand) {
                return PlayState.CONTINUE;
            } else {
                Set<Item> wornArmor = new ObjectOpenHashSet();

                for(ItemStack stack : entity.getArmorSlots()) {
                    if (stack.isEmpty()) {
                        return PlayState.STOP;
                    }

                    wornArmor.add(stack.getItem());
                }

                boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                        (VespertineArmorItem) ModItems.VESPERTINE_ARMOR_HELMET.get(),
                        (VespertineArmorItem) ModItems.VESPERTINE_ARMOR_CHESTPLATE.get(),
                        (VespertineArmorItem) ModItems.VESPERTINE_ARMOR_LEGGINGS.get(),
                        (VespertineArmorItem) ModItems.VESPERTINE_ARMOR_BOOTS.get()));
                return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
            }
        }));
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
