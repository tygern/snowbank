package com.tygern.snowbank.flake.staticsource

import com.tygern.snowbank.flake.Flake
import com.tygern.snowbank.flake.FlakeProvider
import java.util.*

class StaticFlakeProvider : FlakeProvider {
    override fun get(id: Int): Flake = Flake(id = id, numberOfPoints = 46, pointy = false)

    override fun update(id: Int, flake: Flake): Flake = flake

    override fun delete(id: Int) {
    }

    override fun create(flake: Flake): Flake = flake

    override fun getList(): List<Flake> = Arrays.asList(
            Flake(id = 1, numberOfPoints = 6, pointy = true),
            Flake(id = 2, numberOfPoints = 5, pointy = false)
    )
}