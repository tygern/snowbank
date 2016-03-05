package com.tygern.snowbank.flake

class StaticFlakeProvider : FlakeProvider {
    override fun getFlakes(): Array<Flake> {
        return arrayOf(
                Flake(numberOfPoints = 6, pointy = true),
                Flake(numberOfPoints = 5, pointy = false)
        )
    }
}