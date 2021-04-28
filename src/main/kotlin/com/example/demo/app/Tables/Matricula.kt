package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Matricula : Table<Nothing>("matricula")  {
    val id = int("id").primaryKey()
    val id_alumne = int("id_alumne")
    val id_uf = int("id_uf")
    val curs = varchar("curs")
}