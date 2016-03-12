package com.tygern.snowbank.flake.api

data class Flake(
        var id: Int? = null,
        val numberOfPoints: Int = 0,
        val pointy: Boolean = false
)