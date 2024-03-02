package uk.co.callumbirks.cobblemon_catch_combo.spawning

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence
import net.minecraft.server.network.ServerPlayerEntity
import uk.co.callumbirks.cobblemon_catch_combo.CatchCombo
import uk.co.callumbirks.cobblemon_catch_combo.config.SpawnBoostConfig
import uk.co.callumbirks.cobblemon_catch_combo.util.Util

open class PlayerCatchComboInfluence(
    val player: ServerPlayerEntity, val recalculationMillis: Long = 5000L
) : SpawningInfluence {
    var lastCalculatedTime: Long = 0
    var previousScore: Int = 0
    val config: SpawnBoostConfig = CatchCombo.spawnBoostConfig

    private fun isPlayerInRange(ctx: SpawningContext, range: Double): Boolean {
        return player.squaredDistanceTo(ctx.position.toCenterPos()) <= range * range
    }

    private fun getPlayerScore(species: String): Int {
        return if (System.currentTimeMillis() - lastCalculatedTime > recalculationMillis) {
            lastCalculatedTime = System.currentTimeMillis()
            previousScore = Util.getPlayerScore(
                player,
                species,
                config.koStreakPoints,
                config.koCountPoints,
                config.captureStreakPoints,
                config.captureCountPoints
            )
            previousScore
        } else {
            previousScore
        }
    }

    override fun affectWeight(detail: SpawnDetail, ctx: SpawningContext, weight: Float): Float {
        if (config.debug) {
            CatchCombo.info("affectWeight called")
        }
        if (isPlayerInRange(ctx, config.effectiveRange.toDouble()) && detail is PokemonSpawnDetail) {
            detail.pokemon.species?.let { species ->
                return weight + weight * getPlayerScore(species)
            }
        }
        return weight
    }
}