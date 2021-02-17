package com.somacode.cargame.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.util.Entity


class Car : Entity() {

    private val ACCELERATION = 5f
    private val MAX_ACCELERATION = 11.5f
    private val MIN_ACCELERATION = 0f

    var sprite: Sprite? = null
    var x = 245f
    var y = 20f
    var acceleration = 0f

    private val MAX_DIRT_TIMER = 0.1f
    var dirtTimer = MAX_DIRT_TIMER


    init {
        sprite = Sprite(Texture("car.png"))
    }

    private fun checkDirt(delta: Float) {
        val dirtTime = delta * acceleration * 2
        dirtTimer -= if (dirtTime <= 0) delta else dirtTime
        if (dirtTimer <= 0) {
//            println("añadiendo")
            dirtTimer = MAX_DIRT_TIMER
            World.instance.entities.add(Dirt.newObject(x + 19, y))
            World.instance.entities.add(Dirt.newObject(x + 2, y))
        }
    }

    override fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            acceleration += ACCELERATION * delta;
            if (acceleration > MAX_ACCELERATION) acceleration = MAX_ACCELERATION;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            acceleration -= ACCELERATION * delta;
            if (acceleration < MIN_ACCELERATION) acceleration = MIN_ACCELERATION;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= delta * (acceleration + 1) * 20;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += delta * (acceleration + 1) * 20;
        }

        y += (200 * delta) + (acceleration - 2.5f);

        checkDirt(delta)
    }

    override fun draw(spriteBatch: SpriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }

}