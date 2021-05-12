package com.example.demo.controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.Tables.Alumne_grup
import com.example.demo.app.model.Alumnes_grups
import org.ktorm.dsl.*
import tornadofx.Controller

class GrupsController : Controller(){

    var dd = Connexio().database
    val alumneControler:AlumnesController by inject()

    //Obtenim tots els grups
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


    /*fun obteAlumnesIdGrup(idgrup:Int):MutableList<Alumne>{
        var alumnesGrups:MutableList<Alumnes_grups> = ArrayList()
        var alumnes:MutableList<Alumne> = ArrayList()
        var ag: com.example.demo.app.model.Alumnes_grups?=null
        for(row in dd!!.from(com.example.demo.app.Tables.Alumne_grup).select()){

                val idGrup: Int? = row[com.example.demo.app.Tables.Alumne_grup.id_grup]
                val idAlumne: Int? = row[com.example.demo.app.Tables.Alumne_grup.id_alumne]

            if(idgrup == idGrup) {
                alumnes = alumneControler.obteAlumnePerId(idAlumne!!)
            }
        }

        return alumnes
    }*/

    fun obteAlumnesGrups(idGrup:Int):MutableList<Alumne>{
        var alumnesGrups:MutableList<Alumnes_grups> = ArrayList()
        var alumnes:MutableList<Alumne> = ArrayList()
        var ag: com.example.demo.app.model.Alumnes_grups?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Alumne_grup).select()){

            val idGrup:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_grup]
            val idAlumne:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_alumne]

            ag = com.example.demo.app.model.Alumnes_grups(idGrup!!,idAlumne!!)
            alumnesGrups.add(ag) //Obtenim totes les dades de la taula grups_alumnes.
        }


        for(i in alumnesGrups.indices){
            var alum:Alumne?=null
            var idgrup:Int?=null
            var idAlumne:Int?=null
            idgrup = alumnesGrups.get(i).id_grup
            //idAlumne = alumnesGrups.get(i).id_alumne
            /*println("id del idgrup: " + idgrup) //Aqui ja obtenim el id del grup.
            println("id del idalumne: " + idAlumne) //Aqui ja obtenim els id dels alumnes guardats al array.*/
            if(idGrup == idgrup){
                alumnes.add(alumneControler.obteAlumnePerId(alumnesGrups.get(i).id_alumne))
            }


        }

        //println("Alumnes obtinguts mitjancant els id_alumne del array alumnesGrups: "+alumnes)
        return alumnes
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

    fun obteIdGrupMesGran():Int{
        var idMesGran:Int?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Alumne_grup).select()){
            val idGrup:Int? = row[com.example.demo.app.Tables.Alumne_grup.id_grup]
             idMesGran = idGrup!!
        }
        return idMesGran!!+2
    }

    fun afegeixAlumneTaula(idgrup:Int,alumneId:Int):Unit{
        dd!!.insert(Alumne_grup) {
            set(it.id_grup, idgrup)
            set(it.id_alumne, alumneId)
            println("Has afegit correctament l'alumne.")
        }
    }
    
    fun esborraAlumneTaula(idgrup:Int,alumneId:Int):Unit{
        dd!!.delete(Alumne_grup){
            it.id_grup eq idgrup
            it.id_alumne eq alumneId
        }
    }



}
