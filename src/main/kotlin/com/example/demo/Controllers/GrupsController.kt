package com.example.demo.app.Controllers

import com.example.demo.app.Connexio
import com.example.demo.app.Familia
import com.example.demo.app.Grups
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import tornadofx.Controller

class GrupsController : Controller(){

    var dd = Connexio().database

    fun obteGrups():MutableList<Grups>{
        var grups:MutableList<Grups> = ArrayList()
        var gr: Grups?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Grups).select()){

            val id:Int? = row[com.example.demo.app.Tables.Grups.id]
            val idcicle:Int? = row[com.example.demo.app.Tables.Grups.id_cicle]
            val nom:String? = row[com.example.demo.app.Tables.Grups.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Grups.descripcio]

            gr = Grups(id!!, idcicle!!,nom.toString(),descripcio.toString())
            grups.add(gr)
        }

        return grups
    }

}
