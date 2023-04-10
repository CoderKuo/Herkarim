package com.dakuo.herkarim.ui.inventory

import com.dakuo.herkarim.ui.SlotItem
import com.dakuo.herkarim.ui.material.MaterialHandler
import com.dakuo.herkarim.ui.material.SlotMaterial
import com.dakuo.herkarim.ui.meta.SlotMeta
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.function.Function

class InventorySlots(val index:String,val item:SlotItem?) {
    fun addMaterialHandler(func: (player: Player) -> Material?){
        item?.material?.addMaterialHandler(MaterialHandler(func))
    }
}