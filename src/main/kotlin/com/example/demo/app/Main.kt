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
import java.awt.image.ImageObserver.ERROR
import java.sql.SQLIntegrityConstraintViolationException

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
    var item:Grups?=null
    var Gru:Grups?=null
    var ind:Int?=null
    var TaulaSeleccionada:Boolean?=false
    var itemDirty:Boolean? = null
    var alumneEscollit:Alumne?=null
    var modelGrup: TableViewEditModel<Grups> by singleAssign()
    var modelAlumnes1: TableViewEditModel<Alumne> by singleAssign()
    var modelAlumnes2: TableViewEditModel<Alumne> by singleAssign()

    // Profesor
    /*val profesorscontroler: ProfesorsController by inject()
    val Tv_professors: javafx.scene.control.TableView<com.example.demo.app.Professor> by fxid("professorsTableProfesores")
    var llistatProfessor: MutableList<com.example.demo.app.Professor> = ArrayList()*/
    // Moduls
   /* val modulscontroler: ModulsController by inject()
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
    var llistatNomGrups: MutableList<String?> = ArrayList()*/

    init{
        llistatGrups = grupcontroler.obteGrups()
        println("llistat grups: "+llistatGrups)
        llistatAlumnes = alumnecontroler.obteAlumnes()
        grupsAlumnes = grupcontroler.obteGrupAlumnes()
        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())
/*
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

*/

        //var aS = FXCollections.observableArrayList(alumnesSeleccionats.observable())
        with(root){

            with(Tv_grups) {
                TaulaSeleccionada=false

                Tv_grups.items=g
                //t = tableview(g) {
                column("ID", Grups::idProperty)
                column("ID Cicle", Grups::idCicleProperty).makeEditable()
                column("Nom", Grups::nomProperty).makeEditable()
                column("Descripció", Grups::descripcioProperty).makeEditable()

                contextmenu{ //Afegir metode que agregui un nou grup en blanc, i que despres es pugui editar i guardar.
                    item("Esborrar").action { println("Has esborrat el grup seleccionat.")
                    var nouGrup:Grups = Tv_grups.selectedItem!!
                        g.remove(nouGrup)
                    }
                }
                enableCellEditing()
                enableDirtyTracking()
                isEditable = true
                prefHeight = 266.0
                prefWidth = 311.0
                layoutX = 370.0
                layoutY = 100.0
                modelGrup = editModel

                //Detectem si els items han patit canvis.
                modelGrup.selectedItemDirtyState.onChange {

                    var Gr:Grups? = null
                    Gr = Tv_grups?.selectedItem

                    item = Gr!!.copy(id=Gr.id,id_cicle= Gr.id_cicle,nom=Gr.nom,descripcio=Gr.descripcio) //Copiem les dades del objecte seleccionat a l'item Alumne, aixo ens permetra verificar canvis.
                    ind = Tv_grups?.selectionModel?.selectedIndex  //Ens permet saber l'index corresponent al alumne seleccionat al TableView.
                    println("\n\nitem actual: " + item) //Ens mostra l'objecte avans de ser modificat.
                    itemDirty = modelGrup.isDirty(Gr!!)
                    println("L'objecte amb l'index " + ind +" Is dirty?--->"+itemDirty) //Ens mostra si el model ha detectat canvis a l'objecte seleccionat en aquell moment.

                    Gr.id = Tv_grups!!.selectedItem!!.idProperty.get()
                    Gr.id_cicle= Tv_grups!!.selectedItem!!.idCicleProperty.get()
                    Gr.nom = Tv_grups!!.selectedItem!!.nomProperty.get()
                    Gr.descripcio = Tv_grups!!.selectedItem!!.descripcioProperty.get()

                    println("Alumne modificat: " + Gr) //Ens mostra l'objecte modificat.

                    Gru = Gr.copy(id=Gr.id,id_cicle = Gr.id_cicle,nom=Gr.nom,descripcio = Gr.descripcio)

                    modelGrup.commit(Gru!!)
                }


                workspace.saveButton.setOnMouseClicked {
                    var Gs:Grups? = null
                    Gs = Tv_grups.selectedItem

                    if(itemDirty==false) {
                        try {
                            grupcontroler.afegeixTaulaGrups(Gs!!)
                            alert(
                                Alert.AlertType.INFORMATION,
                                "Grup emmagatzemat.",
                                "El grup s'ha emmagatzemat a la base de dades correctament."
                            )
                        } catch (ex: SQLIntegrityConstraintViolationException) {
                            alert(
                                Alert.AlertType.ERROR,
                                "No es possible guardar el grup.",
                                "El grup escollit no es pot afegir, ja es troba a la base de dades."
                            )
                        }
                    }

                    if(itemDirty==true){
                        grupcontroler.actualitzarTaulaGrups(Gs!!)
                        alert(Alert.AlertType.INFORMATION, "Grup actualitzat.", "El grup s'ha actualitzat a la base de dades correctament.")
                    }
                } //Final Workspace.SaveButton

                workspace.deleteButton.setOnMouseClicked {
                    var Gs:Grups? = null
                    try {
                        Gs = Tv_grups.selectedItem
                        grupcontroler.esborraTaulaGrups(Gs!!)
                        alert(Alert.AlertType.INFORMATION, "Grup esborrat.", "El grup s'ha esborrat de la base de dades.")
                        g.remove(Gs)
                        modelGrup.commit()
                    }catch(ex:SQLIntegrityConstraintViolationException){
                        alert(Alert.AlertType.ERROR, "No es possible esborrar el grup.", "El grup escollit no es pot esborrar, no es troba a la base de dades.")
                    }
                }//Final Workspace.DeleteButton

                //Fer que es detecti una taula, i que si es detecta, es permeti realitzar les accions amb els botons del workspace.
                workspace.createButton.setOnMouseClicked {

                    if (TaulaSeleccionada==false) {
                        alert(Alert.AlertType.ERROR, "No es possible realitzar l'accio.", "No has escollit cap taula.")
                    } else {
                        var nouGrup: Grups = Grups(grupcontroler.obteIdGrupMesGran()+1, 0, "", "")
                        g.add(nouGrup)
                    }
                }//Final Workspace.CreateButton

            }

            workspace.refreshButton.setOnMouseClicked {
                onRefresh()
            }//Final Workspace.RefreshButton
            /*with(Tv_moduls) {
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

            }*/

            with(Tv_alumne){
                Tv_alumne.items = a
                column("ID", Alumne::idProperty)
                column("Nom", Alumne::nomProperty).makeEditable()
                column("Cognoms", Alumne::cognomsProperty).makeEditable()
                column("Dni", Alumne::dniProperty).makeEditable()
                column("Data naixement", Alumne::datanaixementProperty).makeEditable()
                column("sexe", Alumne::sexeProperty).makeEditable()
                column("Telefon", Alumne::telefonProperty).makeEditable()
                column("Email", Alumne::idProperty).makeEditable()
                column("Descripció", Alumne::descripcioProperty).makeEditable()
                enableCellEditing()
                enableDirtyTracking()
                isEditable = true
                prefHeight = 311.0
                prefWidth = 246.0
                layoutX = 530.0
                layoutY = 100.0
                modelAlumnes1 = editModel


            }



            with(Tv_alumne2){
                //Tv_alumne2.items = aS
                column("ID", Alumne::idProperty)
                column("Nom", Alumne::nomProperty).makeEditable()
                column("Cognoms", Alumne::cognomsProperty).makeEditable()
                column("Dni", Alumne::dniProperty).makeEditable()
                column("Data naixement", Alumne::datanaixementProperty).makeEditable()
                column("sexe", Alumne::sexeProperty).makeEditable()
                column("Telefon", Alumne::telefonProperty).makeEditable()
                column("Email", Alumne::idProperty).makeEditable()
                column("Descripció", Alumne::descripcioProperty).makeEditable()

                enableCellEditing()
                enableDirtyTracking()
                isEditable = true
                prefHeight = 311.0
                prefWidth = 198.0
                layoutX = 530.0
                layoutY = 100.0
                modelAlumnes2 = editModel
            }

            /*with(Tv_families) {
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
            }*/

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
                grupcontroler.afegeixAlumneTaulaAlumneGrup(grupEscollit!!.id,alumneEscollit!!.id)
                llistatAlumnesId.add(alumneEscollit!!)
                var aS = FXCollections.observableArrayList(llistatAlumnesId.observable())
                Tv_alumne2.items = aS
                alumneEscollit=null
            }

            Bt_eliminar.setOnMouseClicked {
                println("Alumne a esborrar: " + alumneEscollit)
                grupcontroler.esborraAlumneTaulaAlumneGrup(grupEscollit!!.id,alumneEscollit!!.id)
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
                llistatAlumnesId = grupcontroler.obteAlumnesIdGrup(grupEscollit!!.id)
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

