package me.kafae.rings.bin

import me.kafae.rings.rings.*

fun stringToRing(s: String): Ring? {
    return when (s) {
        "module_ring" -> Module()
        "aetherial_ring" -> Aetherial()
        else -> null
    }
}