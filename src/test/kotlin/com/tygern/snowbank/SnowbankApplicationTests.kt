package com.tygern.snowbank

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SnowbankApplication::class))
@WebAppConfiguration
class SnowbankApplicationTests {

    var mockMvc: MockMvc? = null

    @Autowired
    val context: WebApplicationContext? = null

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    fun getFlakes() {
        mockMvc!!
                .perform(get("/flakes"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"numberOfPoints\":6},{\"numberOfPoints\":5}]"))
    }

}
