package com.example.demo.app.model


import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Moduls(var id:Int, var id_cicle:Int, var nom:String, var descripcio:String) {
    val idProperty = SimpleIntegerProperty(id)
    val idCicleProperty = SimpleIntegerProperty(id_cicle)
    val nomProperty = SimpleStringProperty(nom)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class ModulsModel(moduls: Moduls?): ItemViewModel<Moduls>(moduls){
    val id = bind(Moduls::idProperty)
    val id_cicle = bind(Moduls::idCicleProperty)
    val nom = bind(Moduls::nomProperty)
    val descripcio = bind(Moduls::descripcioProperty)
}