package com.lightre.citresewnrebuilt.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.DummyProfiler;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.lightre.citresewnrebuilt.cit.ActiveCITs;

import java.util.Map;

/**
 * Initializes the (re)loading of active cits in the resource manager.
 *
 * @see ActiveCITs
 */
@Mixin(ModelBaker.class)
public class ModelBakerMixin {
    /**
     * @see ActiveCITs#load(ResourceManager, Profiler)
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void citresewn$loadCITs(Map<?, ?> models, Map<?, ?> resolvedModels, UnbakedModel missingModel, CallbackInfo ci) {
        Profiler profiler = DummyProfiler.INSTANCE;
        profiler.push("citresewn:reloading_cits");
        ActiveCITs.load(MinecraftClient.getInstance().getResourceManager(), profiler);
        profiler.pop();
    }
}
