package com.dakuo.herkarim.ui.meta

import com.dakuo.herkarim.ui.placeholder.PlaceHolderFun
import org.bukkit.entity.Player

class DefaultSlotMeta:SlotMeta() {
    override val name = "default"


    override fun getPlaceHolder(): List<PlaceHolderFun> {
        return listOf()
    }

    override fun click(player: Player): Boolean {
        return false
    }

}