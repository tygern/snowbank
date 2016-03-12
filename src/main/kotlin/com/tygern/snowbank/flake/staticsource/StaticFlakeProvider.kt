package com.tygern.snowbank.flake.staticsource

import com.tygern.snowbank.flake.api.Flake
import com.tygern.snowbank.flake.api.FlakeProvider
import java.util.*

class StaticFlakeProvider : FlakeProvider {
    override fun update(id: Int, flake: Flake): Flake = flake

    override fun delete(id: Int) {
    }

    override fun create(flake: Flake): Flake = flake

    override fun getList(): List<Flake> = Arrays.asList(
            Flake(id = 1, numberOfPoints = 6, pointy = true),
            Flake(id = 2, numberOfPoints = 5, pointy = false)
    )
}