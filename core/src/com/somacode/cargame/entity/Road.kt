package com.somacode.cargame.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.util.Entity


class Road(car: Car) : Entity() {
    var sprite: Sprite? = null
    var car: Car? = car

    var roads: FloatArray = FloatArray(7)
    var multiplier = 0

    init {
        sprite = Sprite(Texture("background.jpg"))
        multiplier = (roads.count() / 2f).toInt()
        for (i in 0 until roads.count()) {
            roads[i] = sprite!!.height * (-multiplier + i)
        }
    }

    override fun update(delta: Float) {
        if (car!!.y - roads[multiplier] > 0) {
            for (i in 0 until roads.count()) {
                roads[i] += sprite!!.height
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        for (i in 0 until roads.count()) {
            sprite!!.setPosition(0f, roads[i])
            sprite!!.draw(spriteBatch)
        }
    }
}