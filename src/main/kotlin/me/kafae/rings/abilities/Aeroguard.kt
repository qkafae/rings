package me.kafae.rings.abilities

import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown1
import org.bukkit.entity.Player

class Aeroguard: ActiveAbility() {

    override val cooldown: Int = 180
    override val icon: String = "§b⛊"
    override val displayName: String = "§b§l$icon ᴀᴇʀᴏɢᴜᴀʀᴅ"
    override val desc: Array<String> = arrayOf(
        "§7creates a wind barrier around the",
        "§7player that deflects all incoming",
        "§7damage back to its source.",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: 15s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown1(p)
    }

}