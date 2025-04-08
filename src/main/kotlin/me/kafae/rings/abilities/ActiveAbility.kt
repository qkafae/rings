package me.kafae.rings.abilities

import org.bukkit.entity.Player

abstract class ActiveAbility {

    abstract val cooldown: Int
    abstract val displayName: String
    abstract val icon: String
    abstract val desc: Array<String>

    abstract fun onUse(p: Player)

}