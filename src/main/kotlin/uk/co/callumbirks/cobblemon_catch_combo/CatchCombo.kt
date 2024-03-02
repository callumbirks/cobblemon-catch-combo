package uk.co.callumbirks.cobblemon_catch_combo

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import uk.co.callumbirks.cobblemon_catch_combo.config.SpawnBoostConfig

object CatchCombo : ModInitializer {
    const val MOD_ID = "cobblemon_catch_combo"

    private val logger = LoggerFactory.getLogger("cobblemon-catch-combo")

    public lateinit var spawnBoostConfig: SpawnBoostConfig

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")
        spawnBoostConfig = SpawnBoostConfig.Builder.load()
    }

    fun info(message: String) {
        logger.info(message)
    }
}