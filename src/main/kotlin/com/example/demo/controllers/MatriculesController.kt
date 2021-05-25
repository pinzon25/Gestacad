package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.model.Matricula
import org.ktorm.dsl.*
import tornadofx.Controller
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList


class MatriculesController : Controller() {
    var dd = Connexio().database

    fun obteMatricules(): MutableList<Matricula> {
        var matricula: MutableList<Matricula> = ArrayList()
        var matri: Matricula? = null
        for (row in dd!!.from(com.example.demo.app.Tables.Matricula).select()) {

            val id_matricula: Int? = row[com.example.demo.app.Tables.Matricula.id_matricula]
            val id_alumne: Int? = row[com.example.demo.app.Tables.Matricula.id_alumne]
            val id_uf: Int? = row[com.example.demo.app.Tables.Matricula.id_uf]
            val curs: String? = row[com.example.demo.app.Tables.Matricula.curs]

            matri = Matricula(id_matricula!!, id_alumne!!, id_uf!!, curs.toString())
            matricula.add(matri)
        }
        return matricula
    }

    fun actualitzarTaulaMatricules(matricula: Matricula): Unit {
        dd!!.update(com.example.demo.app.Tables.Matricula) {


            set(it.id_alumne, matricula.id_alumne)
            set(it.id_uf, matricula.id_uf)
            set(it.curs, matricula.curs)
            where { it.id_matricula eq matricula.id_matricula }

        }
    }

    fun obteIdMatriculaMesGran(): Int {
        var idMesGran: Int? = null

        for (row in dd!!.from(com.example.demo.app.Tables.Matricula).select()) {
            val id_matricula: Int? = row[com.example.demo.app.Tables.Matricula.id_matricula]
            idMesGran = id_matricula!!
        }
        return idMesGran!! + 1
    }

    fun afegeixMatriculaTaula(matricula: Matricula): Unit {
        dd!!.insert(com.example.demo.app.Tables.Matricula) {

            set(it.id_matricula, matricula.id_matricula)
            set(it.id_alumne, matricula.id_alumne)
            set(it.id_uf, matricula.id_uf)
            set(it.curs, matricula.curs)
            println("Has afegit correctament la matricula!!!.")
        }
    }

    fun eliminaNotaTaula(matricula: Matricula): Unit {
        dd!!.delete(com.example.demo.app.Tables.Matricula) {
            it.id_matricula eq matricula.id_matricula
        }
    }

    fun setCursActual(): String {
        var date = Date()
        var localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        var actualYear = localDate.year
        var nextYear = actualYear + 1
        var nextYearLastCharacters = nextYear.toString().substring(nextYear.toString().length - 2)

        var cursoActual = actualYear.toString() + "/" + nextYearLastCharacters

        return cursoActual
    }
}