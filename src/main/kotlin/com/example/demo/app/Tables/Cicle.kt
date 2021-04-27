package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Cicle : Table<Nothing>("cicles") {
    val id_cicle = int("id_cicle").primaryKey()
    val id_familia = int("id_familia")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val nom_cicle = varchar("nom_cicle")
}