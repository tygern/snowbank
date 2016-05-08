package com.tygern.snowbank

import com.jayway.jsonpath.JsonPath
import io.damo.kspec.Spec
import io.damo.kspec.spring.SpringSpecTreeRunner
import io.damo.kspec.spring.injectValue
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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
class FlakeApiTests : Spec({

    val port = injectValue("local.server.port", Int::class)
    val JSON = MediaType.parse("application/json; charset=utf-8");
    val client = OkHttpClient()

    test("POST /flakes") {
        var requestBody = "{\"numberOfPoints\": 14, \"pointy\": false}"
        var body = RequestBody.create(JSON, requestBody);

        val request = Request.Builder()
                .post(body)
                .url("http://localhost:$port/flakes")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(201));

        val json = JsonPath.parse(response.body().string());

        assertThat(json.read<Int>("$.id"), equalTo(3))
        assertThat(json.read<Int>("$.numberOfPoints"), equalTo(14))
        assertThat(json.read<Boolean>("$.pointy"), equalTo(false))
    }

    test("PUT /flakes/id") {
        var requestBody = "{\"numberOfPoints\": 33, \"pointy\": false}"
        var body = RequestBody.create(JSON, requestBody);

        val request = Request.Builder()
                .put(body)
                .url("http://localhost:$port/flakes/1")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(200));

        val json = JsonPath.parse(response.body().string());

        assertThat(json.read<Int>("$.id"), equalTo(1))
        assertThat(json.read<Int>("$.numberOfPoints"), equalTo(33))
        assertThat(json.read<Boolean>("$.pointy"), equalTo(false))
    }

    test("DELETE /flakes/id") {
        val request = Request.Builder()
                .delete()
                .url("http://localhost:$port/flakes/1")
                .build()

        val response = client.newCall(request).execute()

        assertThat(response.code(), equalTo(204));

        val getRequest = Request.Builder()
                .url("http://localhost:$port/flakes")
                .build()
        val getResponse = client.newCall(getRequest).execute()

        val json = JsonPath.parse(getResponse.body().string());
        assertThat(json.read<List<Any>>("$"), hasSize<Any>(1))
        assertThat(json.read<Int>("$[0].id"), equalTo(2))
    }
})
