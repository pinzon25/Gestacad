package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Informe : Table<Nothing>("informe") {
    val id_informe = int("id_informe").primaryKey()
    val assumpte = varchar("assumpte")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
    val id_professor_informe = int("id_professor_informe")
    val id_alumne_informe = int("id_alumne_informe")
}