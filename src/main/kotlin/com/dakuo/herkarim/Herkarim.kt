package com.dakuo.herkarim

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.platform.BukkitPlugin

object Herkarim : Plugin() {

    override fun onEnable() {
        BukkitPlugin.getInstance().saveResource("example.yml",false)
        info("Successfully running HerKarimExamplePlugin!")
    }
}