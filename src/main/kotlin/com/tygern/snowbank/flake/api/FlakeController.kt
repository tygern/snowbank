package com.tygern.snowbank.flake.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class FlakeController @Autowired constructor(val flakeProvider: FlakeProvider)  {

    @RequestMapping("/flakes", method = arrayOf(RequestMethod.GET))
    fun getList() = flakeProvider.getFlakes()

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/flakes", method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody flake: Flake) = flakeProvider.create(flake)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/flakes/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id: Int) = flakeProvider.delete(id)
}
