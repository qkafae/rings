package me.kafae.rings.executors

import me.kafae.rings.Main
import me.kafae.rings.bin.ringCheck
import me.kafae.rings.rings.Module
import me.kafae.rings.rings.Ring
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AbilityExecutor: CommandExecutor {

    override fun onCommand(s: CommandSender, c: Command, l: String, args: Array<out String>): Boolean {
        if (s !is Player) {
            return true
        }
        val p: Player = s
        return if (args.isNotEmpty()) {
            when (args[0]) {
                "x" -> {
                    val r: Ring? = ringCheck(p)
                    if (r == null || r is Module) {
                        p.sendMessage("§4No ability can be used")
                        return true
                    }
                    if (Main.dataMap[p.uniqueId.toString()]!!.cooldown1 > 0) {
                        p.sendMessage("§4Ability on cooldown")
                        return true
                    }
                    r.corrAbility1!!.onUse(p)
                }
                "y" -> {
                    val r: Ring? = ringCheck(p)
                    if (r == null || r is Module) {
                        p.sendMessage("§4No ability can be used")
                        return true
                    }
                    if (Main.dataMap[p.uniqueId.toString()]!!.pristine < 3) {
                        p.sendMessage("§4Cannot use this ability")
                        return true
                    }
                    if (Main.dataMap[p.uniqueId.toString()]!!.cooldown2 > 0) {
                        p.sendMessage("§4Ability on cooldown")
                        return true
                    }
                    r.corrAbility2!!.onUse(p)
                }
                else -> p.sendMessage("§4Please specify correct ability /ability <ability>")
            }
            true
        } else {
            p.sendMessage("§4Please specify ability /ability <ability>")
            true
        }
    }

}