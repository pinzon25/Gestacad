package com.example.demo.controllers

import com.example.demo.app.Professor
import com.example.demo.app.Connexio
import org.ktorm.dsl.*
import tornadofx.Controller
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList


class ProfesorsController : Controller() {
    var dd = Connexio().database
    fun obteProfesors(): MutableList<Professor> {
        var profesor: MutableList<Professor> = ArrayList()
        var prof: Professor? = null
        for (row in dd!!.from(com.example.demo.app.Tables.Professor).select()) {

            val id: Int? = row[com.example.demo.app.Tables.Professor.id]
            val nom: String? = row[com.example.demo.app.Tables.Professor.nom]
            val cognoms: String? = row[com.example.demo.app.Tables.Professor.cognoms]
            val dni: String? = row[com.example.demo.app.Tables.Professor.dni]
            val datanaixement: LocalDate? = row[com.example.demo.app.Tables.Professor.data_naixement]
            val sexe: String? = row[com.example.demo.app.Tables.Professor.sexe]
            val telefon: Int? = row[com.example.demo.app.Tables.Professor.telefon]
            val email: String? = row[com.example.demo.app.Tables.Professor.email]
            val descripcio: String? = row[com.example.demo.app.Tables.Professor.descripcio]

            var d: Date = Date(datanaixement!!.year, datanaixement.monthValue, datanaixement.dayOfMonth)
            prof = Professor(
                id!!,
                nom.toString(),
                cognoms.toString(),
                dni.toString(),
                d,
                sexe.toString(),
                telefon.toString(),
                email.toString(),
                descripcio.toString()
            )
            profesor.add(prof)
        }
        return profesor
    }
    fun obteProfesorsPerNom(profesorNom:String): MutableList<Professor> {
        var profesor: MutableList<Professor> = ArrayList()
        var prof: Professor? = null
        for (row in dd!!.from(com.example.demo.app.Tables.Professor).select().where(com.example.demo.app.Tables.Professor.nom eq profesorNom)) {

            val id: Int? = row[com.example.demo.app.Tables.Professor.id]
            val nom: String? = row[com.example.demo.app.Tables.Professor.nom]
            val cognoms: String? = row[com.example.demo.app.Tables.Professor.cognoms]
            val dni: String? = row[com.example.demo.app.Tables.Professor.dni]
            val datanaixement: LocalDate? = row[com.example.demo.app.Tables.Professor.data_naixement]
            val sexe: String? = row[com.example.demo.app.Tables.Professor.sexe]
            val telefon: Int? = row[com.example.demo.app.Tables.Professor.telefon]
            val email: String? = row[com.example.demo.app.Tables.Professor.email]
            val descripcio: String? = row[com.example.demo.app.Tables.Professor.descripcio]

            var d: Date = Date(datanaixement!!.year, datanaixement.monthValue, datanaixement.dayOfMonth)
            prof = Professor(
                id!!,
                nom.toString(),
                cognoms.toString(),
                dni.toString(),
                d,
                sexe.toString(),
                telefon.toString(),
                email.toString(),
                descripcio.toString()
            )
            profesor.add(prof)
        }
        return profesor
    }
    fun obteIdProfesorsMesGran():Int{
        var idMesGran:Int?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Professor).select()){
            val idProfesor:Int? = row[com.example.demo.app.Tables.Professor.id]
            idMesGran = idProfesor!!
        }
        return idMesGran!!+1
    }
    fun afegeixProfesorTaula(profesor: Professor):Unit{

        dd!!.insert(com.example.demo.app.Tables.Professor) {
            var date:Date = profesor.data_naixement
            var localDate:LocalDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()
            set(it.id, profesor.id)
            set(it.nom,profesor.nom)
            set(it.cognoms,profesor.cognoms)
            set(it.dni,profesor.dni)
            set(it.data_naixement,localDate)
            set(it.sexe,profesor.sexe)
            set(it.telefon,profesor.telefon)
            set(it.email,profesor.email)
            set(it.descripcio,profesor.descripcio)
            println("Professor creat correctament  !!!.")
        }
    }
    fun actualitzarTaulaProfesors(profesor: Professor):Unit{
        dd!!.update(com.example.demo.app.Tables.Professor) {
            var date:Date = profesor.data_naixement
            var localDate:LocalDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()

            set(it.id, profesor.id)
            set(it.nom,profesor.nom)
            set(it.cognoms,profesor.cognoms)
            set(it.dni,profesor.dni)
            set(it.data_naixement,localDate)
            set(it.sexe,profesor.sexe)
            set(it.telefon,profesor.telefon)
            set(it.email,profesor.email)
            set(it.descripcio,profesor.descripcio)
            where { it.id eq profesor.id }
        }
    }
    fun eliminaProfesorsTaula(profesor: Professor):Unit{
        dd!!.delete(com.example.demo.app.Tables.Professor) {
            it.id eq profesor.id
        }
    }
}

