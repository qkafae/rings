package me.kafae.rings.executors

import me.kafae.rings.bin.stringToRing
import me.kafae.rings.rings.Ring
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RingExecutor: CommandExecutor {

    override fun onCommand(s: CommandSender, c: Command, l: String, args: Array<out String>): Boolean {
        if (s is Player) {
            if (args.isNotEmpty()) {
                val ring: Ring? = stringToRing(args[0].lowercase() + "_ring")

                if (ring == null) {
                    s.sendMessage("§4Cannot find ring named ${args[0]}")
                    return true
                } else {
                    ring.giveItem(s.player!!)
                    return true
                }
            } else {
                s.sendMessage("§4Wrong usage: /ring <ring>")
                return true
            }
        } else {
            return true
        }
    }
}