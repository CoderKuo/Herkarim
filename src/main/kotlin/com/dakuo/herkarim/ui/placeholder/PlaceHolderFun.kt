package com.dakuo.herkarim.ui.placeholder

import org.bukkit.entity.Player

class PlaceHolderFun(private val key:String,private val value:(Player)->String) {

    fun getKey():String{
        return key
    }

    fun getValue(player: Player):String{
        return value(player)
    }

}