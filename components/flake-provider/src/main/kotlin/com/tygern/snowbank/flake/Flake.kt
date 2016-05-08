package com.tygern.snowbank.flake

data class Flake(
        var id: Int? = null,
        val numberOfPoints: Int = 0,
        val pointy: Boolean = false
)