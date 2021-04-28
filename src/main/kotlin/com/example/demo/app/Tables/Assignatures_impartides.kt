package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int

object Assignatures_impartides: Table<Nothing>("assignatures_impartides") {
    val id_professor = int("id_professor").primaryKey()
    val id_modul = int("id_modul").primaryKey()
}