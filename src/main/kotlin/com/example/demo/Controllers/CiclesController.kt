package com.example.demo.Controllers

import com.example.demo.app.Cicle
import com.example.demo.app.Connexio
import com.example.demo.app.Grups
import org.ktorm.dsl.from
import org.ktorm.dsl.select

class CiclesController {

    var dd = Connexio().database

    fun obteCicles():MutableList<Cicle>{
        var cicles:MutableList<Cicle> = ArrayList()
        var ci: Cicle?=null
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