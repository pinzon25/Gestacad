package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Notes: Table<Nothing>("notes") {
    val id = int("id")
    val id_alumne = int("id_alumne")
    val id_uf = int("id_uf")
    val pes = float("pes")
    val descripcio = varchar("descripcio")
}