package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text
import org.ktorm.schema.varchar

object Correu : Table<Nothing>("correu") {
    val id_correu = int("id_correu").primaryKey()
    val id_proffesor_mail = int("id_proffesor_mail")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val id_alumne_mail = int("id_alumne_mail")
    val contingut = text("contingut")
}