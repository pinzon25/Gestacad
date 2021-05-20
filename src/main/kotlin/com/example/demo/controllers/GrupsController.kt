package com.example.demo.controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.Grups
import com.example.demo.app.Main
import com.example.demo.app.Tables.Alumne_grup
import com.example.demo.app.model.Alumnes_grups
import javafx.scene.control.Alert
import org.ktorm.dsl.*
import tornadofx.Controller
import tornadofx.alert

class GrupsController : Controller(){

    var dd = Connexio().database
    val alumneControler:AlumnesController by inject()

    //READ DE LA TAULA GRUPS
    fun obteGrups():MutableList<com.example.demo.app.Grups>{
        var grups:MutableList<com.example.demo.app.Grups> = ArrayList()
        var gr: com.example.demo.app.Grups?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Grups).select()){
            val id:Int? = row[com.example.demo.app.Tables.Grups.id]
            val idcicle:Int? = row[com.example.demo.app.Tables.Grups.id_cicle]
            val nom:String? = row[com.example.demo.app.Tables.Grups.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Grups.descripcio]

            gr = com.example.demo.app.Grups(id!!, idcicle!!, nom.toString(), descripcio.toString())
            grups.add(gr)
        }

        return grups
    }

    //INSERT DE LA TAULA GRUPS
    fun afegeixTaulaGrups(grup: Grups):Unit{

        dd!!.insert(com.example.demo.app.Tables.Grups) {
            set(it.id, grup.id)
            set(it.id_cicle, grup.id_cicle)
            set(it.nom, grup.nom)
            set(it.descripcio, grup.descripcio)
            println("Has afegit correctament el grup!!!.")
        }
        //alert(Alert.AlertType.INFORMATION,"Grup afegit.", "El grup s'ha afegit a la base de dades.")
    }

    //DELETE DE LA TAULA GRUPS.
    fun esborraTaulaGrups(grup:Grups):Unit{
        dd!!.delete(com.example.demo.app.Tables.Grups){
            it.id eq grup.id
        }
    }

    //UPDATE DE LA TAULA GRUPS
    fun actualitzarTaulaGrups(grup:Grups):Unit{
        dd!!.update(com.example.demo.app.Tables.Grups){
            set(it.id_cicle,grup.id_cicle)
            set(it.nom,grup.nom)
            set(it.descripcio,grup.descripcio)
            where { it.id eq grup.id }
        }
    }

    //OBTENIM EL ID MES GRAN DE LA TAULA GRUPS
    fun obteIdGrupMesGran():Int{
        var idMesGran:Int?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Grups).select()){
            val idGrup:Int? = row[com.example.demo.app.Tables.Grups.id]
            idMesGran = idGrup!!
        }

        return idMesGran!!
    }

    //READ LA TAULA ALUMNE_GRUP.
    fun obteGrupAlumnes():MutableList<Alumnes_grups>{
        var alumnesGrups:MutableList<Alumnes_grups> = ArrayList()
        var ag: com.example.demo.app.model.Alumnes_grups?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne_grup).select()){
            val idGrup:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_grup]
            val idAlumne:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_alumne]

            ag = com.example.demo.app.model.Alumnes_grups(idGrup!!,idAlumne!!)
            alumnesGrups.add(ag)
        }
        return alumnesGrups
    }

    //INSERT DE LA TAULA ALUMNE_GRUP
    fun afegeixAlumneTaulaAlumneGrup(idgrup:Int,alumneId:Int):Unit{
        dd!!.insert(Alumne_grup) {
            set(it.id_grup, idgrup)
            set(it.id_alumne, alumneId)
            println("Has afegit correctament l'alumne.")
            alert(Alert.AlertType.INFORMATION, "Alumne inscrit al grup.", "L'alumne s'ha inscrit al grup correctament.")
        }
    }

    //DELETE DE LA TAULA ALUMNE_GRUP
    fun esborraAlumneTaulaAlumneGrup(idgrup:Int,alumneId:Int):Unit{
        dd!!.delete(Alumne_grup){
            it.id_grup eq idgrup
            it.id_alumne eq alumneId
        }
        alert(Alert.AlertType.INFORMATION, "Alumne esborrat del grup.", "L'alumne s'ha esborrat del grup correctament.")
    }

    //OBTE TOTS ELS ALUMNES QUE PERTANYEN A UN ID DE GRUP CONCRET.
    fun obteAlumnesIdGrup(idgrup:Int):MutableList<Alumne>{
        //var alumnesGrups:MutableList<Alumnes_grups> = ArrayList()
        var alumnes:MutableList<Alumne> = ArrayList()
        //var ag: com.example.demo.app.model.Alumnes_grups?=null

        //Llegim tots els registres de la taula alumne_grup.
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne_grup).select().where(Alumne_grup.id_grup eq idgrup)){
            //val idGrup:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_grup]
            val idAlumne:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_alumne]
            /*ag = com.example.demo.app.model.Alumnes_grups(idGrup!!,idAlumne!!)
            alumnesGrups.add(ag) //Obtenim totes les dades de la taula grups_alumnes.*/
            alumnes.add(alumneControler.obteAlumnePerId(idAlumne!!))
        }

        //println("Alumnes obtinguts mitjancant els id_alumne del array alumnesGrups: "+alumnes)
        return alumnes
    }

}
