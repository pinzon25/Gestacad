package com.example.demo.controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.MyApp
import javafx.scene.layout.BorderPane
import org.ktorm.dsl.*
import tornadofx.Controller
import tornadofx.Workspace
import java.time.LocalDate
import java.time.ZoneId
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

    fun obteAlumnePerId(idAl:Int):/*MutableList<Alumne>:*/Alumne{
        var alumnes:MutableList<Alumne> = ArrayList()
        var al: Alumne?=null
        var a:Alumne?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne).select().where(com.example.demo.app.Tables.Alumne.id eq idAl )){

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
        //return alumnes
        return alumnes.get(0)
    }

    fun obteAlumnePerNom(nomAl:String):MutableList<Alumne>{
        var alumnes:MutableList<Alumne> = ArrayList()
        var al: Alumne?=null
        var a:Alumne?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne).select().where(com.example.demo.app.Tables.Alumne.nom eq nomAl)){

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

    fun obteNomGrupsAlumnes():ArrayList<String?>{
        var grups:ArrayList<String?> = ArrayList()
        var grA: String?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Grups).select()){

            val nom:String? = row[com.example.demo.app.Tables.Grups.nom]

            grA = nom
            grups.add(grA)
        }

        return grups
    }


    fun obteIdAlumneMesGran():Int{
        var idMesGran:Int?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Alumne).select()){
            val idAlumne:Int? = row[com.example.demo.app.Tables.Alumne.id]
            idMesGran = idAlumne!!
        }
        return idMesGran!!+1
    }

    fun afegeixAlumneTaula(alumne: Alumne):Unit{

        dd!!.insert(com.example.demo.app.Tables.Alumne) {
            var date:Date = alumne.data_naixement
            var localDate:LocalDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()

            set(it.id, alumne.id)
            set(it.nom,alumne.nom)
            set(it.cognoms,alumne.cognoms)
            set(it.dni,alumne.dni)
            set(it.data_naixement,localDate)
            set(it.sexe,alumne.sexe)
            set(it.telefon,alumne.telefon)
            set(it.email,alumne.email)
            set(it.deleted,alumne.deleted)
            set(it.descripcio,alumne.descripcio)
            println("Has creat correctament l'alumne!!!.")
        }
    }

    fun actualitzarTaulaAlumnes(alumne: Alumne):Unit{
        dd!!.update(com.example.demo.app.Tables.Alumne){
            var date:Date = alumne.data_naixement
            var localDate:LocalDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate()

            set(it.nom,alumne.nom)
            set(it.cognoms,alumne.cognoms)
            set(it.dni,alumne.dni)
            set(it.data_naixement,localDate)
            set(it.sexe,alumne.sexe)
            set(it.telefon,alumne.telefon)
            set(it.email,alumne.email)
            set(it.deleted,alumne.deleted)
            set(it.descripcio,alumne.descripcio)
            where { it.id eq alumne.id }
        }
    }

    fun eliminaAlumneTaula(alumne: Alumne):Unit{
        dd!!.delete(com.example.demo.app.Tables.Alumne) {
            it.id eq alumne.id
        }
    }

}