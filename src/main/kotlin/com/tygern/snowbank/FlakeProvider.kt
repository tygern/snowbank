package com.tygern.snowbank

interface FlakeProvider {
    fun getFlakes() : Array<Flake>
}