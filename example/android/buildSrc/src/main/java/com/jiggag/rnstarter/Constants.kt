package com.jiggag.rnstarter

import java.io.FileInputStream
import java.util.Properties

private fun getProperty(key: String): String {
    val properties = Properties()
    properties.load(FileInputStream("gradle.properties"))

    return properties.getProperty(key)
}

object Constants {
    const val KOTLIN_VERSION = "1.4.32"
    const val BUILD_TOOLS_VERSION = "30.0.2"
    const val MIN_SDK_VERSION = 21
    const val COMPILE_SDK_VERSION = 31
    const val TARGET_SDK_VERSION = 31
    const val NDK_VERSION = "21.4.7075529"
    val FLIPPER_VERSION = getProperty("FLIPPER_VERSION")
    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"
}
