package me.kafae.rings.events

import me.kafae.rings.Main
import me.kafae.rings.datastore.DataStore
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent

class PLeaveEvent: Listener {

    private val ds: DataStore = DataStore()

    @EventHandler(priority = EventPriority.NORMAL)
    private fun onPlayerQuit(e: PlayerQuitEvent) {
        val p: Player = e.player
        val uid: String = p.uniqueId.toString()
        ds.save(uid)
        Main.log.info("Saved data for ${p.name}")
    }
}