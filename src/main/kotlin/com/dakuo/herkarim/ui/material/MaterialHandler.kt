package com.dakuo.herkarim.ui.material

import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.function.Function


class MaterialHandler(val function: Function<Player,Material?>) {
    var priority:MaterialHandlerPriority = MaterialHandlerPriority.MONITOR
}