package me.kafae.rings.rings

import me.kafae.rings.abilities.ActiveAbility
import me.kafae.rings.abilities.PasssiveAbility
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

abstract class Ring {

    abstract val id: String
    abstract val displayName: String
    abstract val customModel: NamespacedKey
    open val corrAbility1: ActiveAbility? = null
    open val corrAbility2: ActiveAbility? = null
    open val corrPAbility: PasssiveAbility? = null
    open val baseItem: Material = Material.PAPER

    open fun getItem(): ItemStack {
        val ring: ItemStack = ItemStack(baseItem)
        val im: ItemMeta = ring.itemMeta!!

        im.setDisplayName(displayName)

        val lore: MutableList<String> = mutableListOf("")
        if (corrAbility1 != null) {
            lore.addLast("§6ᴀʙɪʟɪᴛʏ: " + corrAbility1!!.displayName)
            for (i in corrAbility1!!.desc) {
                lore.addLast(i)
            }
        }
        if (corrAbility2 != null) {
            lore.addLast("")
            lore.addLast("§6ᴀʙɪʟɪᴛʏ: " + corrAbility2!!.displayName + " §4(3+ ᴘʀɪsᴛɪɴᴇ)")
            for (i in corrAbility2!!.desc) {
                lore.addLast(i)
            }
        }
        if (corrPAbility != null) {
            lore.addLast("")
            lore.addLast("§6ᴘᴀssɪᴠᴇ: " + corrPAbility!!.displayName)
            for (i in corrPAbility!!.desc) {
                lore.addLast(i)
            }
        }
        lore.addLast("")
        lore.addLast("§7ID: $id")

        im.lore = lore.toList()
        im.itemModel = customModel

        ring.itemMeta = im

        return ring
    }

    open fun giveItem(p: Player) {
        p.inventory.setItemInOffHand(getItem())
    }

}