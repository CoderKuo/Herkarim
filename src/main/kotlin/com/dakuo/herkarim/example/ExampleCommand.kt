package com.dakuo.herkarim.example

import com.dakuo.herkarim.ui.UILoader
import com.dakuo.herkarim.ui.loadedUI
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.platform.type.BukkitPlayer

@CommandHeader("exampleui")
object ExampleCommand {

    @CommandBody
    val main = mainCommand {
        execute<ProxyPlayer>{ sender, context, argument ->
            val player = (sender as BukkitPlayer).player
            ExampleInventory().openInventory(player)
        }
    }

    @CommandBody
    val open = subCommand {
        dynamic {
            suggestion<ProxyPlayer>{ _,_->
                loadedUI.map { it.key }
            }
            execute<ProxyPlayer>{ sender, context, argument ->
                val player = (sender as BukkitPlayer).player
                loadedUI[argument]!!.build(player)
            }
        }
    }
}