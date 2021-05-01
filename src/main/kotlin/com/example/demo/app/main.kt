package com.example.demo.app

import com.example.demo.app.Controllers.AlumnesController
import com.example.demo.app.Controllers.FamiliaController
import com.example.demo.app.Controllers.GrupsController
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.util.Callback
import org.ktorm.schema.Column
import tornadofx.*
import java.util.Collections.addAll
import kotlin.reflect.KProperty1

class main: View() {
    override val root: BorderPane by fxml()

    //CONTROLLERS
    val grupcontroler: GrupsController by inject()
    val alumnecontroler:AlumnesController by inject()

    //TABS
    val Tb_grups:Tab by fxid("Tb_grups")

    //TABLEVIEWS
    val Tv_grups:javafx.scene.control.TableView<Grups> by fxid("grupsTableGrups")
    val Tv_alumne:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes")
    var t:javafx.scene.control.TableView<Grups>?=null

    //TABLECOLUMNS
    val Tc_id:TableColumn<TableView<Grups>,Int> by fxid("Cl_id")
    val Tc_nom:TableColumn<TableView<Grups>,String> by fxid("Cl_nom")
    val Tc_descripcio:TableColumn<TableView<Grups>,String> by fxid("Cl_descripcio")

    //COLLECTIONS
    var llistatAlumnes: MutableList<Alumne> = ArrayList()
    var llistatGrups: MutableList<Grups> = ArrayList()



    init{
        llistatGrups = grupcontroler.obteGrups()
        println("llistat grups: "+llistatGrups)
        llistatAlumnes = alumnecontroler.obteAlumnes()
        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())
        with(root){
            with(Tv_grups) {
                Tv_grups.items=g
                //t = tableview(g) {
                    column("ID", Grups::idProperty)
                    column("Nom", Grups::nomProperty)
                    column("Descripció", Grups::descripcioProperty)
                    isEditable = true
                    prefHeight = 311.0
                    prefWidth = 246.0
                    layoutX = 370.0
                    layoutY = 100.0
               // }
                /*Tc_id.setCellValueFactory {
                   // var p:PropertyValueFactory = PropertyValueFactory<Grups,Int>("ID")*/
            }
            with(Tv_alumne){
                Tv_alumne.items = a
                column("ID", Alumne::idProperty)
                column("Nom", Alumne::nomProperty)
                column("Cognoms", Alumne::cognomsProperty)
                column("Dni", Alumne::dniProperty)
                column("Data naixement", Alumne::datanaixementProperty)
                column("sexe", Alumne::sexeProperty)
                column("Telefon", Alumne::telefonProperty)
                column("Email", Alumne::idProperty)
                column("Descripció", Alumne::descripcioProperty)
                isEditable = true
                prefHeight = 311.0
                prefWidth = 246.0
                layoutX = 530.0
                layoutY = 100.0
            }
                }



            }
        }




   //}





