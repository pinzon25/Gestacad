package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Assignatura : Table<Nothing>("assignatures") {
    val id_assignatura = int("id_assignatura").primaryKey()
    val nom_assignatura = varchar("nom_assignatura")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
}