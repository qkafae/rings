package me.kafae.rings.abilities

import me.kafae.rings.Main
import me.kafae.rings.bin.formatTime
import me.kafae.rings.bin.updateCooldown2
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.entity.WindCharge
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.util.Vector
import kotlin.properties.Delegates

class Surf: ActiveAbility() {

    companion object {
        var taskID by Delegates.notNull<Int>()
    }

    override val cooldown: Int = 5 // 240
    override val icon: String = "§b☁"
    override val displayName: String = "§b§l$icon sᴜʀғ"
    override val desc: Array<String> = arrayOf(
        "§7summons a rideable wind charge to",
        "§7mount. Any non-trusted players who,",
        "§7approach a 2 bock radius of the",
        "§7wind charge will receive damage.",
        "§7 > Cooldown: ${formatTime(cooldown)}",
        "§7 > Duration: 30s"
    )

    override fun onUse(p: Player) {
        p.sendMessage("§fUsed ability $displayName§f!")
        updateCooldown2(p)

        Main.surfList.addLast(p)

        val windCharge = p.world.spawn(p.location, WindCharge::class.java).apply {
            isSilent = true
            isInvulnerable = true
            setGravity(false)
            setMetadata("stationary", FixedMetadataValue(Main.self, true))

            velocity = Vector(0, 0, 0)
        }

        windCharge.addPassenger(p)

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.self, {
            if (windCharge.isValid) {
                windCharge.eject()
                windCharge.remove()
                Main.surfList.remove(p)
                p.sendMessage("$displayName§f has expired!")
                p.world.spawnParticle(Particle.CLOUD, windCharge.location, 15, 0.5, 0.2, 0.5, 0.05)
            }
        }, 600L)
    }

}