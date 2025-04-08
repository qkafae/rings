package me.kafae.rings.rings

import org.bukkit.NamespacedKey

class Module: Ring() {

    override val id: String = "module_ring"
    override val displayName: String = "§fʀɪɴɢ ᴍᴏᴅᴜʟᴇ"
    override val customModel: NamespacedKey = NamespacedKey("rings", "module_ring_model")

}