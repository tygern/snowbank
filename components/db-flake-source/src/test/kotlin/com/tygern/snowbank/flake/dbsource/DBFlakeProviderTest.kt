package com.tygern.snowbank.flake.dbsource

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tygern.snowbank.flake.Flake
import io.damo.kspec.Spec
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import java.util.*

class DBFlakeProviderTest : Spec({

    val flakeRepository: FlakeRepository = mock()
    val flakeProvider = DBFlakeProvider(flakeRepository = flakeRepository)

    before {
        reset(flakeRepository)
    }

    describe("#getList") {
        test {
            val flake = Flake(id = 7, numberOfPoints = 15, pointy = true)
            val flakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

            whenever(flakeRepository.findAll()).thenReturn(Arrays.asList(flakeEntity))

            var result = flakeProvider.getList()

            assertThat(result.size, equalTo(1))
            assertThat(result[0], equalTo(flake))
        }
    }

    describe("#create") {
        test {
            val flakeToSave = Flake(id = null, numberOfPoints = 15, pointy = true)
            val flakeEntity = FlakeEntity(id = null, numberOfPoints = 15, pointy = true)

            val savedFlake = Flake(id = 7, numberOfPoints = 15, pointy = true)
            val savedFlakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

            whenever(flakeRepository.save(flakeEntity)).thenReturn(savedFlakeEntity)

            var result = flakeProvider.create(flakeToSave)

            assertThat(result, equalTo(savedFlake))

            verify(flakeRepository).save(flakeEntity)
        }
    }

    describe("#delete") {
        test {
            flakeProvider.delete(7)

            verify(flakeRepository).delete(7)
        }
    }

    describe("#update") {
        test {
            val flakeToSave = Flake(id = null, numberOfPoints = 15, pointy = true)
            val flakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

            val savedFlake = Flake(id = 7, numberOfPoints = 15, pointy = true)
            val savedFlakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

            whenever(flakeRepository.save(flakeEntity)).thenReturn(savedFlakeEntity)

            var result = flakeProvider.update(7, flakeToSave)

            assertThat(result, equalTo(savedFlake))

            verify(flakeRepository).save(flakeEntity)
        }
    }

    describe("#get") {
        test {
            val flake = Flake(id = 7, numberOfPoints = 15, pointy = true)
            val flakeEntity = FlakeEntity(id = 7, numberOfPoints = 15, pointy = true)

            whenever(flakeRepository.findOne(7)).thenReturn(flakeEntity)

            var result = flakeProvider.get(7)

            assertThat(result, equalTo(flake))
        }

        test("not found") {
            whenever(flakeRepository.findOne(7)).thenReturn(null)

            var result = flakeProvider.get(7)

            assertNull(result)
        }
    }
})