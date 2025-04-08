package me.kafae.rings.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class Gui {

    abstract val owner: InventoryHolder?
    abstract val slots: Int
    abstract val title: String

    abstract val items: HashMap<Int, ItemStack>

    open val ui: Inventory = Bukkit.createInventory(owner, slots, title). apply {
        for ((k, v) in items) {
            setItem(k, v)
        }
    }

    open fun open(p: Player) {
        p.openInventory(ui)
    }

}