package me.kafae.rings.bin

import org.bukkit.inventory.ItemStack

fun isSame(x: ItemStack, y: ItemStack): Boolean {
    return x.hasItemMeta() && y.hasItemMeta() && x.itemMeta!!.hasItemModel() && y.itemMeta!!.hasItemModel() && x.itemMeta!!.itemModel == y.itemMeta!!.itemModel
}