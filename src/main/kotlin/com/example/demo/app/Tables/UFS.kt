package com.example.demo.app.Tables

import com.example.demo.app.Tables.Moduls.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UFS : Table<Nothing>("ufs") {
    val id = int("id").primaryKey()
    val id_modul = int("id_modul")
    val nom = varchar("nom")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val descripcio = varchar("descripcio")
}
