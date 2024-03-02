package uk.co.callumbirks.cobblemon_catch_combo.mixin;

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import uk.co.callumbirks.cobblemon_catch_combo.spawning.PlayerCatchComboInfluence;

@Mixin(value = PlayerSpawnerFactory.class)
public class CatchComboInfluenceMixin {
    @Inject(method = "<init>", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void addSpawnBoostInfluence(CallbackInfo ci) {
        var influenceBuilders = ((PlayerSpawnerFactory) (Object) this).getInfluenceBuilders();
        influenceBuilders.add((player) -> new PlayerCatchComboInfluence(player, 5000L));
    }
}