//package com.tyro.sfarsaci.experiments
//
//import io.quarkus.test.junit.QuarkusTest
//import io.restassured.RestAssured.given
//import jdk.nashorn.internal.ir.annotations.Ignore
//import org.hamcrest.CoreMatchers.`is`
//import org.junit.jupiter.api.Test
//
//@Ignore
//@QuarkusTest
//open class GreetingResourceTest {
//    private val salutation = "G'day"
//
//    @Test
//    fun testHelloEndpoint() {
//        given()
//                .`when`().get("/hello")
//                .then()
//                .statusCode(200)
//                .body(`is`(salutation))
//    }
//
//    @Test
//    fun `Given a name it should say hello to that name`() {
//        given()
//                .`when`().get("/hello?userName=Test User")
//                .then()
//                .statusCode(200)
//                .body(`is`("$salutation Test User"))
//    }
//
//}