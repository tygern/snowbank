package com.tygern.snowbank

import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FlakeApiTests : FlakeApiTestCase() {
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
