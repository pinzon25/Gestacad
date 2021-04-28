package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Moduls : Table<Nothing>("moduls") {
    val id = int("id").primaryKey()
    val id_cicle = int("id_cicle")
    val nom = varchar("nom")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val descripcio = varchar("descripcio")
}