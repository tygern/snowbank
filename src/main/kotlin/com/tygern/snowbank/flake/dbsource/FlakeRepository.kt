package com.tygern.snowbank.flake.dbsource

import org.springframework.data.repository.CrudRepository

interface FlakeRepository : CrudRepository<FlakeEntity, Int> {
}