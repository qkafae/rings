package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown1
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Particle.DustTransition
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.cos
import kotlin.math.sin

class DivineStrength: ActiveAbility() {

    var dura: Int = 200
    override val cooldown: Int = 90
    override val icon: String = "§4☠"
    override val displayName: String = "§4§l$icon ᴅɪᴠɪɴᴇ sᴛʀᴇɴɢᴛʜ"
    override val desc: Array<String> = arrayOf(
        "§7Grants player strength from divine beings, ",
        "§7giving them Strength II",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: ${dura / 20}s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown1(p)
        dura += Main.dataMap[p.uniqueId.toString()]!!.pristine * 20
        p.addPotionEffect(PotionEffect(PotionEffectType.STRENGTH, dura, 1, false, false))

        val pt: Int = 10
        val r: Double = 1.0
        val r2: Double = 0.25
        val pt2: Particle = Particle.DUST_COLOR_TRANSITION
        val opt: DustTransition = DustTransition(Color.fromRGB(112, 4, 4), Color.fromRGB(138, 3, 3), 0.5f)

        object: BukkitRunnable() {
            var ticks: Int = 0
            override fun run() {
                val loc: Location = p.location.clone().add(0.0, 1.2, 0.0)
                val rloc: Location = p.location.clone().add(0.0, 2.0, 0.0)
                if (ticks++ > dura) {
                    this.cancel()
                    p.sendMessage("§f$displayName§f has expired!")
                    return
                }

                for (i in 0 until pt) {
                    val ang: Double = i * (2 * Math.PI / pt)
                    val x: Double = cos(ang) * r
                    val z: Double = sin(ang) * r
                    val pLoc: Location = loc.clone().add(x, 0.0, z)

                    p.world.spawnParticle(
                        Particle.ASH,
                        pLoc,
                        10,
                        0.0, 0.0, 0.0,
                        0.02
                    )
                }

                for (ang in 0..359 step 15) {
                    val rad = Math.toRadians(ang.toDouble() + ticks * 0.5)
                    val x = cos(rad) * r2
                    val z = sin(rad) * r2

                    p.world.spawnParticle(
                        pt2,
                        rloc.x + x,
                        rloc.y,
                        rloc.z + z,
                        1, 0.0, 0.0, 0.0, 0.0,
                        opt
                    )
                }
            }
        }.runTaskTimer(Main.self, 0L, 1L)
    }

}