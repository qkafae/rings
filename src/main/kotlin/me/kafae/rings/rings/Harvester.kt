package me.kafae.rings.rings

import me.kafae.rings.abilities.*
import org.bukkit.NamespacedKey

class Harvester: Ring() {

    override val id: String = "harvester_ring"
    override val displayName: String = "§4ʜᴀʀᴠᴇsᴛᴇʀ ʀɪɴɢ"
    override val customModel: NamespacedKey = NamespacedKey("rings", "harvester_ring_model")
    override val corrAbility1: ActiveAbility = DivineStrength()
    override val corrAbility2: ActiveAbility = ScorchingBlood()
    override val corrPAbility: PasssiveAbility = Devour()

}