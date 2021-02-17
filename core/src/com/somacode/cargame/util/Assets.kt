package com.somacode.cargame.util

import com.badlogic.gdx.assets.AssetManager

class Assets {

    private lateinit var instance: Assets

    private constructor()

    private object HOLDER {
        val INSTANCE = Assets()
    }

    companion object {
        val instance: Assets by lazy { HOLDER.INSTANCE }
    }

    private val assetManager = AssetManager()


    fun <T> load(assetPath: String, clazz: Class<T>): T {
        if (!assetManager.contains(assetPath)) {
            assetManager.load(assetPath, clazz)
            assetManager.finishLoading()
        }
        return assetManager.get(assetPath, clazz) as T
    }
}