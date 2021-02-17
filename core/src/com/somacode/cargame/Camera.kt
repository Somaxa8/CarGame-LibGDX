package com.somacode.cargame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.entity.Car
import com.somacode.cargame.util.Entity


class Camera(car: Car) : Entity() {

    private val OFFSET_Y = 150
    var camera: OrthographicCamera? = null
    var car: Car? = null
    var x = 0f
    var carX = 0f


    init {
        this.car = car
        val w = Gdx.graphics.width.toFloat()
        val h = Gdx.graphics.height.toFloat()
        camera = OrthographicCamera(w, h)
        camera!!.zoom = 0.6f
        x = car.x + car.sprite!!.width / 2f
    }


    override fun update(delta: Float) {
        carX = car!!.x + car!!.sprite!!.width / 2f

        if (carX > x) x += (carX - x) * 2 * delta
        if (carX < x) x -= (x - carX) * 2 * delta

        camera!!.position[x, car!!.y + OFFSET_Y + car!!.sprite!!.height / 2f] = 0f

        val zoom = car!!.acceleration * 0.02f

        camera!!.zoom = 0.8f + zoom
        camera!!.update()
    }

    override fun draw(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = camera!!.combined
    }
}