package com.tygern.snowbank.flake.staticsource

import com.tygern.snowbank.flake.Flake
import com.tygern.snowbank.flake.FlakeProvider
import java.util.*

class StaticFlakeProvider : FlakeProvider {
    override fun getFlakes(): List<Flake> {
        return Arrays.asList(
                Flake(id = 1, numberOfPoints = 6, pointy = true),
                Flake(id = 2, numberOfPoints = 5, pointy = false)
        )
    }
}