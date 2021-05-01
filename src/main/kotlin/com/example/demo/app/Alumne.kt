package com.example.demo.app

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import tornadofx.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*


data class Alumne (var id:Int, var nom:String, var cognoms:String, var dni:String, var data_naixement: Date, var sexe:String, var telefon:String, var email:String, var deleted:Boolean, var descripcio:String) {
    val idProperty = SimpleIntegerProperty(id)
    val nomProperty = SimpleStringProperty(nom)
    val cognomsProperty = SimpleStringProperty(cognoms)
    val dniProperty = SimpleStringProperty(dni)
    val datanaixementProperty = SimpleStringProperty(data_naixement.toString())
    //val datanaixementProperty = SimpleDateFormat(data_naixement.toString()) //Modificar el parametre ja que sino dona l'error-->java.lang.IllegalArgumentException: Illegal pattern character 'o'
    val sexeProperty = SimpleStringProperty(sexe)
    val telefonProperty = SimpleStringProperty(telefon)
    val emailProperty = SimpleStringProperty(email)
    val deletedProperty = SimpleBooleanProperty(deleted)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class alumneModel(alumne:Alumne?): ItemViewModel<Alumne>(alumne){
    val id = bind(Alumne::idProperty)
    val nom = bind(Alumne::nomProperty)
    val cognoms = bind(Alumne::cognomsProperty)
    val dni = bind(Alumne::dniProperty)
    val data_naixement = bind(Alumne::datanaixementProperty)
    val sexe = bind(Alumne::sexeProperty)
    val telefon = bind(Alumne::telefonProperty)
    val email = bind(Alumne::emailProperty)
    val deleted = bind(Alumne::deletedProperty)
    val descripcio = bind(Alumne::descripcioProperty)
}


