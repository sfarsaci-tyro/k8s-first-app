package com.tyro.sfarsaci.experiments.person

import javax.inject.Inject
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/person")
class PersonResource {

    @Inject
    private lateinit var personService: PersonService

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun createPerson(@QueryParam("userName") userName: String?) = personService.createPerson(userName!!)

}