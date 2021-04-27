package com.example.demo.app.Tables

import org.ktorm.schema.*

object Professor : Table<Nothing>("professor") {
    val id_professor = int("id_professor").primaryKey()
    val nom_professor = varchar("nom_professor")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val cognoms_professor = varchar("cognoms_professor")
    val dni_professor = varchar("dni_professor")
    val data_naixement = date("data_naixement")
    val sexe = varchar("sexe")
    val telefon = int("telefon")
    val pro_correu_electronic = varchar("pro_correu_electronic")
    val informe = int("informe")
    val administrador = boolean("administrador")
}