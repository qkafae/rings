package me.kafae.rings.bin

import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector

private fun raycast(p: Player, dir: Vector, maxDis: Double): LivingEntity? {
    val sLoc: Location = p.eyeLocation
    val eLoc: Location = sLoc.clone().add(dir.multiply(maxDis))

    for (e in p.world.entities) {
        if (e is LivingEntity && e != p) {
            val eBoundingBox: BoundingBox = e.boundingBox
            if (eBoundingBox.overlaps(BoundingBox.of(sLoc, eLoc))) {
                return e
            }
        }
    }
    return null
}
