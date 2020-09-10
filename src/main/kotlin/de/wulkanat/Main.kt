package de.wulkanat

import de.wulkanat.files.Config
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

fun main() {
    val builder = JDABuilder.createLight(Config.token, GatewayIntent.GUILD_MESSAGES)
        .setActivity(Activity.playing(Config.status))
        .addEventListeners(CliAdapter())
        .build()
        .awaitReady()
}