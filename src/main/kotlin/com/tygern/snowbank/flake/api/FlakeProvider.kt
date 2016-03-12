package com.tygern.snowbank.flake.api

interface FlakeProvider {
    fun getFlakes(): List<Flake>

    fun create(flake: Flake): Flake
}