package me.kafae.rings.events

import me.kafae.rings.Main
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EntityDamagePlayerEvent: Listener {

    @EventHandler(priority = EventPriority.HIGH)
    private fun onPlayerDamagePlayer(e: EntityDamageByEntityEvent) {
        if (e.damager is Player && e.entity is Player) {
            val vic: Player = e.entity as Player
            val atk: Player = e.damager as Player
            if (vic.uniqueId.toString() in Main.dataMap[atk.uniqueId.toString()]!!.trusted) {
                e.isCancelled = true
            }
        } else if (e.entity is Player && e.damager is LivingEntity) {
            val vic: Player = e.entity as Player
            if (vic in Main.aeroguardList) {
                e.isCancelled = true
                (e.damager as LivingEntity).damage(e.damage)
            }
        }
    }

}