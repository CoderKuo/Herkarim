package com.dakuo.herkarim.ui.meta

import org.bukkit.configuration.ConfigurationSection
import taboolib.common.LifeCycle
import taboolib.common.io.runningClasses
import taboolib.common.platform.Awake

object SlotMetaLoader {

    val types = HashMap<String,Class<SlotMeta>>()

    @Awake(LifeCycle.ENABLE)
    fun registerAll(){
        runningClasses.forEach {
            if (SlotMeta::class.java.isAssignableFrom(it) && it != SlotMeta::class.java){
                (it.getConstructor().newInstance() as? SlotMeta)?.register()
            }
        }
    }

    fun getInstance(key:String,config: ConfigurationSection):SlotMeta{
        if (!types.containsKey(key)){
            error("error meta")
        }
        val clazz = types[key]!!
        val newInstance = clazz.getConstructor().newInstance()
        clazz.declaredFields.filter { it.isAnnotationPresent(MetaPerm::class.java) }.forEach {
            val annotation = it.getAnnotation(MetaPerm::class.java)
            when (annotation.type) {
                MetaPermType.String -> {
                    it.set(newInstance,config.getString(annotation.name))
                }
                MetaPermType.Boolean->{
                    it.setBoolean(newInstance,config.getBoolean(annotation.name))
                }
                MetaPermType.Int->{
                    it.setInt(newInstance,config.getInt(annotation.name))
                }
                MetaPermType.StringList->{
                    it.set(newInstance,config.getStringList(annotation.name))
                }
                MetaPermType.Object->{
                    val newInstance1 = it.type.getConstructor(ConfigurationSection::class.java)
                        .newInstance(config.getConfigurationSection(annotation.name))
                    it.set(newInstance,newInstance1)
                }
            }
        }
        return newInstance
    }

}