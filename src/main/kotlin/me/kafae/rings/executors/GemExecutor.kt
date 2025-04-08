package me.kafae.rings.executors

import me.kafae.rings.bin.stringToGem
import me.kafae.rings.gemstones.Gemstone
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GemExecutor: CommandExecutor {

    override fun onCommand(s: CommandSender, c: Command, l: String, args: Array<out String>): Boolean {
        if (s is Player) {
            if (args.isNotEmpty()) {
                val gem: Gemstone? = stringToGem(args[0].lowercase())

                if (gem == null) {
                    s.sendMessage("§4Cannot find gemstone named ${args[0]}")
                    return true
                } else {
                    gem.giveItem(s.player!!)
                    return true
                }
            } else {
                s.sendMessage("§4Wrong usage: /gem <gem>")
                return true
            }
        } else {
            return true
        }
    }
}