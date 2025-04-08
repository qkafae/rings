package me.kafae.rings.bin

import me.kafae.rings.gemstones.Aetherium
import me.kafae.rings.gemstones.Gemstone

fun stringToGem(s: String): Gemstone? {
    return when (s) {
        "aetherium" -> Aetherium()
        else -> null
    }
}