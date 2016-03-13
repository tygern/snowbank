package com.tygern.snowbank.flake.dbsource

import com.tygern.snowbank.flake.api.Flake

fun toFlake(entity: FlakeEntity) = Flake(
        id = entity.id,
        numberOfPoints = entity.numberOfPoints.toInt(),
        pointy = entity.pointy
)

fun toFlakeEntity(flake: Flake) = FlakeEntity(
        id = flake.id,
        numberOfPoints = flake.numberOfPoints.toInt(),
        pointy = flake.pointy
)

