package com.jarvis.anime.model

abstract class BaseNameObject(
    var name: Translation? = null,
) : BaseObject() {

    constructor(baseNameObject: BaseNameObject) : this(
        name = baseNameObject.name
    )

    fun getNameForLocale(): String { return name?.getValue() ?: "" }
}