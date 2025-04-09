package me.kafae.rings.events

import me.kafae.rings.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDismountEvent

class PlayerDismountEvent: Listener {

    @EventHandler(priority = EventPriority.HIGH)
    private fun onEntityDismount(e: EntityDismountEvent) {
        if (e.entity in Main.surfList) {
            e.isCancelled = true
        }
    }

}