package me.kafae.rings.bin

import kotlin.math.floor

fun formatTime(t: Int): String {
    lateinit var ft: String
    val m: Int = floor(t / 60.0).toInt()
    val s: Int = t - m * 60
    ft = if (m > 0) {
        "${m}m ${s}s"
    } else {
        "${s}s"
    }

    return ft
}