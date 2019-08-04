package com.tyro.sfarsaci.experiments.person

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
open class PersonServiceImpl() : PersonService {
    @Inject
    private lateinit var em: EntityManager

    @Transactional
    override fun createPerson(name: String) = Person(name = name)
            .apply {
                em.persist(this)
            }.id

    override fun getPerson(id: Long) = em.find(Person::class.java, id)!!
}


interface PersonService {
    fun createPerson(name: String): Long?
    fun getPerson(id: Long): Person
}
