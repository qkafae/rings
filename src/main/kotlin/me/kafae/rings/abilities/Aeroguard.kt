package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown1
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.cos
import kotlin.math.sin

class Aeroguard: ActiveAbility() {

    override val cooldown: Int = 5 // 120
    override val icon: String = "§b⛊"
    override val displayName: String = "§b§l$icon ᴀᴇʀᴏɢᴜᴀʀᴅ"
    override val desc: Array<String> = arrayOf(
        "§7creates a wind barrier around the",
        "§7player that deflects all incoming",
        "§7damage back to its source.",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: 15s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown1(p)
        Main.aeroguardList.addLast(p)

        object: BukkitRunnable() {
            val dura: Long = ((15 + Main.dataMap[p.uniqueId.toString()]!!.pristine) * 20).toLong()
            val particle: Particle = Particle.DUST_COLOR_TRANSITION
            val size: Float = 2.5f
            val r: Double = 1.0
            val y: Double = 0.9
            val ppt: Int = 1
            var ticks: Int = 0
            val opt: Particle.DustTransition = Particle.DustTransition(Color.fromRGB(255, 255, 255), Color.fromRGB(200, 230, 255), size)
            val rps: Double = 0.4
            val radpt: Double = 2 * Math.PI * rps / 20
            var cang: Double = 0.0
            override fun run() {
                if (ticks > dura) {
                    this.cancel()
                    Main.aeroguardList.remove(p)
                    p.sendMessage("$displayName§f has expired!")
                    return
                }

                repeat(ppt) { i ->
                    val ang: Double = cang + (i * (2 * Math.PI / ppt))
                    val x = r * cos(ang)
                    val z = r * sin(ang)

                    p.world.spawnParticle(
                        particle,
                        p.location.add(x, y, z),
                        1,
                        0.0, 0.0, 0.0,
                        0.0,
                        opt,
                        true
                    )
                }

                cang += radpt
                if (cang >= 2 * Math.PI) {
                    cang -= 2 * Math.PI
                }
                ticks++
            }
        }.runTaskTimer(Main.self, 0L, 1L)
    }

}