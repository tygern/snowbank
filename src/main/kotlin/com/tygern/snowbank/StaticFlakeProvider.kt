package com.tygern.snowbank

class StaticFlakeProvider : FlakeProvider {
    override fun getFlakes(): Array<Flake> {
        return arrayOf(Flake(6), Flake(5))
    }
}