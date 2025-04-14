package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown2
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.cos
import kotlin.math.sin

class AetherHowl: ActiveAbility() {

    val dura: Long = 300L
    override val cooldown: Int = 150
    override val icon: String = "§b⛊"
    override val displayName: String = "§b§l$icon ᴀᴇᴛʜᴇʀ's ʜᴏᴡʟ"
    override val desc: Array<String> = arrayOf(
        "§7creates a aetherial wolf around the",
        "§7player that deflects all incoming",
        "§7damage back to its source. The player",
        "§7will also glow",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: ${(dura / 20).toInt()}s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown2(p)
        Main.aeroguardList.addLast(p)
        p.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, dura.toInt(), 1, false, false))

        object: BukkitRunnable() {
            val particle: Particle = Particle.DUST_COLOR_TRANSITION
            val size: Float = 2.0f
            val r: Double = 1.0
            val y: Double = 0.9
            val ppt: Int = 1
            var ticks: Int = 0
            val opt: Particle.DustTransition = Particle.DustTransition(Color.fromRGB(255, 255, 255), Color.fromRGB(200, 230, 255), size)
            val rps: Double = 0.8
            val radpt: Double = 2 * Math.PI * rps / 20
            var cang: Double = 0.0
            override fun run() {
                if (ticks++ > dura) {
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
            }
        }.runTaskTimer(Main.self, 0L, 1L)
    }

}