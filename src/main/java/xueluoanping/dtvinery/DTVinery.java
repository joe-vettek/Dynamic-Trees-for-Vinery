package xueluoanping.dtvinery;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // MinecraftForge.EVENT_BUS.register(TreeGrowHandler.instance);
        RegistryHandler.setup(MOD_ID);


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
