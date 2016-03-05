package com.tygern.snowbank

import com.github.kittinunf.fuel.httpGet
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SnowbankApplication::class))
@WebIntegrationTest(randomPort = true)
class SnowbankApplicationTests @Autowired
constructor() {

    @Value("\${local.server.port}")
    val port: Int? = null;

    @Test
    fun getFlakes() {
        val data = "http://localhost:$port/flakes"
                .httpGet()
                .responseString()
                .third

        assertThat(data, equalTo("[{\"numberOfPoints\":6},{\"numberOfPoints\":5}]"))
    }

}
