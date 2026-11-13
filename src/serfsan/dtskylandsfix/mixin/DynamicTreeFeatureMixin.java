package serfsan.dtskylandsfix.mixin;

import com.dtteam.dynamictrees.api.worldgen.LevelContext;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// DT 2ce0dfdd moved the heightmap biome check from y=0 to the bottom of the world; move it back.
@Mixin(targets = "com.dtteam.dynamictrees.worldgen.feature.DynamicTreeFeature", remap = false)
public abstract class DynamicTreeFeatureMixin {

    @WrapOperation(method = "generateTrees", remap = false, require = 1,
            at = @At(value = "INVOKE", ordinal = 0, remap = false,
                    target = "Lcom/dtteam/dynamictrees/worldgen/feature/DynamicTreeFeature;getNoiseBiome(Lcom/dtteam/dynamictrees/api/worldgen/LevelContext;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;"))
    private Holder<?> dtskylandsfix$heightmapBiome(LevelContext context, BlockPos pos, Operation<Holder<?>> original) {
        return original.call(context, new BlockPos(pos.getX(), Math.max(pos.getY(), 0), pos.getZ()));
    }
}
