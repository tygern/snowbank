package com.tygern.snowbank.flake

interface FlakeProvider {
    fun getList(): List<Flake>

    fun create(flake: Flake): Flake

    fun delete(id: Int)

    fun update(id: Int, flake: Flake): Flake

    fun get(id: Int): Flake?
}