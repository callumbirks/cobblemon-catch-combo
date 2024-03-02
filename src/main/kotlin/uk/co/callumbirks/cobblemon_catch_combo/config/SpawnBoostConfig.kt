package uk.co.callumbirks.cobblemon_catch_combo.config

import com.google.gson.GsonBuilder
import uk.co.callumbirks.cobblemon_catch_combo.CatchCombo
import java.io.File
import java.io.FileReader
import java.io.PrintWriter

class SpawnBoostConfig {
    val effectiveRange = 64
    val koStreakPoints = 1
    val koCountPoints = 0
    val captureStreakPoints = 1
    val captureCountPoints = 0
    val debug = false

    class Builder {
        companion object {
            fun load(): SpawnBoostConfig {
                val gson = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()

                var config = SpawnBoostConfig()
                val configFile = File("config/${CatchCombo.MOD_ID}/spawn_boost.json")
                configFile.parentFile.mkdirs()

                if (configFile.exists()) {
                    try {
                        val fileReader = FileReader(configFile)
                        config = gson.fromJson(fileReader, SpawnBoostConfig::class.java)
                        fileReader.close()
                    } catch (e: Exception) {
                        println("Error reading config file: " + e.localizedMessage)
                    }
                }

                val pw = PrintWriter(configFile)
                gson.toJson(config, pw)
                pw.close()

                return config
            }
        }
    }
}