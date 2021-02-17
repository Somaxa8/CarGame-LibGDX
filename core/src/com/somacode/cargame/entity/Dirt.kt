package com.somacode.cargame.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.util.Assets
import com.somacode.cargame.util.Entity
import java.util.*


class Dirt(x: Float, y: Float) : Entity() {
    private val MAX_TIME_LIFE = 3f
    var sprite: Sprite? = null
    var x = 0f
    var y: Float = 0f
    var timeLife = 0f
    var alpha = 0f

    companion object {

        private val pool = ArrayList<Dirt>()

        fun newObject(x: Float, y: Float): Dirt {
            if (pool.isNotEmpty()) {
                val dirt = pool[0]
                pool.removeAt(0)
                dirt.init(x, y)
                return dirt
            }
            return Dirt(x, y)
        }
    }

    private fun destroy() {
        pool.add(this)
        World.instance.entities.remove(this)
    }

    init {
        sprite = Sprite(Assets.instance.load("dirt.png", Texture::class.java))
        init(x, y)
    }

    private fun init(x: Float, y: Float) {
        timeLife = MAX_TIME_LIFE
        alpha = 0.6f
        this.x = x
        this.y = y
        sprite!!.setPosition(x, y)
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite!!.setAlpha(alpha)
        println("dirt")
        sprite!!.draw(spriteBatch)
    }

    override fun update(delta: Float) {
        timeLife -= delta
        alpha = timeLife / MAX_TIME_LIFE - 0.5f
        if (timeLife <= 0) {
            destroy()
        }
    }
}