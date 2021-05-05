package com.example.demo.app.model

import javafx.beans.property.SimpleIntegerProperty

data class Alumnes_grups(var id_grup:Int, var id_alumne:Int){
    val id_grupProperty = SimpleIntegerProperty(id_grup)
    val id_alumnesProperty = SimpleIntegerProperty(id_alumne)
}
