package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.model.Moduls
import org.ktorm.dsl.*
import tornadofx.Controller

class ModulsController  : Controller() {

    var dd = Connexio().database

    fun obteModuls():MutableList<Moduls>{
        var moduls:MutableList<Moduls> = ArrayList()
        var mo: Moduls?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Moduls).select()){

            val id:Int? = row[com.example.demo.app.Tables.Moduls.id]
            val idcicle:Int? = row[com.example.demo.app.Tables.Moduls.id_cicle]
            val nom:String? = row[com.example.demo.app.Tables.Moduls.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Moduls.descripcio]

            mo = Moduls(id!!, idcicle!!, nom.toString(), descripcio.toString())
            moduls.add(mo)



        }

        return moduls
    }
}