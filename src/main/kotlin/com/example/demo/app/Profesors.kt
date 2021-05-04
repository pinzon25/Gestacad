package com.example.demo.app

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import tornadofx.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

data class Profesors (var id:Int, var nom:String, var cognoms:String, var dni:String, var data_naixement: Date, var sexe:String, var telefon:String, var email:String, var descripcio:String){
    val idProperty = SimpleIntegerProperty(id)
    val nomProperty = SimpleStringProperty(nom)
    val cognomsProperty = SimpleStringProperty(cognoms)
    val dniProperty = SimpleStringProperty(dni)
    val datanaixementProperty = SimpleStringProperty(data_naixement.toString())
    val sexeProperty = SimpleStringProperty(sexe)
    val telefonProperty = SimpleStringProperty(telefon)
    val emailProperty = SimpleStringProperty(email)
    val descripcioProperty = SimpleStringProperty(descripcio)
}
class profesorModel(profesor:Profesors?): ItemViewModel<Profesors>(profesor){
    val id = bind(Profesors::idProperty)
    val nom = bind(Profesors::nomProperty)
    val cognoms = bind(Profesors::cognomsProperty)
    val dni = bind(Profesors::dniProperty)
    val data_naixement = bind(Profesors::datanaixementProperty)
    val sexe = bind(Profesors::sexeProperty)
    val telefon = bind(Profesors::telefonProperty)
    val email = bind(Profesors::emailProperty)
    val descripcio = bind(Profesors::descripcioProperty)
}