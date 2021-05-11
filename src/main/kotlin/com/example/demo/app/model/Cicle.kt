package com.example.demo.app.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Cicle(var id:Int, var id_familia:Int, var nom:String, var descripcio:String) {

    val idProperty = SimpleIntegerProperty(id)
    val idFamiliaProperty = SimpleIntegerProperty(id_familia)
    val nomProperty = SimpleStringProperty(nom)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class cicleModel(familia:Cicle?): ItemViewModel<Cicle>(familia){
    val id = bind(Cicle::idProperty)
    val id_familia = bind(Cicle::idFamiliaProperty)
    val nom = bind(Cicle::nomProperty)
    val descripcio = bind(Cicle::descripcioProperty)
}