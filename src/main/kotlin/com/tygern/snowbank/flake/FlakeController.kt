package com.tygern.snowbank.flake

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FlakeController @Autowired constructor(val flakeProvider: FlakeProvider)  {

    @RequestMapping("/flakes")
    fun getList() = flakeProvider.getFlakes()
}
