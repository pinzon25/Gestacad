package com.example.demo.controllers

import com.example.demo.view.Cicles
import com.example.demo.app.Connexio
import com.example.demo.app.Grups
import com.example.demo.app.model.Cicle
import org.ktorm.dsl.*
import tornadofx.Controller

class CiclesController : Controller(){

    var dd = Connexio().database

    fun obteCicles():MutableList<Cicle>{
        var cicles:MutableList<Cicle> = ArrayList()
        var ci: Cicle?= null
        for(row in dd!!.from(com.example.demo.app.Tables.Cicle).select()){

            val id:Int? = row[com.example.demo.app.Tables.Cicle.id]
            val idfamilia:Int? = row[com.example.demo.app.Tables.Cicle.id_familia]
            val nom:String? = row[com.example.demo.app.Tables.Cicle.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Cicle.descripcio]

            //Jan: Haig de fer bé el constructor de la Classe Cicle

            ci = Cicle(id!!, idfamilia!!, nom.toString(), descripcio.toString())
            cicles.add(ci)
        }

        return cicles
    }

    fun afegeixTaulaCicles(cicle: Cicle):Unit{
        dd!!.insert(com.example.demo.app.Tables.Cicle) {
            set(it.id, cicle.id)
            set(it.id_familia, cicle.id_familia)
            set(it.nom, cicle.nom)
            set(it.descripcio, cicle.descripcio)
            println("Has afegit correctament el grup!!!.")
        }
    }

    //DELETE DE LA TAULA GRUPS.
    fun esborraTaulaCicles(cicle: Cicle):Unit{
        dd!!.delete(com.example.demo.app.Tables.Grups){
            it.id eq cicle.id
        }
    }

    fun actualitzarTaulaGrups(cicle: Cicle):Unit{
        dd!!.update(com.example.demo.app.Tables.Grups){
            set(it.id, cicle.id)
            set(it.nom, cicle.nom)
            set(it.descripcio, cicle.descripcio)
            where { it.id eq cicle.id }
        }
    }



}