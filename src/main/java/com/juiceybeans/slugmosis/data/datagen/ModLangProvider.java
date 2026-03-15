package com.juiceybeans.slugmosis.data.datagen;

import com.juiceybeans.slugmosis.Slugmosis;
import com.juiceybeans.slugmosis.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output, String locale) {
        super(output, Slugmosis.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
            addItem(item, formatToEnglishLocalization(item.getId().getPath()));
        }

        addLang();
    }

    private static String formatToEnglishLocalization(String input) {
        return Arrays.stream(input.toLowerCase(Locale.ROOT).split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }

    private void addLang() {
        add("itemGroup.slugmosis.slugmo_tab", "Slugmosis Items");
        add("config.jade.plugin_slugmosis.lightning_agitator_info", "Lightning Agitator Info");
        add("slugmosis.jade.lightning_agitator.short_circuited", "§cShort circuited!");
    }
}
