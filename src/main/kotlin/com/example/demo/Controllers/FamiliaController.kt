package com.example.demo.app.Controllers

import com.example.demo.app.Connexio
import com.example.demo.app.Familia
import com.example.demo.app.MyApp
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import tornadofx.Controller

class FamiliaController: Controller() {
    var dd = Connexio().database

    fun obteFamilies():ArrayList<Familia>{
        var families:ArrayList<Familia> = ArrayList()
        var fa:Familia?=null
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

