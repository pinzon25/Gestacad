package com.example.demo.app.Controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.Grups
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import tornadofx.Controller
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/*var id:Int,
var nom:String,
var cognoms:String,
var dni:String,
var data_naixement: Date,
var sexe:String,
var telefon:String,
var email:String,
var deleted:Boolean,
var descripcio:String*/
class AlumnesController: Controller() {
    var dd = Connexio().database

    fun obteAlumnes():MutableList<Alumne>{
        var alumnes:MutableList<Alumne> = ArrayList()
        var al: Alumne?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne).select()){

            val id:Int? = row[com.example.demo.app.Tables.Alumne.id]
            val nom:String? = row[com.example.demo.app.Tables.Alumne.nom]
            val cognoms:String? = row[com.example.demo.app.Tables.Alumne.cognoms]
            val dni:String? = row[com.example.demo.app.Tables.Alumne.dni]
            val datanaixement: LocalDate? = row[com.example.demo.app.Tables.Alumne.data_naixement]
            val sexe:String? = row[com.example.demo.app.Tables.Alumne.sexe]
            val telefon:String? = row[com.example.demo.app.Tables.Alumne.telefon]
            val email:String? = row[com.example.demo.app.Tables.Alumne.email]
            val deleted:Boolean? = row[com.example.demo.app.Tables.Alumne.deleted]
            val descripcio:String? = row[com.example.demo.app.Tables.Alumne.descripcio]

            var d:Date = Date(datanaixement!!.year,datanaixement.monthValue, datanaixement.dayOfMonth)//Conversio del SimpleDateFormat/LocalDate a Date(year: Int, month: Int, day: Int)
            al = Alumne(id!!, nom.toString(),cognoms.toString(),dni.toString(),d,sexe.toString(),telefon.toString(),email.toString(),deleted==false,descripcio.toString())
            alumnes.add(al)
        }

        return alumnes
    }


}