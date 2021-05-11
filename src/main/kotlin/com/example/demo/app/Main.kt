package com.example.demo.app

import com.example.demo.app.model.Alumnes_grups
import com.example.demo.app.model.Moduls
import com.example.demo.controllers.AlumnesController
import com.example.demo.controllers.FamiliaController
import com.example.demo.controllers.GrupsController
import com.example.demo.controllers.ModulsController
import com.example.demo.controllers.ProfesorsController
import javafx.collections.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import tornadofx.*

class Main: View() {

    override val root: BorderPane by fxml()

    //CONTROLLERS
    val grupcontroler: GrupsController by inject()
    val alumnecontroler: AlumnesController by inject()

    //TABS
    val Tb_grups:Tab by fxid("Tb_grups")

    //Buttons
    val Bt_afegir:Button by fxid("grupsBtnAfegirAlumnes")
    val Bt_eliminar:Button by fxid("grupsBtnTreureAlumnes")

    //TEXTFIELDS
    val txtCercadorAlumnes:TextField by fxid("alumnesTfNombre")

    //COMBOBOX

    val comboBoxAlumnes:ComboBox<String?> by fxid("alumnesCbGrupos")

    //TABLEVIEWS
    val Tv_grups:javafx.scene.control.TableView<Grups> by fxid("grupsTableGrups")
    val Tv_alumne:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes")
    val Tv_alumne2:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes2")

    //TABLECOLUMNS
    /*val Tc_id:TableColumn<TableView<Grups>,Int> by fxid("Cl_id")
    val Tc_nom:TableColumn<TableView<Grups>,String> by fxid("Cl_nom")
    val Tc_descripcio:TableColumn<TableView<Grups>,String> by fxid("Cl_descripcio")*/

    //COLLECTIONS
    var llistatAlumnes: MutableList<Alumne> = ArrayList()
    var llistatAlumnesId: MutableList<Alumne> = ArrayList()
    var llistatAlumnesNom: MutableList<Alumne> = ArrayList()
    var llistatGrups: MutableList<Grups> = ArrayList()
    var grupsAlumnes : MutableList<Alumnes_grups> = ArrayList()

    var alumnesSeleccionats: MutableList<Alumne> = ArrayList()

    //Variables canviants i aillades.
    var grupEscollit:Grups?=null
    var alumneEscollit:Alumne?=null

    // Profesor
    val profesorscontroler: ProfesorsController by inject()
    val Tv_professors: javafx.scene.control.TableView<com.example.demo.app.Professor> by fxid("professorsTableProfesores")
    var llistatProfessor: MutableList<com.example.demo.app.Professor> = ArrayList()
    // Moduls
    val modulscontroler: ModulsController by inject()
    val Tv_moduls: javafx.scene.control.TableView<Moduls> by fxid("modulstableMODULS")
    var llistatModuls: MutableList<Moduls> = ArrayList()

    // Families
    val familiacontroler: FamiliaController by inject()
    val Tv_families: javafx.scene.control.TableView<com.example.demo.app.Familia> by fxid("familiestableFAMILIES")
    var llistatFamilies: MutableList<com.example.demo.app.Familia> = ArrayList()
    var familiaEscollida:Familia?=null

    //Vista Alumnes
    val Tv_vistaalumne:javafx.scene.control.TableView<Alumne> by fxid("alumnesTableAlumnes")
    var alumneVistaEscollit:Alumne?=null
    var llistatNomGrups: MutableList<String?> = ArrayList()

    init{
        llistatGrups = grupcontroler.obteGrups()
        println("llistat grups: "+llistatGrups)
        llistatAlumnes = alumnecontroler.obteAlumnes()
        grupsAlumnes = grupcontroler.obteGrupsAlumne()
        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())

        //Profesor
        llistatProfessor = profesorscontroler.obteProfesors()
        var e = FXCollections.observableArrayList(llistatProfessor.observable())
        //Moduls
        llistatModuls = modulscontroler.obteModuls()
        var m = FXCollections.observableArrayList(llistatModuls.observable())

        //Families
        llistatFamilies = familiacontroler.obteFamilies()
        var eFamilies = FXCollections.observableArrayList(llistatFamilies.observable())

        //Alumne
        llistatAlumnesNom = alumnecontroler.obteAlumnes()
        /*llistatNomGrups = alumnecontroler.obteNomGrupsAlumnes()
        var cb = FXCollections.observableArrayList(llistatNomGrups.observable())
        comboBoxAlumnes*/



        //var aS = FXCollections.observableArrayList(alumnesSeleccionats.observable())
        with(root){

            with(Tv_grups) {
                Tv_grups.items=g
                //t = tableview(g) {
                    column("ID", Grups::idProperty).isEditable
                    column("Nom", Grups::nomProperty).isEditable
                    column("Descripció", Grups::descripcioProperty).isEditable
                    isEditable = true
                    prefHeight = 266.0
                    prefWidth = 311.0
                    layoutX = 370.0
                    layoutY = 100.0
               // }

                    /*Fer que quan el usuari seleccioni el grup s'obtingui el id d'aquell grup, d'aquell grup buscar el id dels alumnes que te, i buscar cada id al
                     arraylist d'alumnes, i aquells alumnes afegirlos al arraylist de "alumnesSeleccionats"*/

            }
            with(Tv_moduls) {
                Tv_moduls.items=m
                //t = tableview(g) {
                column("ID", Moduls::idProperty).isEditable
                column("Nom", Moduls::nomProperty).isEditable
                column("Descripció", Moduls::descripcioProperty).isEditable

            }
            with(Tv_professors) {
                Tv_professors.items = e
                column("ID", com.example.demo.app.Professor::idProperty).isEditable
                column("Nom", com.example.demo.app.Professor::nomProperty).isEditable
                column("Cognoms", com.example.demo.app.Professor::cognomsProperty).isEditable
                column("Dni", com.example.demo.app.Professor::dniProperty).isEditable
                column("Data naixement", com.example.demo.app.Professor::datanaixementProperty).isEditable
                column("sexe", com.example.demo.app.Professor::sexeProperty).isEditable
                column("Telefon", com.example.demo.app.Professor::telefonProperty).isEditable
                column("Email", com.example.demo.app.Professor::idProperty).isEditable
                column("Descripció", com.example.demo.app.Professor::descripcioProperty).isEditable

            }

            with(Tv_alumne){
                Tv_alumne.items = a
                column("ID", Alumne::idProperty).isEditable
                column("Nom", Alumne::nomProperty).isEditable
                column("Cognoms", Alumne::cognomsProperty).isEditable
                column("Dni", Alumne::dniProperty).isEditable
                column("Data naixement", Alumne::datanaixementProperty).isEditable
                column("sexe", Alumne::sexeProperty).isEditable
                column("Telefon", Alumne::telefonProperty).isEditable
                column("Email", Alumne::idProperty).isEditable
                column("Descripció", Alumne::descripcioProperty).isEditable
                isEditable = true
                prefHeight = 311.0
                prefWidth = 246.0
                layoutX = 530.0
                layoutY = 100.0
            }

            with(Tv_alumne2){
                //Tv_alumne2.items = aS
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
                prefWidth = 198.0
                layoutX = 530.0
                layoutY = 100.0
            }

            with(Tv_families) {
                Tv_families.items = eFamilies
                column("ID", com.example.demo.app.Familia::idProperty).isEditable
                column("Nom", com.example.demo.app.Familia::nomProperty).isEditable
                column("Descripció", com.example.demo.app.Familia::descripcioProperty).isEditable

            }

            with(Tv_vistaalumne){
                Tv_vistaalumne.items = a
                column("ID", Alumne::idProperty)
                column("Nom", Alumne::nomProperty)
                column("Cognoms", Alumne::cognomsProperty)
                column("Dni", Alumne::dniProperty)
                column("Data naixement", Alumne::datanaixementProperty)
                column("sexe", Alumne::sexeProperty)
                column("Telefon", Alumne::telefonProperty)
                column("Email", Alumne::idProperty)
                column("Descripció", Alumne::descripcioProperty)
            }

            //Familia seleccionada
            Tv_families.onUserSelect {
                familiaEscollida=Tv_families.selectedItem
                println("Familia escollida: " + familiaEscollida)
            }

            //Alumne seleccionada
            Tv_vistaalumne.onUserSelect {
                alumneVistaEscollit=Tv_vistaalumne.selectedItem
                println("Alumne escollit: " + alumneVistaEscollit)
            }

            Tv_grups.onUserSelect {
                grupEscollit = Tv_grups.selectedItem
                println("El grup seleccionat es: " + grupEscollit)
                mostraAlumnesGrup()
                var aS = FXCollections.observableArrayList(llistatAlumnesId.observable())
                Tv_alumne2.items = aS
            }

            Tv_alumne.onUserSelect {
                alumneEscollit=Tv_alumne.selectedItem
                println("Alumne escollit: " + alumneEscollit)
            }

            Bt_afegir.setOnMouseClicked {
                println("Alumne a afegir: " + alumneEscollit)
                grupcontroler.afegeixAlumneTaula(grupEscollit!!.id,alumneEscollit!!.id)
                llistatAlumnesId.add(alumneEscollit!!)
                var aS = FXCollections.observableArrayList(llistatAlumnesId.observable())
                Tv_alumne2.items = aS
                alumneEscollit=null
            }

            Bt_eliminar.setOnMouseClicked {
                println("Alumne a esborrar: " + alumneEscollit)
                grupcontroler.esborraAlumneTaula(grupEscollit!!.id,alumneEscollit!!.id)
                llistatAlumnesId.remove(alumneEscollit!!)
                //alumnesSeleccionats.remove(alumneEscollit!!)
                var aS = FXCollections.observableArrayList(llistatAlumnesId.observable())
                Tv_alumne2.items=aS
                alumneEscollit=null
                //Tv_alumne2.items.removeAll(aS)
            }
                }
            }
    fun mostraAlumnesGrup():Unit{
        for(i in grupsAlumnes.indices){
            if(grupEscollit!!.id==grupsAlumnes[i].id_grup){
                llistatAlumnesId = grupcontroler.obteAlumnesGrups(grupEscollit!!.id)
            }
        }
        println("Alumnes obtinguts que pertanyen al grup seleccionat: " + llistatAlumnesId)
    }

    /*fun mostraAlumnesGrupchBox():Unit{
        for(i in grupsAlumnes.indices){
            if(grupEscollit!!.id==grupsAlumnes[i].id_grup){
                llistatAlumnesId = grupcontroler.obteAlumnesGrups(grupEscollit!!.id)
            }
        }
    }



    fun mostraAlumnesbyNomCognom():Unit {
        var nomAlumne = txtCercadorAlumnes.getText().toString()
        llistatAlumnesNom = alumnecontroler.obteAlumnePerNom(nomAlumne)

    }*/
}

