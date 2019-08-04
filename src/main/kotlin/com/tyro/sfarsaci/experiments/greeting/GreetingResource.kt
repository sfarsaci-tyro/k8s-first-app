package com.tyro.sfarsaci.experiments.greeting


import org.slf4j.LoggerFactory
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

    private val log: org.slf4j.Logger = LoggerFactory.getLogger("GreetingResource")

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@QueryParam("userName") userName: String?): String {
        return "G'day${userName?.prependIndent(" ") ?: ""}"
    }

    @GET
    @Path("/busy")
    @Produces(MediaType.TEXT_PLAIN)
    fun busy() = Thread.sleep(1000).run{ "G'day" }
}