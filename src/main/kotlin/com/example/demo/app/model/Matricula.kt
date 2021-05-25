package com.example.demo.app.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Matricula(var id_matricula: Int, var id_alumne: Int, var id_uf: Int, var curs: String) {

    val id_matriculaProperty = SimpleIntegerProperty(id_matricula);
    val id_alumneProperty = SimpleIntegerProperty(id_alumne);
    val id_ufProperty = SimpleIntegerProperty(id_uf);
    val cursProperty = SimpleStringProperty(curs);

}

class matriculaModel(matricula: Matricula?) : ItemViewModel<Matricula>(matricula) {

    val id_matricula = bind(Matricula::id_matriculaProperty);
    val id_alumne =bind(Matricula::id_alumneProperty);
    val id_uf = bind(Matricula::id_ufProperty);
    val curs = bind(Matricula::cursProperty);

}