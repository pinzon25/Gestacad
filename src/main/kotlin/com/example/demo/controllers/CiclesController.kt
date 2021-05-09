package com.example.demo.controllers

import com.example.demo.view.Cicles
import com.example.demo.app.Connexio
import org.ktorm.dsl.*

class CiclesController {

    var dd = Connexio().database

    fun obteCicles():MutableList<Cicles>{
        var cicles:MutableList<Cicles> = ArrayList()
        var ci: Cicles?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Cicle).select()){

            val id:Int? = row[com.example.demo.app.Tables.Cicle.id]
            val idfamilia:Int? = row[com.example.demo.app.Tables.Cicle.id_familia]
            val nom:String? = row[com.example.demo.app.Tables.Cicle.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Cicle.descripcio]

            //Jan: Haig de fer bé el constructor de la Classe Cicle

            //ci = Cicle(id!!, idfamilia!!,nom.toString(),descripcio.toString())
            //cicles.add(ci)
        }

        return cicles
    }
}