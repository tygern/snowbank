package com.tygern.snowbank.flake.dbsource

import com.tygern.snowbank.flake.Flake
import com.tygern.snowbank.flake.FlakeProvider
import org.springframework.beans.factory.annotation.Autowired

class DBFlakeProvider @Autowired
constructor(private val flakeRepository: FlakeRepository) : FlakeProvider {

    override fun getFlakes(): List<Flake> = flakeRepository
            .findAll()
            .map({ toFlake(it) })

    private fun toFlake(entity: FlakeEntity) = Flake(
            id = entity.id.toInt(),
            numberOfPoints = entity.numberOfPoints.toInt(),
            pointy = entity.pointy
    )
}