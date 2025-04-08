package me.kafae.rings.gemstones

import me.kafae.rings.rings.Ring
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

abstract class Gemstone {

    abstract val id: String
    abstract val displayName: String
    abstract val customModel: NamespacedKey
    abstract val corrRing: Ring
    open val baseItem: Material = Material.PAPER
    open val lore: MutableList<String> = mutableListOf("")

    open fun getItem(): ItemStack {
        val shard: ItemStack = ItemStack(baseItem)
        val im: ItemMeta = shard.itemMeta!!

        im.setDisplayName(displayName)
        lore.addFirst("")
        lore.addLast("")
        lore.addLast("§7ID: $id")
        im.lore = lore.toList()
        im.itemModel = customModel

        shard.itemMeta = im

        return shard
    }

    open fun giveItem(p: Player) {
        p.inventory.addItem(getItem())
    }

}