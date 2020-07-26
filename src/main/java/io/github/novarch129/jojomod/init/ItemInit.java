package io.github.novarch129.jojomod.init;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.JojoBizarreSurvival.JojoItemGroup;
import io.github.novarch129.jojomod.item.EmperorItem;
import io.github.novarch129.jojomod.item.StandArrowItem;
import io.github.novarch129.jojomod.item.StandDiscItem;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class reminds me of how stupid {@link DeferredRegister} looks when used to register lots of things.
 */
@SuppressWarnings("unused")
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<Item> STAND_ARROW = ITEMS.register("stand_arrow", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("arrow", TextFormatting.YELLOW)).group(JojoItemGroup.INSTANCE), 0, "On use, grants the user the power of a STAND."));
    public static final RegistryObject<Item> SUMMON_KING_CRIMSON = ITEMS.register("summon_king_crimson", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("king_crimson", TextFormatting.DARK_RED)).group(JojoItemGroup.INSTANCE), Util.StandID.KING_CRIMSON, "Skips 10 seconds of time, allowing the user to see others movements."));
    public static final RegistryObject<Item> SUMMON_D4C = ITEMS.register("summon_d4c", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("d4c", TextFormatting.AQUA)).group(JojoItemGroup.INSTANCE), Util.StandID.D4C, "Allows the user to hop to parallel worlds when between two objects."));
    public static final RegistryObject<Item> SUMMON_GOLD_EXPERIENCE = ITEMS.register("summon_gold_experience", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("gold_experience", TextFormatting.GOLD)).group(JojoItemGroup.INSTANCE), Util.StandID.GOLD_EXPERIENCE, "Has the ability to turn objects into living creatures."));
    public static final RegistryObject<Item> SUMMON_MADE_IN_HEAVEN = ITEMS.register("summon_made_in_heaven", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("made_in_heaven", TextFormatting.GREEN)).group(JojoItemGroup.INSTANCE), Util.StandID.MADE_IN_HEAVEN, "Possess the ability to accelerate the passage of time."));
    public static final RegistryObject<Item> SUMMON_AEROSMITH = ITEMS.register("summon_aerosmith", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("aerosmith", TextFormatting.LIGHT_PURPLE)).group(JojoItemGroup.INSTANCE), Util.StandID.AEROSMITH, "Remote controlled Stand, allows the user to detect entities by their breathing."));
    public static final RegistryObject<Item> SUMMON_WEATHER_REPORT = ITEMS.register("summon_weather_report", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("weather_report", TextFormatting.WHITE)).group(JojoItemGroup.INSTANCE), Util.StandID.WEATHER_REPORT, "Possess the ability to control the weather, is there something more to this ability?"));
    public static final RegistryObject<Item> SUMMON_KILLER_QUEEN = ITEMS.register("summon_killer_queen", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("killer_queen", TextFormatting.GRAY)).group(JojoItemGroup.INSTANCE), Util.StandID.KILLER_QUEEN, "Can turn anything it touches into a bomb.\n\nControls: \n" + Util.KeyCodes.ABILITY_1 + ": Activate 1st bomb.\n" + Util.KeyCodes.ABILITY_2 + ": Summon Sheer Heart Attack."));
    public static final RegistryObject<Item> SUMMON_CRAZY_DIAMOND = ITEMS.register("summon_crazy_diamond", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("crazy_diamond", TextFormatting.BLUE)).group(JojoItemGroup.INSTANCE), Util.StandID.CRAZY_DIAMOND, "Has the ability to revert objects to a previous state."));
    public static final RegistryObject<Item> SUMMON_PURPLE_HAZE = ITEMS.register("summon_purple_haze", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("purple_haze", TextFormatting.DARK_PURPLE)).group(JojoItemGroup.INSTANCE), Util.StandID.PURPLE_HAZE, "Releases a deadly virus into the atmosphere."));
    public static final RegistryObject<Item> SUMMON_THE_EMPEROR = ITEMS.register("summon_the_emperor", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("emperor", TextFormatting.DARK_GRAY)).group(JojoItemGroup.INSTANCE), Util.StandID.THE_EMPEROR, ""));
    public static final RegistryObject<Item> SUMMON_WHITESNAKE = ITEMS.register("summon_whitesnake", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("whitesnake", TextFormatting.WHITE)).group(JojoItemGroup.INSTANCE), Util.StandID.WHITESNAKE, ""));
    public static final RegistryObject<Item> SUMMON_CMOON = ITEMS.register("summon_cmoon", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("cmoon", TextFormatting.DARK_GREEN)).group(JojoItemGroup.INSTANCE), Util.StandID.CMOON, ""));
    public static final RegistryObject<Item> SUMMON_THE_WORLD = ITEMS.register("summon_the_world", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("the_world", TextFormatting.YELLOW)).group(JojoItemGroup.INSTANCE), Util.StandID.THE_WORLD, ""));
    public static final RegistryObject<Item> SUMMON_STAR_PLATINUM = ITEMS.register("summon_star_platinum", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("star_platinum", TextFormatting.DARK_PURPLE)).group(JojoItemGroup.INSTANCE), Util.StandID.STAR_PLATINUM, ""));
    public static final RegistryObject<Item> SUMMON_SILVER_CHARIOT = ITEMS.register("summon_silver_chariot", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("silver_chariot", TextFormatting.GRAY)).group(JojoItemGroup.INSTANCE), Util.StandID.SILVER_CHARIOT, ""));
    public static final RegistryObject<Item> SUMMON_MAGICIANS_RED = ITEMS.register("summon_magicians_red", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("magicians_red", TextFormatting.RED)).group(JojoItemGroup.INSTANCE), Util.StandID.MAGICIANS_RED, ""));
    public static final RegistryObject<Item> SUMMON_THE_HAND = ITEMS.register("summon_the_hand", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("the_hand", TextFormatting.DARK_BLUE)).group(JojoItemGroup.INSTANCE), Util.StandID.THE_HAND, ""));
    public static final RegistryObject<Item> SUMMON_HIEROPHANT_GREEN = ITEMS.register("summon_hierophant_green", () -> new StandArrowItem(new Item.Properties().maxStackSize(1).rarity(Rarity.create("hierophant_green", TextFormatting.GREEN)).group(JojoItemGroup.INSTANCE), Util.StandID.HIEROPHANT_GREEN, ""));

    public static final RegistryObject<Item> STAND_DISC = ITEMS.register("stand_disc", () -> new StandDiscItem(new Item.Properties().maxStackSize(1).group(JojoItemGroup.INSTANCE)));
    public static final RegistryObject<Item> THE_EMPEROR = ITEMS.register("the_emperor", () -> new EmperorItem(new Item.Properties().maxStackSize(1)));
    public static final RegistryObject<Item> CANZONI_PREFERITE = ITEMS.register("canzoni_preferite", () -> new MusicDiscItem(1, SoundInit.CANZONI_PREFERITE, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(JojoItemGroup.INSTANCE)));
}