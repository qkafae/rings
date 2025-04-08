package me.kafae.rings.bin

import me.kafae.rings.Main.Companion.dataMap
import me.kafae.rings.rings.Module
import me.kafae.rings.rings.Ring
import org.bukkit.entity.Player
import kotlin.math.floor

fun updateCooldown1(p: Player) {
    val r: Ring = offHandCheck(p)?: return
    val pris: Double = dataMap[p.uniqueId.toString()]!!.pristine.toDouble()
    if (!isSame(r.getItem(), Module().getItem())) {
        dataMap[p.uniqueId.toString()]!!.cooldown1 = floor(r.corrAbility1!!.cooldown * (1.0 - (pris / 10.0)))
        dataMap[p.uniqueId.toString()]!!.lastUsedRing = r.id
    }
}

fun updateCooldown2(p: Player) {
    val r: Ring = offHandCheck(p)?: return
    val pris: Double = dataMap[p.uniqueId.toString()]!!.pristine.toDouble()
    if (!isSame(r.getItem(), Module().getItem())) {
        if (r.corrAbility2 != null && dataMap[p.uniqueId.toString()]!!.pristine >= 3) {
            dataMap[p.uniqueId.toString()]!!.cooldown2 = floor(r.corrAbility2!!.cooldown * (1.0 - (pris / 10.0)))
        } else {
            dataMap[p.uniqueId.toString()]!!.cooldown2 = -1.0
        }
        dataMap[p.uniqueId.toString()]!!.lastUsedRing = r.id
    }
}