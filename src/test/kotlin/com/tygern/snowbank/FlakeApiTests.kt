package com.tygern.snowbank

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SnowbankApplication::class))
@WebAppConfiguration
@SqlGroup(
        Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = arrayOf("classpath:dbSetup.sql")),
        Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = arrayOf("classpath:dbTeardown.sql"))
)
class FlakeApiTests {

    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    fun testgetFlakes() {
        mockMvc
                .perform(get("/flakes"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].id", `is`(1)))
                .andExpect(jsonPath("$[0].numberOfPoints", `is`(6)))
                .andExpect(jsonPath("$[0].pointy", `is`(true)))
                .andExpect(jsonPath("$[1].id", `is`(2)))
                .andExpect(jsonPath("$[1].numberOfPoints", `is`(5)))
                .andExpect(jsonPath("$[1].pointy", `is`(false)))
    }

    @Test
    fun testcreateFlake() {
        mockMvc
                .perform(post("/flakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfPoints\": 14, \"pointy\": false}"))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", `is`(3)))
                .andExpect(jsonPath("$.numberOfPoints", `is`(14)))
                .andExpect(jsonPath("$.pointy", `is`(false)))

        mockMvc
                .perform(get("/flakes"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(3)))
                .andExpect(jsonPath("$[2].id", `is`(3)))
                .andExpect(jsonPath("$[2].numberOfPoints", `is`(14)))
                .andExpect(jsonPath("$[2].pointy", `is`(false)))
    }

    @Test
    fun testupdateFlake() {
        mockMvc
                .perform(put("/flakes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfPoints\": 33, \"pointy\": false}"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.numberOfPoints", `is`(33)))
                .andExpect(jsonPath("$.pointy", `is`(false)))

        mockMvc
                .perform(get("/flakes"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[1].id", `is`(1)))
                .andExpect(jsonPath("$[1].numberOfPoints", `is`(33)))
                .andExpect(jsonPath("$[1].pointy", `is`(false)))
    }

    @Test
    fun testdeleteFlake() {
        mockMvc
                .perform(delete("/flakes/1"))
                .andExpect(status().isNoContent)

        mockMvc
                .perform(get("/flakes"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(1)))
                .andExpect(jsonPath("$[0].id", `is`(2)))
                .andExpect(jsonPath("$[0].numberOfPoints", `is`(5)))
                .andExpect(jsonPath("$[0].pointy", `is`(false)))
    }
}
