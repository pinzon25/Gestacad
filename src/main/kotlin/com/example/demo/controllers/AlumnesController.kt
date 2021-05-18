package com.example.demo.controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.MyApp
import javafx.scene.layout.BorderPane
import org.ktorm.dsl.*
import tornadofx.Controller
import tornadofx.Workspace
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
            val telefon:Int? = row[com.example.demo.app.Tables.Alumne.telefon]
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
            val telefon:Int? = row[com.example.demo.app.Tables.Alumne.telefon]
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
            val telefon:Int? = row[com.example.demo.app.Tables.Alumne.telefon]
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


    /*fun esborraAlumne(id: Int?) {
        val ps = dd.prepareStatement("DELETE FROM Alumne WHERE id_alumne = ?")

        if (id != null) {
            ps.setInt(1, id)
            ps.executeUpdate()
            alert(Alert.AlertType.CONFIRMATION, "", "L'alumne s'ha esborrat correctament!")
        } else {
            alert(Alert.AlertType.ERROR, "", "No has escollit ningun alumne!")
        }

        //c.close()
    }

    fun crearNouAlumne(alumne: Alumne) {
        val ps = c.prepareStatement("INSERT INTO Alumne (Nom,Cognoms,Edat) VALUES (?,?,?)")
        ps.setString(1, alumne.nom)
        ps.setString(2, alumne.cognoms)
        ps.setInt(3, alumne.edat)
        ps.executeUpdate()
        alert(Alert.AlertType.CONFIRMATION, "", "Alumne creat correctament!")
        //c.close()
    }

    fun actualitza(a: Alumne) {
        //println("Has entrat al metode on actualitzem els camps al SQL.")
        println("Alumne rebut al metode Actualitza: " + a)
        val ps = c.prepareStatement("UPDATE Alumne SET Nom = ?, Cognoms = ?,  edat = ? WHERE id_alumne = ?")
        ps.setString(1, a.nom)
        ps.setString(2, a.cognoms)
        ps.setInt(3, a.edat)
        ps.setInt(4, a.id)
        val rs = ps.executeUpdate()
        alert(Alert.AlertType.CONFIRMATION, "", "L'alumne actualitzat correctament!")
    }*/

}