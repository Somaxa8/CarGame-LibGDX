package com.somacode.cargame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.somacode.cargame.entity.World
import com.somacode.cargame.util.Entity

class Game : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var world: Entity? = null

    override fun create() {
        batch = SpriteBatch()
        world = World.instance
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val delta = Gdx.graphics.deltaTime
        world!!.update(delta)
        batch!!.begin()
        world!!.draw(batch!!)
        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
    }
}