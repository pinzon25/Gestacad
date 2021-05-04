package com.example.demo.controllers
import com.example.demo.app.utils.Connexio
import com.example.demo.app.Professor

import org.ktorm.dsl.*
import tornadofx.Controller
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


class ProfesorsController: Controller() {
    var dd = Connexio().database
    fun obteProfesors():MutableList<Professor>{
        var profesors:MutableList<Professor> = ArrayList()
        var al: Professor?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Professor).select()){

            val id:Int? = row[com.example.demo.app.Tables.Professor.id]
            val nom:String? = row[com.example.demo.app.Tables.Professor.nom]
            val cognoms:String? = row[com.example.demo.app.Tables.Professor.cognoms]
            val dni:String? = row[com.example.demo.app.Tables.Professor.dni]
            val datanaixement: LocalDate? = row[com.example.demo.app.Tables.Professor.data_naixement]
            val sexe:String? = row[com.example.demo.app.Tables.Professor.sexe]
            val telefon:Int? = row[com.example.demo.app.Tables.Professor.telefon]
            val email:String? = row[com.example.demo.app.Tables.Professor.email]
            val descripcio:String? = row[com.example.demo.app.Tables.Professor.descripcio]

            var d: Date = Date(datanaixement!!.year,datanaixement.monthValue, datanaixement.dayOfMonth)
            al = Professor(id!!, nom.toString(),cognoms.toString(),dni.toString(),d,sexe.toString(),telefon.toString(),email.toString(),descripcio.toString())
            profesors.add(al)
        }

        return profesors
    }


}