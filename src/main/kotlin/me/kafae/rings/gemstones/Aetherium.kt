package me.kafae.rings.gemstones

import me.kafae.rings.rings.Aetherial
import me.kafae.rings.rings.Ring
import org.bukkit.NamespacedKey

class Aetherium: Gemstone() {

    override val id: String = "aetherium"
    override val displayName: String = "§bᴀᴇᴛʜᴇʀɪᴜᴍ §f☁"
    override val customModel: NamespacedKey = NamespacedKey("rings", "aetherium_model")
    override val corrRing: Ring = Aetherial()
    override val lore: MutableList<String> = mutableListOf(
        "§7Used to obtain: ${Aetherial().displayName}"
    )

}