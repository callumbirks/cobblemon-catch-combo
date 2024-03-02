package uk.co.callumbirks.cobblemon_catch_combo.util

import net.minecraft.entity.player.PlayerEntity
import us.timinc.mc.cobblemon.counter.api.CaptureApi
import us.timinc.mc.cobblemon.counter.api.KoApi

object Util {
    fun getPlayerScore(
        player: PlayerEntity,
        species: String,
        koStreakPoints: Int,
        koCountPoints: Int,
        captureStreakPoints: Int,
        captureCountPoints: Int,
    ): Int {
        val koStreakData = KoApi.getStreak(player)
        val koStreak = if (koStreakData.first == species) koStreakData.second else 0
        val koCount = KoApi.getCount(player, species)
        val captureStreakData = CaptureApi.getStreak(player)
        val captureStreak = if (captureStreakData.first == species) captureStreakData.second else 0
        val captureCount = CaptureApi.getCount(player, species)
        return (koStreak * koStreakPoints) + (koCount * koCountPoints) + (captureStreak * captureStreakPoints) + (captureCount * captureCountPoints)
    }
}