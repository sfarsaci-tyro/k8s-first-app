package com.tyro.sfarsaci.experiments.person


import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
open class PersonResourceTest {

    @Test
    fun `Should create a person by name and return the id`() {
        val response = given()
                .`when`().post("/person?userName=test")
                .then()
                .statusCode(200)

        val id = response.extract().body().`as`(Long::class.java)

        val person = given()
                .`when`().get("/person/$id")
                .then()
                .statusCode(200)
                .extract().body().`as`(Person::class.java)

        assert(person == Person(id, "test"))
    }
}