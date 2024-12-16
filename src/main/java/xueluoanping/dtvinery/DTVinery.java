package xueluoanping.dtvinery;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;

import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.satisfy.vinery.core.entity.WanderingWinemakerEntity;
import net.satisfy.vinery.core.registry.ObjectRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xueluoanping.dtvinery.data.start;

import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DTVinery.MOD_ID)
public class DTVinery {
    public static final String MOD_ID = "dtvinery";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");

    public DTVinery() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // MinecraftForge.EVENT_BUS.register(TreeGrowHandler.instance);

        RegistryHandler.setup(MOD_ID);


    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        //        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        for (VillagerTrades.ItemListing itemListing : WanderingWinemakerEntity.TRADES.get(1)) {
            var itemsForEmeralds= (VillagerTrades.ItemsForEmeralds) itemListing;
            var item=itemsForEmeralds.itemStack.getItem();
            if (item== ObjectRegistry.APPLE_TREE_SAPLING.get().asItem()){
                ((VillagerTrades.ItemsForEmeralds)itemListing).itemStack=
                        new ItemStack(ModConstants.APPLE_OAK_SEED.get());
            } else if (item==ObjectRegistry.DARK_CHERRY_SAPLING.get().asItem()) {
                ((VillagerTrades.ItemsForEmeralds)itemListing).itemStack=
                        new ItemStack(ModConstants.DARK_CHERRY_SEED.get());
            }
        }
        // WanderingWinemakerEntity.TRADES.put(1, new VillagerTrades.ItemListing[]{
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.WHITE_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.TAIGA_RED_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.TAIGA_WHITE_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.SAVANNA_RED_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.SAVANNA_WHITE_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.JUNGLE_RED_GRAPE_SEEDS.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.JUNGLE_WHITE_GRAPE.get(), 1, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ModConstants.DARK_CHERRY_SEED.get(), 3, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ModConstants.APPLE_OAK_SEED.get(), 5, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPE.get(), 2, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_GRAPEJUICE.get(), 4, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.WHITE_GRAPEJUICE.get(), 4, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_SAVANNA_GRAPEJUICE.get(), 4, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.WHITE_TAIGA_GRAPEJUICE.get(), 4, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.RED_JUNGLE_GRAPEJUICE.get(), 4, 1, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.COARSE_DIRT_SLAB.get(), 1, 3, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.GRASS_SLAB.get(), 1, 3, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.DARK_CHERRY_PLANKS.get(), 3, 4, 8, 1),
        //         new VillagerTrades.ItemsForEmeralds(ObjectRegistry.CHERRY_WINE.get(), 1, 1, 8, 1)
        // });
    }

    public void gatherData(final GatherDataEvent event) {
        // Resources.MANAGER.gatherData();

        // GatherDataHelper.gatherAllData(
        //         MOD_ID,
        //         event,
        //         SoilProperties.REGISTRY,
        //         Family.REGISTRY,
        //         Species.REGISTRY,
        //         LeavesProperties.REGISTRY
        // );

        start.dataGen(event);
    }

    public static void logger(Object... x) {

        // if (General.bool.get())
        if (useLogger) {
            StringBuilder output = new StringBuilder();
            for (Object i : x) {
                output.append("，【").append(i).append("】");
            }
            LOGGER.info(output.substring(1));
        }

    }

    public static ResourceLocation rl(String name) {
        return new ResourceLocation(DTVinery.MOD_ID, name);
    }
}
