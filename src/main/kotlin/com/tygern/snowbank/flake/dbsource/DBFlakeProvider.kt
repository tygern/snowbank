package com.tygern.snowbank.flake.dbsource

import com.tygern.snowbank.flake.api.Flake
import com.tygern.snowbank.flake.api.FlakeProvider
import org.springframework.beans.factory.annotation.Autowired

class DBFlakeProvider @Autowired
constructor(private val flakeRepository: FlakeRepository) : FlakeProvider {
    override fun get(id: Int): Flake = toFlake(flakeRepository.findOne(id))

    override fun update(id: Int, flake: Flake): Flake {
        flake.id = id

        return save(flake)
    }

    override fun delete(id: Int) = flakeRepository.delete(id)

    override fun create(flake: Flake): Flake = save(flake)

    override fun getList(): List<Flake> = flakeRepository
            .findAll()
            .map({ toFlake(it) })

    private fun save(flake: Flake): Flake {
        val entityToSave = toFlakeEntity(flake)

        val savedEntity = flakeRepository.save(entityToSave)

        return toFlake(savedEntity)
    }
}