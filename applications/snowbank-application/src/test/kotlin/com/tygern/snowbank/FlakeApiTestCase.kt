package com.tygern.snowbank

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SnowbankApplication::class))
@WebAppConfiguration
@SqlGroup(
        Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = arrayOf("classpath:dbSetup.sql")),
        Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = arrayOf("classpath:dbTeardown.sql"))
)
abstract class FlakeApiTestCase {
    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}