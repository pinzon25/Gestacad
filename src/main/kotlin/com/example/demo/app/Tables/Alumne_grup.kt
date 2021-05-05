package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int

object Alumne_grup : Table<Nothing>("alumne_grup") {
    val id_grup = int("id_grup").primaryKey()
    val id_alumne = int("id_alumne").primaryKey()
}