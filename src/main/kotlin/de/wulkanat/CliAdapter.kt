package de.wulkanat

import de.wulkanat.cli.discordUsageEmbed
import de.wulkanat.cli.makeCli
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CliAdapter : ListenerAdapter() {
    val cli = makeCli<MessageReceivedEvent>(prefix = "!") {
        command name "createRole" with { required string argument } does "Create a new Role" through
                {required, _, event -> createRole(event, required.first()) }
        command name "removeRole" with { required string argument } does "Remove a Role" through
                {required, _, event -> removeRole(event, required.first()) }
        command name "toggleFeature" with { required literal argument with "on" or "off" } does "Toggle Feature" through
                {required, _, event -> toggleFeature(event, required.first() == "on")}
        command name "launch" with { optional existence "override" } does "Launch a Thing" through
                {_, optional, event -> launch(event, optional["override"] != null)}
    }

    private fun createRole(event: MessageReceivedEvent, name: String) {
        event.message.channel.sendMessage("Pretend I actually created the role `$name`.").queue()
    }

    private fun removeRole(event: MessageReceivedEvent, name: String) {
        event.message.channel.sendMessage("Pretend I actually removed the role `$name`.").queue()
    }

    private fun toggleFeature(event: MessageReceivedEvent, toggle: Boolean) {
        event.message.channel.sendMessage("Pretend I actually toggled to `$toggle`.").queue()
    }

    private fun launch(event: MessageReceivedEvent, override: Boolean) {
        event.message.channel.sendMessage("Launched. Override is $override.").queue()
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message.contentRaw
        // Only accept admin requests
        if (event.message.member?.hasPermission(Permission.ADMINISTRATOR) != true) {
            return
        }

        cli.parse(msg, event,
            commandMisuse = { command, message ->
                event.message.channel.sendMessage(command.discordUsageEmbed(message)).queue()
            },
            helpMessage = {
                event.message.channel.sendMessage(it.discordUsageEmbed()).queue()
            }
        )
    }
}