package com.tyro.sfarsaci.experiments.person

import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/person")
class PersonResource {

    @Inject
    private lateinit var personService: PersonService

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getPerson(@PathParam("id") id: Long) : Person = personService.getPerson(id)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun createPerson(@QueryParam("userName") userName: String) = personService.createPerson(userName)

}