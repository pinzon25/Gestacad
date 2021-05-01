package com.example.demo.app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

/*CREATE TABLE grups (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_cicle INTEGER NOT NULL,
    nom VARCHAR(64) NOT NULL,
    descripcio VARCHAR(128),
    FOREIGN KEY (id_cicle)
        REFERENCES cicles (id)
);*/
data class Grups(var id:Int, var id_cicle:Int, var nom:String, var descripcio:String) {
    val idProperty = SimpleIntegerProperty(id)
    val idCicleProperty = SimpleIntegerProperty(id_cicle)
    val nomProperty = SimpleStringProperty(nom)
    val descripcioProperty = SimpleStringProperty(descripcio)
}

class GrupsModel(grups:Grups?): ItemViewModel<Grups>(grups){
    val id = bind(Grups::idProperty)
    val id_cicle = bind(Grups::idCicleProperty)
    val nom = bind(Grups::nomProperty)
    val descripcio = bind(Grups::descripcioProperty)
}