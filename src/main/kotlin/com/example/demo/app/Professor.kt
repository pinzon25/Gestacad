package com.example.demo.app

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import tornadofx.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

data class Professor (var id:Int, var nom:String, var cognoms:String, var dni:String, var data_naixement: Date, var sexe:String, var telefon:String, var email:String, var descripcio:String){
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
class profesorModel(profesor:Professor?): ItemViewModel<Professor>(profesor){
    val id = bind(Professor::idProperty)
    val nom = bind(Professor::nomProperty)
    val cognoms = bind(Professor::cognomsProperty)
    val dni = bind(Professor::dniProperty)
    val data_naixement = bind(Professor::datanaixementProperty)
    val sexe = bind(Professor::sexeProperty)
    val telefon = bind(Professor::telefonProperty)
    val email = bind(Professor::emailProperty)
    val descripcio = bind(Professor::descripcioProperty)
}