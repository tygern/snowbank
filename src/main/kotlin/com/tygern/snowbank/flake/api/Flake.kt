package com.tygern.snowbank.flake.api

data class Flake(
        val id: Int? = null,
        val numberOfPoints: Int = 0,
        val pointy: Boolean = false
)