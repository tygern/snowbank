package com.tygern.snowbank.flake.dbsource

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
data class FlakeEntity(

        @Id
        @SequenceGenerator(name = "flake_entity_id_seq",
                sequenceName = "flake_entity_id_seq",
                allocationSize = 1)
        @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE,
                generator = "flake_entity_id_seq")
        var id: Int? = null,
        var numberOfPoints: Int = 0,
        var pointy: Boolean = false
)