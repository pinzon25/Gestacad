package com.example.demo.app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Familia(var id:Int,var nom:String, var descripcio:String) {

    val idProperty = SimpleIntegerProperty(id)
    val nomProperty = SimpleStringProperty(nom)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class FamiliaModel(familia:Familia?): ItemViewModel<Familia>(familia){
    val id = bind(Familia::idProperty)
    val nom = bind(Familia::nomProperty)
    val descripcio = bind(Familia::descripcioProperty)
}