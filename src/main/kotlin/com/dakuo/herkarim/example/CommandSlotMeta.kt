package com.dakuo.herkarim.example

import com.dakuo.herkarim.ui.meta.MetaPerm
import com.dakuo.herkarim.ui.meta.MetaPermType
import com.dakuo.herkarim.ui.meta.SlotMeta
import com.dakuo.herkarim.ui.placeholder.PlaceHolderFun
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class CommandSlotMeta:SlotMeta() {

    override val name: String = "cmd"

    @MetaPerm("command",MetaPermType.String)
    lateinit var command:String

    override fun getPlaceHolder(): List<PlaceHolderFun> {
        return listOf()
    }

    override fun click(player: Player): Boolean {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command)
        return false
    }

}