package me.kafae.rings.rings

import me.kafae.rings.abilities.*
import org.bukkit.NamespacedKey

class Aetherial: Ring() {

    override val id: String = "aetherial_ring"
    override val displayName: String = "§bᴀᴇᴛʜᴇʀɪᴀʟ ʀɪɴɢ"
    override val customModel: NamespacedKey = NamespacedKey("rings", "aetherial_ring_model")
    override val corrAbility1: ActiveAbility = Surf()
    override val corrAbility2: ActiveAbility = AetherHowl()
    override val corrPAbility: PasssiveAbility = FeelTheBreeze()

}