package com.tygern.snowbank

import org.hamcrest.Matchers
import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class FlakeApiRetrevalTest : FlakeApiTestCase() {
    @Test
    fun testgetList() {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/flakes"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.`is`(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfPoints", Matchers.`is`(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pointy", Matchers.`is`(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.`is`(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfPoints", Matchers.`is`(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].pointy", Matchers.`is`(false)))
    }

    @Test
    fun testget() {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/flakes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.`is`(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfPoints", Matchers.`is`(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pointy", Matchers.`is`(true)))
    }

    @Test
    fun testgetNotFound() {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/flakes/10"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}