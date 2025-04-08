package me.kafae.rings.events

import me.kafae.rings.Main
import me.kafae.rings.datastore.DataStore
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PJoinEvent: Listener {

    private val ds: DataStore = DataStore()

    @EventHandler(priority = EventPriority.NORMAL)
    private fun onPlayerJoin(e: PlayerJoinEvent) {
        val p: Player = e.player
        val uid: String = p.uniqueId.toString()
        Main.dataMap[uid] = ds.load(uid)
        Main.log.info("Loaded data for ${p.name}")
    }

}