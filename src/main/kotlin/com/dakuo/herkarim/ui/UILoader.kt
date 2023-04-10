package com.dakuo.herkarim.ui

import com.dakuo.herkarim.ui.inventory.HerKarimInventory
import com.dakuo.herkarim.ui.inventory.InventoryBuilder
import com.dakuo.herkarim.ui.inventory.toInventory
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import taboolib.platform.BukkitPlugin
import java.io.File


val loadedUI = mutableMapOf<String,InventoryBuilder>()

object UILoader {

    @Awake(LifeCycle.ENABLE)
    fun load(){
        val listFiles = File(BukkitPlugin.getInstance().dataFolder, "ui").listFiles { dir, name ->
            name.contains(".yml")
        }
        listFiles?.forEach {
            loadedUI[it.name.removeSuffix(".yml")] = it.toInventory()
            info("${it.name} 加载完成!")
        }
    }
}