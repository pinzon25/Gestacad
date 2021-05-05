package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.model.Alumnes_grups
import org.ktorm.dsl.*
import tornadofx.Controller

class GrupsController : Controller(){

    var dd = Connexio().database

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

    fun obteGrupsAlumne():MutableList<Alumnes_grups>{
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



}
