package com.tyro.sfarsaci.experiments.person

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
open class PersonServiceImpl() : PersonService {
    @Inject
    private var em: EntityManager? = null

    @Transactional
    override fun createPerson(name: String) = Person(name = name)
            .apply {
                em!!.persist(this)
            }.id
}


interface PersonService {
    fun createPerson(name: String): Long?
}
