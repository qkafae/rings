package me.kafae.rings.abilities

import org.bukkit.entity.Player

abstract class PasssiveAbility {

    abstract val displayName: String
    abstract val desc: Array<String>

    abstract fun enable(p: Player)

}