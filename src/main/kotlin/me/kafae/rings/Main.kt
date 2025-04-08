package me.kafae.rings

import me.kafae.rings.bin.*
import me.kafae.rings.datastore.DataStore
import me.kafae.rings.datastore.DataStore.PlayerData
import me.kafae.rings.events.*
import me.kafae.rings.executors.*
import me.kafae.rings.rings.Module
import me.kafae.rings.rings.Ring
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.nio.file.Files
import java.util.logging.Logger
import kotlin.math.floor

class Main : JavaPlugin() {

    private val ds: DataStore = DataStore()

    companion object {
        val dataMap: MutableMap<String, PlayerData> = mutableMapOf()
        lateinit var log: Logger
        val aeroguardList: MutableList<Player> = mutableListOf()
    }

    override fun onEnable() {
        log = this.logger

        logger.info("Setting up events & listeners")
        server.pluginManager.registerEvents(PJoinEvent(), this)
        server.pluginManager.registerEvents(PLeaveEvent(), this)
        server.pluginManager.registerEvents(FallDamageEvent(), this)
        logger.info("Finished setting up events & listeners")

        logger.info("Rolling in all commands")
        this.getCommand("ring")?.setExecutor(RingExecutor())
        this.getCommand("gem")?.setExecutor(GemExecutor())
        this.getCommand("ability")?.setExecutor(AbilityExecutor())
        logger.info("All commands are rolled in")

        logger.info("Using magic to create directories")
        Files.createDirectories(ds.dir)

        logger.info("Rings are ready to rock and roll!")

        mainLoop()
        logger.info("Main loop is running")
    }

    override fun onDisable() {
        logger.info("Saving all player data")
        ds.saveAll()
        logger.info("Saved all player data")
        logger.info("Goodbye from Rings, until we meet again!")
    }

    private fun mainLoop() {
        object: BukkitRunnable() {
            override fun run() {
                Bukkit.getOnlinePlayers().forEach { p: Player ->
                    val r: Ring = offHandCheck(p)?: return
                    if (isSame(r.getItem(), Module().getItem())) {
                        dataMap[p.uniqueId.toString()]!!.cooldown1 = -1.0
                        dataMap[p.uniqueId.toString()]!!.cooldown2 = -1.0
                        dataMap[p.uniqueId.toString()]!!.lastUsedRing = "module_ring"
                        return
                    }
                    if (dataMap[p.uniqueId.toString()]!!.pristine < -4) {
                        return
                    }
                    if (r.corrPAbility != null) {
                        r.corrPAbility!!.enable(p)
                    }
                    try {
                        if (r.id != dataMap[p.uniqueId.toString()]!!.lastUsedRing) {
                            updateCooldown1(p)
                            updateCooldown2(p)
                        } else {
                            if (dataMap[p.uniqueId.toString()]!!.cooldown1 > 0 ) {
                                dataMap[p.uniqueId.toString()]!!.cooldown1 -= 0.5
                            }
                            if (dataMap[p.uniqueId.toString()]!!.cooldown2 > 0) {
                                dataMap[p.uniqueId.toString()]!!.cooldown2 -= 0.5
                            }
                        }
                        var msg: String = ""
                        msg += r.corrAbility1!!.icon + "§f : "
                        msg += if (dataMap[p.uniqueId.toString()]!!.cooldown1 == 0.0) {
                                "§aReady"
                            } else {
                                "§f" + formatTime(floor(dataMap[p.uniqueId.toString()]!!.cooldown1).toInt()) //will format later
                            }
                        if (r.corrAbility2 != null && dataMap[p.uniqueId.toString()]!!.pristine >= 3) {
                            msg += "§f | " + r.corrAbility2!!.icon + "§f : "
                            msg += if (dataMap[p.uniqueId.toString()]!!.cooldown2 == 0.0) {
                                "§aReady"
                            } else {
                                "§f" + formatTime(floor(dataMap[p.uniqueId.toString()]!!.cooldown2).toInt()) //will format later
                            }
                        }
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(msg))
                    } catch (e: NullPointerException) {
                        logger.warning("NullPointerException occurred in main loop!")
                    }
                }
            }
        }.runTaskTimer(this, 0L, 10L)
    }
}
