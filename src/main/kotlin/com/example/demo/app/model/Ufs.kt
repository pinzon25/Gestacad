package com.example.demo.app.model

import com.example.demo.app.Alumne
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Ufs(var id:Int, var id_modul:Int, var nom:String, var descripcio:String){
    val idProperty = SimpleIntegerProperty(id)
    val idmodulProperty = SimpleIntegerProperty(id_modul)
    val nomProperty = SimpleStringProperty(nom)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class UfsModel(uf:Ufs?): ItemViewModel<Ufs>(uf){
    val id = bind(Ufs::idProperty)
    val idmodul = bind(Ufs::idmodulProperty)
    val nom = bind(Ufs::nomProperty)
    val descripcio = bind(Ufs::descripcioProperty)
}