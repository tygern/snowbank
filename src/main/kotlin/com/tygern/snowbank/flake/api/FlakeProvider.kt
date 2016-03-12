package com.tygern.snowbank.flake.api

interface FlakeProvider {
    fun getFlakes(): List<Flake>

    fun create(flake: Flake): Flake

    fun delete(id: Int)

    fun update(id: Int, flake: Flake): Flake
}