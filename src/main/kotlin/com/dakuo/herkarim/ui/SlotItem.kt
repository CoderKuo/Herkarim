package com.dakuo.herkarim.ui

import com.dakuo.herkarim.ui.material.SlotMaterial
import com.dakuo.herkarim.ui.meta.DefaultSlotMeta
import com.dakuo.herkarim.ui.meta.SlotMeta

open class SlotItem(val material:SlotMaterial,val data:Int, val name:String?, val lore:List<String>?) {
    var meta:SlotMeta = DefaultSlotMeta()

    open var isSkull = false
    open var skullData = ""
}