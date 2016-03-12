package com.tygern.snowbank.flake.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
    fun get(@PathVariable id: Int) = flakeProvider.get(id)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id: Int) = flakeProvider.delete(id)
}
