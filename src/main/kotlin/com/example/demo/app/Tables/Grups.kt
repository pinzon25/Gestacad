package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Grups : Table<Nothing>("grups") {
    val id_grup = int("id_grup").primaryKey()
    val nom_grup = varchar("nom_grup")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val id_cicle = int("id_cicle")
}