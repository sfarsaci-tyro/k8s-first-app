package com.tyro.sfarsaci.experiments

import org.slf4j.LoggerFactory
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import kotlin.math.sqrt

@Path("/hello")
class GreetingResource {

    val log: org.slf4j.Logger = LoggerFactory.getLogger("GreetingResource")

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@QueryParam("userName") userName: String?): String {

        log.info("Computed: ${intenseComputation()}")
        return "G'day${userName?.prependIndent(" ") ?: ""}"
    }

    private fun intenseComputation(): Double {
        var temp: Double = 0.0;
        for (i in 1..1000000) {
            temp += sqrt(i + temp + (Math.random() * 1000))
        }
        return temp
    }

}