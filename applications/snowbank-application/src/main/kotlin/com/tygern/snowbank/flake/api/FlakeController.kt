package com.tygern.snowbank.flake.api

import com.tygern.snowbank.flake.Flake
import com.tygern.snowbank.flake.FlakeProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/flakes")
@RestController
class FlakeController @Autowired constructor(val flakeProvider: FlakeProvider)  {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getList() = flakeProvider.getList()

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody flake: Flake) = flakeProvider.create(flake)

    @RequestMapping("{id}", method = arrayOf(RequestMethod.PUT))
    fun update(@PathVariable id: Int, @RequestBody flake: Flake) = flakeProvider.update(id, flake)

    @RequestMapping("{id}", method = arrayOf(RequestMethod.GET))
    fun get(@PathVariable id: Int): ResponseEntity<Flake?> {
        var flake = flakeProvider.get(id)

        val statusCode = if (flake == null) NOT_FOUND else OK

        return ResponseEntity(flake, statusCode)
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id: Int) = flakeProvider.delete(id)
}
