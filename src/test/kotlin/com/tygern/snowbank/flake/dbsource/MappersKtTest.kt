package com.tygern.snowbank.flake.dbsource

import com.tygern.snowbank.flake.api.Flake
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class MappersKtTest {

    lateinit var flake: Flake;
    lateinit var flakeEntity: FlakeEntity;

    @Before
    fun setUp() {
        flake = Flake(id = 8, numberOfPoints = 32, pointy = false)
        flakeEntity = FlakeEntity(id = 8, numberOfPoints = 32, pointy = false)
    }

    @Test
    fun testToFlakeEntity() {
        assertThat(flakeEntity, equalTo(toFlakeEntity(flake)))
    }

    @Test
    fun testToFlake() {
        assertThat(flake, equalTo(toFlake(flakeEntity)))
    }
}