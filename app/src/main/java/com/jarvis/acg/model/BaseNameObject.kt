package com.jarvis.acg.model

abstract class BaseNameObject(
    var name: Translation? = null,
) : BaseObject() {
    fun getNameForLocale(): String { return name?.getValue() ?: "" }
}