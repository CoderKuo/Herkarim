package com.dakuo.herkarim.ui.inventory

import com.dakuo.herkarim.ui.SlotItem
import com.dakuo.herkarim.ui.material.SlotMaterial
import com.dakuo.herkarim.ui.meta.SlotMeta
import com.dakuo.herkarim.ui.meta.SlotMetaLoader
import org.bukkit.Material
import org.bukkit.configuration.Configuration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.platform.util.ItemBuilder
import taboolib.platform.util.buildItem
import java.io.File

fun File.toInventory():InventoryBuilder{
    return InventoryBuilder(this)
}

class InventoryBuilder(file:File) {

    val config:Configuration = YamlConfiguration.loadConfiguration(file)

    val title = config.getString("title","")

    val rows = config.getInt("rows",0)

    var slots:MutableList<InventorySlots>

    val clickMap = mutableMapOf<Int,SlotMeta>()

    init {
        val list = mutableListOf<InventorySlots>()
        config.getConfigurationSection("slots")?.let { it ->
            val keys = it.getKeys(false)
            keys.forEach { key->
                val meta = it.getConfigurationSection("$key.meta")?.let {
                    val type = it.getString("type")!!
                    SlotMetaLoader.getInstance(type,it)
                }
                val item = it.getConfigurationSection("$key.item")?.let {
                    val string = it.getString("material")!!
                    val slotMaterial = SlotMaterial(Material.matchMaterial(string) ?: Material.STONE)
                    val slotItem =
                        SlotItem(slotMaterial, it.getInt("data"), it.getString("name"), it.getStringList("lore"))
                    if (string == "skull"){
                        slotItem.isSkull = true
                        slotItem.skullData = it.getString("skull_data")!!
                    }
                    slotItem
                }
                if (meta != null) {
                    item?.meta = meta
                }
                list.add(InventorySlots(key,item))
            }
        }
        slots = list
    }

    fun modifyInventorySlots(list:(MutableList<InventorySlots>)->Unit):InventoryBuilder{
        slots.apply(list)
        return this
    }

    fun getItems(player: Player):HashMap<Int, ItemStack>{
        val resultMap = mutableMapOf<Int,ItemStack>()
        slots.forEach {
            val convertIntArray = it.index.convertIntArray()
            it.item?.let {
                val slotItemToBukkitItem = SlotItemToBukkitItem(it, player)
                convertIntArray.forEach { index->
                    resultMap[index] = slotItemToBukkitItem
                    clickMap[index] = it.meta
                }
            }
        }
        return HashMap(resultMap)
    }

    fun SlotItemToBukkitItem(item: SlotItem,player: Player):ItemStack{
        val buildItem = buildItem(item.material.get(player)){
            if (item.isSkull){
                this.skullTexture = ItemBuilder.SkullTexture(item.skullData)
            }

            this.name = item.name
            item.lore?.let { this.lore.addAll(it) }

            val placeholder = item.meta.getPlaceHolder()
            placeholder.forEach { placeHolderFun ->
                val value = placeHolderFun.getValue(player)
                this.name = this.name?.replace("%${placeHolderFun.getKey()}%",value)
                this.lore.replaceAll {
                    it.replace("%${placeHolderFun.getKey()}%", value)
                }
            }

            item.meta.execute(item,this,player)
            this.lore.replaceAll { it.replace("&","§") }
            this.name = this.name?.replace("&","§")

            build()
        }
        return buildItem
    }

    fun build(player: Player):HerKarimInventory{
        return HerKarimInventory(this,player)
    }

    fun String.convertIntArray():IntArray{
        var split = this.split(";")
        if (split.first().isEmpty()){
            split = split.subList(1,split.size)
        }
        if (split.last().isEmpty()){
            split = split.subList(0,split.size-1)
        }
        val intArray = IntArray(split.size)
        split.forEachIndexed { index,value->
            intArray[index] = value.toInt()
        }
        return intArray
    }
}