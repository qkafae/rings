package me.kafae.rings.bin

import me.kafae.rings.rings.Aetherial
import me.kafae.rings.rings.*
import org.bukkit.entity.Player

private val rings: Array<Ring> = arrayOf(
    Module(),
    Aetherial()
)

fun offHandCheck(p: Player): Ring? {
    for (r in rings) {
        if (isSame(p.inventory.itemInOffHand, r.getItem())) {
            return r
        }
    }

    return null
}