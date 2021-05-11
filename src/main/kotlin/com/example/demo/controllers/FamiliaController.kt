package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.Familia
import com.example.demo.app.Professor
import org.ktorm.dsl.*
import tornadofx.Controller

class FamiliaController: Controller() {
    var dd = Connexio().database

    fun obteFamilies():MutableList<Familia>{
        var families:MutableList<Familia> = ArrayList()
        var fa:Familia?= null
        for(row in dd!!.from(com.example.demo.app.Tables.Familia).select()){

            val id:Int? = row[com.example.demo.app.Tables.Familia.id]
            val nom:String? = row[com.example.demo.app.Tables.Familia.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Familia.descripcio]

            fa = Familia(id!!, nom.toString(),descripcio.toString())
            families.add(fa)
        }

        return families
    }

}

