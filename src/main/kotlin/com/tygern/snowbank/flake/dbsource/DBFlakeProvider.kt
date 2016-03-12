package com.tygern.snowbank.flake.dbsource

import com.tygern.snowbank.flake.api.Flake
import com.tygern.snowbank.flake.api.FlakeProvider
import org.springframework.beans.factory.annotation.Autowired

class DBFlakeProvider @Autowired
constructor(private val flakeRepository: FlakeRepository) : FlakeProvider {
    override fun create(flake: Flake): Flake {
        val entityToSave = toEntity(flake)

        val savedEntity = flakeRepository.save(entityToSave)

        return toFlake(savedEntity)
    }

    override fun getFlakes(): List<Flake> = flakeRepository
            .findAll()
            .map({ toFlake(it) })

    private fun toFlake(entity: FlakeEntity) = Flake(
            id = entity.id,
            numberOfPoints = entity.numberOfPoints.toInt(),
            pointy = entity.pointy
    )

    private fun toEntity(flake: Flake) = FlakeEntity(
            id = flake.id,
            numberOfPoints = flake.numberOfPoints.toInt(),
            pointy = flake.pointy
    )
}