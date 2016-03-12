package com.tygern.snowbank.flake.dbsource

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tygern.snowbank.flake.api.Flake
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

class DBFlakeProviderTest {

    lateinit var flakeRepository: FlakeRepository
    lateinit var flakeProvider: DBFlakeProvider

    @Before
    fun setUp() {
        flakeRepository = mock()

        flakeProvider = DBFlakeProvider(flakeRepository = flakeRepository)
    }

    @Test
    fun testGetFlakes() {
        val flake = Flake(id = 7, numberOfPoints = 15, pointy = true)
        val flakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

        whenever(flakeRepository.findAll()).thenReturn(Arrays.asList(flakeEntity))

        var result = flakeProvider.getFlakes()

        assertThat(result.size, equalTo(1))
        assertThat(result[0], equalTo(flake))
    }

    @Test
    fun testCreate() {
        val flakeToSave = Flake(numberOfPoints = 15, pointy = true)
        val flakeEntity = FlakeEntity(numberOfPoints = 15, pointy = true)

        val savedFlake = Flake(id = 7, numberOfPoints = 15, pointy = true)
        val savedFlakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

        whenever(flakeRepository.save(flakeEntity)).thenReturn(savedFlakeEntity)

        var result = flakeProvider.create(flakeToSave)

        assertThat(result, equalTo(savedFlake))

        verify(flakeRepository).save(flakeEntity)
    }
}