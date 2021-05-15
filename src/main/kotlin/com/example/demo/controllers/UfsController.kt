package com.example.demo.controllers

import com.example.demo.app.Alumne
import com.example.demo.app.Connexio
import com.example.demo.app.Grups
import com.example.demo.app.Tables.Alumne_grup
import com.example.demo.app.Tables.Moduls
import com.example.demo.app.Tables.UFS
import com.example.demo.app.model.Ufs
import javafx.scene.control.Alert
import org.ktorm.dsl.*
import tornadofx.Controller
import tornadofx.alert
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class UfsController:Controller() {
    var dd = Connexio().database

  //READ DE LA TAULA UFS.
fun obteUfs():MutableList<Ufs>{
    var ufs:MutableList<Ufs> = ArrayList()
    var uf: Ufs?=null
    for(row in dd!!.from(com.example.demo.app.Tables.UFS).select()){

        val id:Int? = row[com.example.demo.app.Tables.UFS.id]
        val idmodul:Int? = row[com.example.demo.app.Tables.UFS.id_modul]
        val nom:String? = row[com.example.demo.app.Tables.UFS.nom]
        val descripcio:String? = row[com.example.demo.app.Tables.UFS.descripcio]

        uf = Ufs(id!!, idmodul!! ,nom.toString(),descripcio.toString())
        ufs.add(uf)
    }

    return ufs
}


    //INSERT DE LA TAULA UFS
    fun afegeixUfs(uf:Ufs):Unit{
        dd!!.insert(UFS) {
            set(it.id, uf.id)
            set(it.id_modul, uf.id_modul)
            set(it.nom, uf.nom)
            set(it.descripcio, uf.descripcio)
            println("Has afegit correctament la uf.")
            alert(Alert.AlertType.INFORMATION, "Uf afegida.", "La UF s'ha afegit correctament.")
        }
    }

    //DELETE DE LA TAULA UFS
    fun esborraUfs(iduf:Int):Unit{
        dd!!.delete(UFS){
            it.id eq iduf
        }
        alert(Alert.AlertType.INFORMATION, "UF esborrada.", "La UF s'ha esborrat correctament.")
    }

    //UPDATE DE LA TAULA UFS
    fun actualitzarUfs(uf: Ufs):Unit{
        dd!!.update(UFS){
            set(it.id,uf.id)
            set(it.id_modul, uf.id_modul)
            set(it.nom, uf.nom)
            set(it.descripcio, uf.descripcio)
            where { it.id eq uf.id }
        }
    }

    /*Obtenir el id del modul segons el nom de modul passat per parametre,
    amb el id del modul busquem a la taula de ufs el id_modul corresponenti obtenim totes
    les dades de la uf, agafem la uf i la afegim al arraylist de ufs.
    * */

    fun obteUfsPerModul(nomModul:String):MutableList<Ufs>{
        var modul:com.example.demo.app.model.Moduls?=null
        var uf:Ufs?=null
        var moduls:MutableList<com.example.demo.app.model.Moduls> = ArrayList()
        var ufs:MutableList<com.example.demo.app.model.Ufs> = ArrayList()

        for(row in dd!!.from(com.example.demo.app.Tables.Moduls).select().where(Moduls.nom eq nomModul)){

            val id:Int? = row[com.example.demo.app.Tables.Moduls.id]
            val idcicle:Int? = row[com.example.demo.app.Tables.Moduls.id_cicle]
            val nom:String? = row[com.example.demo.app.Tables.Moduls.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.Moduls.descripcio]

            modul = com.example.demo.app.model.Moduls(id!!, idcicle!!, nom.toString(), descripcio.toString())
            moduls.add(modul)
        }

        for(row in dd!!.from(com.example.demo.app.Tables.UFS).select().where(UFS.id_modul eq moduls.get(0).id)){

            val id:Int? = row[com.example.demo.app.Tables.UFS.id]
            val idmodul:Int? = row[com.example.demo.app.Tables.UFS.id_modul]
            val nom:String? = row[com.example.demo.app.Tables.UFS.nom]
            val descripcio:String? = row[com.example.demo.app.Tables.UFS.descripcio]

            uf = com.example.demo.app.model.Ufs(id!!, idmodul!!, nom.toString(), descripcio.toString())
            ufs.add(uf)
        }

        return ufs
    }

    fun obteIdUfMesGran():Int{
        var idUfMesGran:Int?=null
        for(row in dd!!.from(com.example.demo.app.Tables.UFS).select()){
            val idUf:Int? = row[com.example.demo.app.Tables.UFS.id]
            idUfMesGran = idUf!!
        }

        return idUfMesGran!!
    }
}