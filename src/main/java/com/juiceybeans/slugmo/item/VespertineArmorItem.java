package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.entity.client.armor.VespertineArmorRenderer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;

public final class VespertineArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VespertineArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
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
        controllers.add(new AnimationController[]{new AnimationController(this, 20, (state) -> {
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

                boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(new VespertineArmorItem[]{
                        (VespertineArmorItem) ModItems.VESPERTINE_ARMOR_HELMET.get(),
                        (VespertineArmorItem)ModItems.VESPERTINE_ARMOR_CHESTPLATE.get(),
                        (VespertineArmorItem)ModItems.VESPERTINE_ARMOR_LEGGINGS.get(),
                        (VespertineArmorItem)ModItems.VESPERTINE_ARMOR_BOOTS.get()}));
                return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
            }
        })});
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
