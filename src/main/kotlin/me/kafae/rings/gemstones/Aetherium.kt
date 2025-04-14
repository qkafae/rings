package me.kafae.rings.gemstones

import me.kafae.rings.rings.Aetherial
import me.kafae.rings.rings.Ring
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe

class Aetherium: Gemstone() {

    override val id: String = "aetherium"
    override val displayName: String = "§bᴀᴇᴛʜᴇʀɪᴜᴍ §f☁"
    override val customModel: NamespacedKey = NamespacedKey("rings", "aetherium_model")
    override val corrRing: Ring = Aetherial()
    override val lore: MutableList<String> = mutableListOf(
        "§7Used to obtain: ${Aetherial().displayName}"
    )
    override val recipe: ShapedRecipe = ShapedRecipe(
        NamespacedKey("rings", "aetherium_recipe"),
        getItem()
    ).apply {
        shape("wtw", "fdf", "wfw")
        setIngredient('w', Material.WIND_CHARGE)
        setIngredient('t', Material.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE)
        setIngredient('f', Material.FEATHER)
        setIngredient('d', Material.DIAMOND)
    }

}