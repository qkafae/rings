package me.kafae.rings.bin
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

fun directDamage(atk: Player, vic: LivingEntity, d: Double) {
    vic.damage(0.1, atk)
    vic.health = (vic.health - d).coerceAtLeast(0.0)
}