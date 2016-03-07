package com.tygern.snowbank.flake.dbsource

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class FlakeEntity(
        @Id
        @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Int = 0,
        var numberOfPoints: Int = 0,
        var pointy: Boolean = false
)