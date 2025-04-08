package me.kafae.rings.abilities

import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown2
import org.bukkit.entity.Player

class Surf: ActiveAbility() {

    override val cooldown: Int = 180
    override val icon: String = "§b☁"
    override val displayName: String = "§b§l$icon sᴜʀғ"
    override val desc: Array<String> = arrayOf(
        "§7summons a rideable wind charge to",
        "§7mount. Any non-trusted players who,",
        "§7approach a 2 bock radius of the",
        "§7wind charge will receive damage.",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: 30s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown2(p)
    }

}