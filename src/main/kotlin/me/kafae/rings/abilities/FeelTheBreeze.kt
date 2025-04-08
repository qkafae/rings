package me.kafae.rings.abilities

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class FeelTheBreeze: PasssiveAbility() {

    override val displayName: String = "§b༄ ғᴇᴇʟ ᴛʜᴇ ʙʀᴇᴇᴢᴇ"
    override val desc: Array<String> = arrayOf(
        "§7Grants player Speed II and resistance",
        "§7against fall damage"
    )

    override fun enable(p: Player) {
        p.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 40, 1, false, false))
    }

}