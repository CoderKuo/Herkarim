package com.dakuo.herkarim.ui.meta

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MetaPerm(val name:String,val type:MetaPermType = MetaPermType.String)

enum class MetaPermType{
    String,Int,StringList,Boolean,Object
}