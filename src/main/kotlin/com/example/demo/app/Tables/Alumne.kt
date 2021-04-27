package com.example.demo.app.Tables

import org.ktorm.schema.*

object Alumne : Table<Nothing>("alumne") {
    val id_alumne = int("id_alumne").primaryKey()
    val nom_alumne = varchar("nom_alumne")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val cognoms_alumne = varchar("cognoms_alumne")
    val dni_alumne = varchar("dni_alumne")
    val data_naixement = date("data_naixement")
    val sexe = varchar("sexe")
    val telefon = int("telefon")
    val correu_electronic_al = varchar("correu_electronic_al")
    val id_grup = int("id_grup")
}