package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.directDamage
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown2
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Particle.DustTransition
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

import kotlin.math.cos
import kotlin.math.sin

class ScorchingBlood: ActiveAbility() {

    var dura: Int = 300
    val r: Double = 3.0
    val dmg: Double = 4.0
    override val cooldown: Int = 120
    override val icon: String = "§4♨"
    override val displayName: String = "§4§l$icon sᴄᴏʀᴄʜɪɴɢ ʙʟᴏᴏᴅ"
    override val desc: Array<String> = arrayOf(
        "§7Summons a blood-made pillar that follows",
        "§7the player, damaging all non-trusted",
        "§7players in it with direct damage",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: ${dura / 20}s",
        "§7 > Radius: ${r.toInt()} blocks",
        "§7 > Damage: ${dmg.toInt()} HP"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown2(p)

        val pt: Particle = Particle.DUST_COLOR_TRANSITION
        val opt: DustTransition = DustTransition(Color.fromRGB(112, 4, 4), Color.fromRGB(138, 3, 3), 1.0f)

        object: BukkitRunnable() {
            var ticks: Int = 0

            override fun run() {
                val ctr: Location = p.location.clone()
                if (ticks++ > dura) {
                    this.cancel()
                    p.sendMessage("§f$displayName§f has expired!")
                    return
                }

                for (l in 0..(2*5)) {
                    val y: Double = ctr.y + (l / 2.0)

                    for (ang in 0..359 step 8) {
                        val rad: Double = Math.toRadians(ang.toDouble())
                        val x: Double = cos(rad) * r
                        val z: Double = sin(rad) * r

                        p.world.spawnParticle(
                            pt,
                            ctr.x + x, y, ctr.z + z,
                            1, 0.0, 0.0, 0.0, 0.0,
                            opt,
                            true
                        )
                    }
                }

                if (ticks % 20 == 0) {
                    p.world.getNearbyEntities(p.location, 3.0, 5.0, 3.0).forEach { e->
                        if (e is LivingEntity && e != p) {
                            if (e is Player && e.uniqueId.toString() in Main.dataMap[p.uniqueId.toString()]!!.trusted) return
                            directDamage(p, e, dmg)
                        }
                    }
                }
            }
        }.runTaskTimer(Main.self, 0L, 1L)
    }

}