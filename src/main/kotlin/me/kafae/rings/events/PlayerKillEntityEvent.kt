package me.kafae.rings.events

import me.kafae.rings.bin.ringCheck
import me.kafae.rings.rings.Harvester
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerKillEntityEvent: Listener {

    @EventHandler(priority = EventPriority.HIGH)
    private fun onPlayerKillEntity(e: EntityDeathEvent) {
        val atk = e.entity.killer ?: return
        if (ringCheck(atk) is Harvester) {
            atk.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 25, 4, false, false))
        }
    }

}