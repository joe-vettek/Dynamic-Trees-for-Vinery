package xueluoanping.dtvinery;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xueluoanping.dtvinery.util.LazyGet;
import xueluoanping.dtvinery.util.RegisterFinderUtil;


public class ModConstants {


    public static final LazyGet<Item> APPLE_SEED = LazyGet.of(() -> RegisterFinderUtil.getItem(DTVinery.rl("apple_seed")));
    public static final LazyGet<Item> DARK_CHERRY_SEED = LazyGet.of(() -> RegisterFinderUtil.getItem(DTVinery.rl("dark_cherry_seed")));

    // public static final LazyGet<Block> APPLE = LazyGet.of(() -> RegisterFinderUtil.getBlock(DynamicTrees.location("apple")));
    // public static final LazyGet<Item> APPLE_OAK_SEED = LazyGet.of(() -> RegisterFinderUtil.getItem(DynamicTrees.location("apple_oak_seed")));
    // public static final LazyGet<Item> CHERRY_SEED_V = LazyGet.of(() -> RegisterFinderUtil.getItem(DynamicTrees.location("cherry_seed")));
    // public static final LazyGet<Block> CHERRY_LEAVES_V = LazyGet.of(() -> RegisterFinderUtil.getBlock(DynamicTrees.location("cherry_leaves")));
    //


}
