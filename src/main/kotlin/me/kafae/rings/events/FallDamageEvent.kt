package me.kafae.rings.events

import me.kafae.rings.bin.ringCheck
import me.kafae.rings.rings.Aetherial
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class FallDamageEvent: Listener {

    @EventHandler(priority = EventPriority.HIGH)
    private fun onEntityDamage(e: EntityDamageEvent) {
        if (e.entity is Player && ringCheck(e.entity as Player) is Aetherial && (e.cause == EntityDamageEvent.DamageCause.FALL || e.cause == EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {
            e.isCancelled = true
        }
    }

}