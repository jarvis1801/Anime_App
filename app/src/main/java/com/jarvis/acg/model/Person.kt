package com.jarvis.acg.model

abstract class Person(
    var info: Translation? = null,
    var thumbnail: ArrayList<String?>? = null
): BaseNameObject()