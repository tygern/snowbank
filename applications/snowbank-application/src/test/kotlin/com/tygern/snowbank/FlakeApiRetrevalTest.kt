package com.tygern.snowbank

import com.jayway.jsonpath.JsonPath.parse
import io.damo.kspec.Spec
import io.damo.kspec.spring.SpringSpecTreeRunner
import io.damo.kspec.spring.injectValue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup


@RunWith(SpringSpecTreeRunner::class)
@SpringApplicationConfiguration(SnowbankApplication::class)
@WebIntegrationTest("server.port:0")
@SqlGroup(
        Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = arrayOf("classpath:dbSetup.sql")),
        Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = arrayOf("classpath:dbTeardown.sql"))
)
class FlakeApiRetrevalTest : Spec({

    val port = injectValue("local.server.port", Int::class)
    val client = OkHttpClient()

    test("GET /flakes") {
        val request = Request.Builder()
                .url("http://localhost:$port/flakes")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(200));

        val json = parse(response.body().string());

        assertThat(json.read<List<Any>>("$"), hasSize<Any>(2))
        assertThat(json.read<Int>("$[0].id"), equalTo(1))
        assertThat(json.read<Int>("$[0].numberOfPoints"), equalTo(6))
        assertThat(json.read<Boolean>("$[0].pointy"), equalTo(true))
        assertThat(json.read<Int>("$[1].id"), equalTo(2))
        assertThat(json.read<Int>("$[1].numberOfPoints"), equalTo(5))
        assertThat(json.read<Boolean>("$[1].pointy"), equalTo(false))
    }

    test("GET /flakes/:id") {
        val request = Request.Builder()
                .url("http://localhost:$port/flakes/1")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(200));

        val json = parse(response.body().string());

        assertThat(json.read<Int>("$.id"), equalTo(1))
        assertThat(json.read<Int>("$.numberOfPoints"), equalTo(6))
        assertThat(json.read<Boolean>("$.pointy"), equalTo(true))
    }

    test("GET /flakes/:id not found") {
        val request = Request.Builder()
                .url("http://localhost:$port/flakes/100")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(404));
    }
})