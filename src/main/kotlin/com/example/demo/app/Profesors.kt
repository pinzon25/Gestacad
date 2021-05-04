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
