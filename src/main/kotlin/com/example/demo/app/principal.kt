package com.example.demo.app

import javafx.scene.layout.AnchorPane
import org.ktorm.database.Database
import org.ktorm.dsl.insert
import tornadofx.View
import tornadofx.Workspace

class principal: View(){
    override val root: AnchorPane by fxml()


    var work:Workspace = MyApp().workspace
    /*companion object cdb{
        val c = Connexio().database
    }*/
    init {
        with(root) {
        work.createButton.setOnMouseClicked { agregaFamilia() }
        }
    }
}

fun agregaFamilia(){
    //val database = Database.connect("jdbc:mysql://localhost:3306/gestacad?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","com.mysql.cj.jdbc.Driver","administrador1","admin1")
    //val c = Connexio().database
    val c = Connexio().database
    c!!.insert(Familia){
        set(it.id_familia,0)
        set(it.nom_familia,"Automocio")
    }
    println("S'ha procedit a afegir la familia automocio.")
}