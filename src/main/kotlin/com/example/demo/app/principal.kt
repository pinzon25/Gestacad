package com.example.demo.app

import com.example.demo.app.Tables.Familia
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import org.ktorm.dsl.insert
import tornadofx.View
import tornadofx.Workspace

class principal: View(){
    override val root: AnchorPane by fxml()
    val Bt_cicle: Button by fxid("Bt_cicles")
    var work:Workspace = MyApp().workspace

    init {
        with(root) {
        work.createButton.setOnMouseClicked { agregaFamilia() }

            Bt_cicle.setOnMouseClicked { replaceWith<Cicle>()
            }


        }
    }
}

fun agregaFamilia(){
    val c = Connexio().database
    c!!.insert(Familia){
        set(it.id,0)
        set(it.nom,"Automocio")
        set(it.descripcio,"Disciplina de la branca d'automocio")
    }
    println("S'ha procedit a afegir la familia automocio.")
}
