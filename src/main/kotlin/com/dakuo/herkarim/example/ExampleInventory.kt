package com.dakuo.herkarim.example

import com.dakuo.herkarim.ui.inventory.toInventory
import org.bukkit.Material
import org.bukkit.entity.Player
import taboolib.platform.BukkitPlugin
import java.io.File

class ExampleInventory {
    fun openInventory(player:Player){
        File(BukkitPlugin.getInstance().dataFolder,"example.yml").toInventory().build(player).open()
    }
}