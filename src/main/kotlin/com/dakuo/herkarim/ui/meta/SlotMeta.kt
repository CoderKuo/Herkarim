package com.dakuo.herkarim.ui.meta

import com.dakuo.herkarim.ui.SlotItem
import com.dakuo.herkarim.ui.placeholder.PlaceHolderFun
import org.bukkit.configuration.Configuration
import org.bukkit.entity.Player
import taboolib.platform.util.ItemBuilder

abstract class SlotMeta {
    abstract val name:String
    var clazz:Class<SlotMeta> = this.javaClass

    abstract fun getPlaceHolder():List<PlaceHolderFun>

    /**
     * 点击事件
    * 返回值: 是否需要重新打开ui
    */
    abstract fun click(player:Player):Boolean

    /**
     * 点击是否取消事件
     *
     */
    open var isCancelled = true

    open fun execute(item: SlotItem,itemBuilder: ItemBuilder,player: Player){

    }

    fun register(){
        SlotMetaLoader.types[name] = clazz
    }
}