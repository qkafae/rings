package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown1
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class Surf: ActiveAbility() {

    override val cooldown: Int = 30
    override val icon: String = "§b↗"
    override val displayName: String = "§b§l$icon sᴜʀғ"
    override val desc: Array<String> = arrayOf(
        "§7Dash in the way that the player is",
        "§7looking at.",
        "§7 > Cooldown: ${formatTime(cooldown)}",
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown1(p)

        p.velocity = p.location.direction.normalize().multiply(4)
        if (p.isOnGround) {
            p.velocity.y = 0.5
        }

        object: BukkitRunnable() {
            override fun run() {
                if (p.isOnGround || p.isInWater) {
                    this.cancel()
                    return
                }
                val size: Float = 1.5f
                val opt: Particle.DustTransition = Particle.DustTransition(Color.fromRGB(255, 255, 255), Color.fromRGB(200, 230, 255), size)

                p.world.spawnParticle(
                    Particle.DUST_COLOR_TRANSITION,
                    p.location.clone().add(0.0, 0.2, 0.0),
                    6,
                    0.2, 0.0, 0.2,
                    0.0,
                    opt
                )
            }
        }.runTaskTimer(Main.self, 0L, 1L)
    }

}