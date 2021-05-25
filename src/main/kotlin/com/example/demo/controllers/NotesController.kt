package com.example.demo.controllers

import com.example.demo.app.Connexio
import com.example.demo.app.Familia
import com.example.demo.app.model.Notes
import org.ktorm.dsl.*
import tornadofx.Controller

class NotesController : Controller() {
    var dd = Connexio().database

    fun obteNotes(): MutableList<Notes> {
        var notes: MutableList<Notes> = ArrayList()
        var not: Notes? = null
        for (row in dd!!.from(com.example.demo.app.Tables.Notes).select()) {

            val id_notes: Int? = row[com.example.demo.app.Tables.Notes.id_notes]
            val id_alumne: Int? = row[com.example.demo.app.Tables.Notes.id_alumne]
            val id_uf: Int? = row[com.example.demo.app.Tables.Notes.id_uf]
            val puntuacio_notes: Float? = row[com.example.demo.app.Tables.Notes.puntuacio_notes]
            val pes_notes: Int? = row[com.example.demo.app.Tables.Notes.pes_notes]
            val descripcio_notes: String? = row[com.example.demo.app.Tables.Notes.descripcio_notes]

            not = Notes(id_notes!!, id_alumne!!, id_uf!!, puntuacio_notes!!, pes_notes!!, descripcio_notes.toString())
            notes.add(not)
        }
        return notes
    }

    fun actualitzarTaulaNotes(nota: Notes):Unit{
        dd!!.update(com.example.demo.app.Tables.Notes){

            //set(it.id_notes, nota.id_notes)
            set(it.id_alumne, nota.id_alumne)
            set(it.id_uf, nota.id_uf)
            set(it.puntuacio_notes, nota.puntuacio)
            set(it.pes_notes, nota.pes)
            set(it.descripcio_notes, nota.description)
            where { it.id_notes eq  nota.id_notes }
        }
    }

    fun obteIdNotaMesGran():Int{
        var idMesGran:Int?=null

        for(row in dd!!.from(com.example.demo.app.Tables.Notes).select()){
            val id_notes:Int? = row[com.example.demo.app.Tables.Notes.id_notes]
            idMesGran = id_notes!!
        }
        return idMesGran!!+1
    }

    fun afegeixNotaTaula(nota: Notes):Unit{
        dd!!.insert(com.example.demo.app.Tables.Notes) {
            set(it.id_notes, nota.id_notes)
            set(it.id_alumne, nota.id_alumne)
            set(it.id_uf, nota.id_uf)
            set(it.puntuacio_notes, nota.puntuacio)
            set(it.pes_notes, nota.pes)
            set(it.descripcio_notes, nota.description)

            println("Has afegit correctament la nota!!!.")
        }
    }

    fun eliminaNotaTaula(nota: Notes):Unit{
        dd!!.delete(com.example.demo.app.Tables.Notes) {
            it.id_notes eq nota.id_notes
        }
    }

}