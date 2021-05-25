package com.example.demo.app.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Notes(var id_notes:Int, var id_alumne:Int, var id_uf: Int, var puntuacio: Float, var pes: Int, var description: String) {

    val idProperty = SimpleIntegerProperty(id_notes);
    val id_alumneProperty = SimpleIntegerProperty(id_alumne);
    val id_ufProperty = SimpleIntegerProperty(id_uf);
    val puntuacioProperty = SimpleFloatProperty(puntuacio);
    val pesProperty = SimpleIntegerProperty(pes);
    val descriptionProperty = SimpleStringProperty(description);

}


class notesModel(notes:Notes?): ItemViewModel<Notes>(notes) {

    val id = bind(Notes::idProperty);
    val id_alumne = bind(Notes::id_alumneProperty);
    val id_uf = bind(Notes::id_ufProperty);
    val puntuacio = bind(Notes::puntuacioProperty);
    val pes = bind(Notes::pesProperty);
    val description = bind(Notes::descriptionProperty);


}