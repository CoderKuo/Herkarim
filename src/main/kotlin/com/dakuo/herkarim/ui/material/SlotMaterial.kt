package com.dakuo.herkarim.ui.material

import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.function.Function

class SlotMaterial(var material: Material) {

    val handler:MutableList<MaterialHandler> = mutableListOf()

    fun addMaterialHandler(handler:MaterialHandler){
        this.handler.add(handler)
    }

    fun get(player: Player):Material{
        handler.sortBy { it.priority.index }
        handler.forEach {
            material = it.function.apply(player) ?: material
        }
        return material
    }
}