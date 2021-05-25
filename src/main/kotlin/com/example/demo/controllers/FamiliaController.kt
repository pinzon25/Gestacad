package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.model.Familia
import com.example.demo.app.Grups
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

    fun obteIdFamiliaMesGran():Int{
        var idMesGran:Int?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Familia).select()){
            val idFamilia:Int? = row[com.example.demo.app.Tables.Familia.id]
            idMesGran = idFamilia!!
        }
        return idMesGran!!+1
    }

    fun afegeixFamiliaTaula(familia: Familia):Unit{
        dd!!.insert(com.example.demo.app.Tables.Familia) {
            set(it.id, familia.id)
            set(it.nom, familia.nom)
            set(it.descripcio, familia.descripcio)
            println("Has creat correctament la familia!!!.")
        }
    }

    fun actualitzarTaulaFamilies(familia: Familia):Unit{
        dd!!.update(com.example.demo.app.Tables.Familia){
            set(it.nom,familia.nom)
            set(it.descripcio,familia.descripcio)
            where { it.id eq familia.id }
        }
    }

    fun eliminaFamiliaTaula(familia: Familia):Unit{
        dd!!.delete(com.example.demo.app.Tables.Familia) {
            it.id eq familia.id
        }
    }
    /*fun eliminaFamiliaTaulaCicles(familia: Familia):Unit{
        dd!!.delete(com.example.demo.app.Tables.Familia) {
            it.id eq familia.id
            //it.nom eq familia.nom
            //it.descripcio eq familia.descripcio
        }
    }*/

}

