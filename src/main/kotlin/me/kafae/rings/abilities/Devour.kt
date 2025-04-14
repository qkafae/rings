package me.kafae.rings.abilities

import org.bukkit.entity.Player

class Devour: PasssiveAbility() {

    override val displayName: String = "§4❣ ᴅᴇᴠᴏᴜʀ"
    override val desc: Array<String> = arrayOf(
        "§7Upon killing a living entity, the player",
        "§7will receive Regeneration V for 5 seconds"
    )

    override fun enable(p: Player) {

    }

}