package com.dakuo.herkarim.ui.inventory

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import taboolib.module.ui.ClickEvent
import taboolib.module.ui.buildMenu
import taboolib.module.ui.type.Basic

class HerKarimInventory(builder: InventoryBuilder,val player: Player) {

    val bukkitInventory:Inventory

    var isCancelled = true

    lateinit var origin:Basic

    init {
        bukkitInventory = buildMenu<Basic> {
            origin = this
            this.title = builder.title ?: " "
            this.rows(builder.rows)
            builder.getItems(player).forEach { t, u ->
                this.set(t,u)
            }
            onClick {
                if (builder.clickMap.containsKey(it.rawSlot)){
                    val click = builder.clickMap[it.rawSlot]!!.click(player)
                    if (click){
                        builder.build(player).open()
                        it.isCancelled = builder.clickMap[it.rawSlot]!!.isCancelled
                        return@onClick
                    }
                }
                it.isCancelled = isCancelled
            }
        }
    }

    fun open(){
        player.openInventory(bukkitInventory)
    }

}