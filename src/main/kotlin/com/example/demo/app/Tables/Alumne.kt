package com.example.demo.app.Tables

import com.example.demo.app.Tables.Professor.primaryKey
import org.ktorm.schema.*

object Alumne : Table<Nothing>("alumnes") {
    val id= int("id").primaryKey()
    val nom = varchar("nom")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val cognoms = varchar("cognoms")
    val dni = varchar("dni")
    val data_naixement = date("data_naixement")
    val sexe = varchar("sexe")
    val telefon = int("telefon")
    val email = varchar("email")
    val deleted = boolean("deleted")
    val descripcio = varchar("descripcio")
}