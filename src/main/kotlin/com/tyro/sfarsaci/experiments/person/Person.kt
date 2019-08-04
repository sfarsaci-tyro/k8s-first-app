package com.tyro.sfarsaci.experiments.person

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
        var id: Long? = null,
        var name: String) {
}