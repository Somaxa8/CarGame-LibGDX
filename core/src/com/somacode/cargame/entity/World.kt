package com.somacode.cargame.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.Camera
import com.somacode.cargame.util.Entity

class World : Entity() {

    var entities: MutableList<Entity> = mutableListOf()

    private object HOLDER {
        val INSTANCE = World()
    }

    companion object {
        val instance: World by lazy { HOLDER.INSTANCE }
    }

    init {
        val carEntity = Car()
        val camera = Camera(carEntity)
        val roadEntity = Road(carEntity)
        entities.add(camera)
        entities.add(roadEntity)
        entities.add(carEntity)
    }

    override fun update(delta: Float) {
        for (entity in entities) {
            entity.update(delta)
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
//        println(entities)
        for (entity in entities) {
            entity.draw(spriteBatch)
        }
    }

}