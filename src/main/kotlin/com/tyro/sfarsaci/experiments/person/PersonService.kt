package com.tyro.sfarsaci.experiments.person

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.ws.rs.NotFoundException

@ApplicationScoped
open class PersonServiceImpl : PersonService {
    @Inject
    private lateinit var em: EntityManager

    override fun getPerson(id: Long) = em.find(Person::class.java, id) ?: throw NotFoundException()

    @Transactional
    override fun createPerson(name: String) = em.merge(Person(name = name)).id!!
}

interface PersonService {
    fun createPerson(name: String): Long
    fun getPerson(id: Long): Person
}
