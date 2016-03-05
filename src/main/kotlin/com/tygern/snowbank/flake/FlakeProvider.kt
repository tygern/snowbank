package com.tygern.snowbank.flake

interface FlakeProvider {
    fun getFlakes() : Array<Flake>
}