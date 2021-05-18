package com.example.demo.controllers

import javafx.scene.control.Alert
import tornadofx.alert
import com.example.demo.app.Connexio
import com.example.demo.app.model.Moduls
import com.example.demo.app.model.Ufs
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
    //INSERT DE LA TAULA Moduls
    fun afegeiModuls(moduls: Moduls):Unit{
        dd!!.insert(com.example.demo.app.Tables.Moduls) {
            set(it.id, moduls.id)
            set(it.id_cicle, moduls.id_cicle)
            set(it.nom, moduls.nom)
            set(it.descripcio, moduls.descripcio)
            println("Has afegit correctament el modul.")

        }
    }

    fun esborraModuls(idModuls:Int):Unit{
        dd!!.delete(com.example.demo.app.Tables.Moduls){
            it.id eq idModuls
        }
        alert(Alert.AlertType.INFORMATION, "Modul esborrat!.", "El Modul s'ha esborrat correctament.")
    }

    fun actualitzarModuls(moduls: Moduls):Unit{
        dd!!.update(com.example.demo.app.Tables.Moduls){
            set(it.id,moduls.id)
            set(it.id_cicle, moduls.id_cicle)
            set(it.nom, moduls.nom)
            set(it.descripcio, moduls.descripcio)
            where { it.id eq moduls.id }
        }
        alert(Alert.AlertType.INFORMATION, "Modul actualitzat.", "EL Modul s'ha actualitzat correctament.")
    }

    fun obteModulsNom():MutableList<String>{
        var moduls:MutableList<String> = ArrayList()
        var mo: Moduls?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Moduls).select()){

            val id:Int? = row[com.example.demo.app.Tables.Moduls.id]
            val idcicle:Int? = row[com.example.demo.app.Tables.Moduls.id_cicle]
            val nom:String? = row[com.example.demo.app.Tables.Moduls.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Moduls.descripcio]

            mo = Moduls(id!!, idcicle!!, nom.toString(), descripcio.toString())
            moduls.add(mo.nom)
        }

        return moduls
    }


}