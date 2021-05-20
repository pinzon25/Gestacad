package com.example.demo.app

import com.example.demo.app.Tables.Cicle
import com.example.demo.app.model.Alumnes_grups
import com.example.demo.app.model.Moduls
import com.example.demo.app.model.Ufs
import com.example.demo.controllers.*
import com.example.demo.view.Cicles
import javafx.collections.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import sun.management.jmxremote.LocalRMIServerSocketFactory
import tornadofx.*
import java.awt.image.ImageObserver.ERROR
import java.sql.SQLIntegrityConstraintViolationException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

class Main: View() {

    override val root: BorderPane by fxml()

    val mainView: Main by inject()

    //CONTROLLERS
    val grupcontroler: GrupsController by inject()
    val alumnecontroler: AlumnesController by inject()
    val ufscontroler:UfsController by inject()
    val modulscontroler:ModulsController by inject()

    val ciclesController: CiclesController by inject()

    val familiacontroler: FamiliaController by inject()

    //TABS
    val Tb_grups:Tab by fxid("Tb_grups")
    val Tb_ufs:Tab by fxid("Tb_ufs")
    val Tb_cicles:Tab by fxid("Tb_cicles")
    val Tb_families:Tab by fxid("Tb_families")
    val Tb_alumnesvista:Tab by fxid("Tb_alumnesvista")

    //Buttons
    val Bt_afegir:Button by fxid("grupsBtnAfegirAlumnes")
    val Bt_eliminar:Button by fxid("grupsBtnTreureAlumnes")

    //TEXTFIELDS
    val txtCercadorAlumnes:TextField by fxid("alumnesTfNombre")

    //COMBOBOX

    val comboBoxAlumnes:ComboBox<String?> by fxid("alumnesCbGrupos")
    val comboBoxModuls:ComboBox<String?> by fxid("ufsCbGrupos")

    //TABLEVIEWS
    val Tv_grups:javafx.scene.control.TableView<Grups> by fxid("grupsTableGrups")
    val Tv_alumne:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes")
    val Tv_alumne2:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes2")
    val Tv_ufs:javafx.scene.control.TableView<Ufs> by fxid("ufstableUFS")

    val Tv_cicles:javafx.scene.control.TableView<com.example.demo.app.model.Cicle> by fxid("ciclestableCicles")

    val Tv_families: javafx.scene.control.TableView<com.example.demo.app.Familia> by fxid("familiestableFAMILIES")
    val Tv_vistaalumne:javafx.scene.control.TableView<Alumne> by fxid("alumnesTableAlumnes")

    //COLLECTIONS
    var llistatAlumnes: MutableList<Alumne> = ArrayList()
    var llistatAlumnesId: MutableList<Alumne>? = ArrayList()
    var llistatGrups: MutableList<Grups> = ArrayList()
    var grupsAlumnes : MutableList<Alumnes_grups> = ArrayList()
    var llistatUfs:MutableList<Ufs> = ArrayList()
    var llistatModuls:MutableList<Moduls> = ArrayList()
    var nomsModuls:MutableList<String> = ArrayList()

    var llistatCicles: MutableList<com.example.demo.app.model.Cicle> = ArrayList()

    var llistatFamilies: MutableList<com.example.demo.app.Familia> = ArrayList()


    //Variables canviants i aillades.
    var grupEscollit:Grups?=null
    var modulEscollit:Moduls?=null
    var ufEscollida:Ufs?=null
    var uf:Ufs?=null
    var nomModulSeleccionat:String?=null
    var item:Grups?=null
    var ufs:Ufs?=null
    var Gru:Grups?=null
    var ind:Int?=null
    var induf:Int?= null
    var TaulaSeleccionada:Boolean?=false
    var itemDirty:Boolean? = null
    var alumneEscollit:Alumne?=null
    var modelGrup: TableViewEditModel<Grups> by singleAssign()
    var modelUfs: TableViewEditModel<Ufs> by singleAssign()


    //Cicles
    var modelCicle: TableViewEditModel<com.example.demo.app.model.Cicle> by singleAssign()
    var itemCicle:com.example.demo.app.model.Cicle?=null
    var indCicle:Int?=null
    var itemDirtyCicle:Boolean?=null
    var Cic:com.example.demo.app.model.Cicle?=null

    //Familia
    var familiaEscollida:Familia?=null
    var modelFamilia: TableViewEditModel<Familia> by singleAssign()
    var itemFamilia:Familia?=null
    var indFamilia:Int?=null
    var itemDirtyFamilia:Boolean? = null
    var Fam:Familia?=null
    //var alumneVistaEscollit:Alumne?=null

    //Alumne

    var alumneEscollida:Alumne?=null
    var modelAlumne: TableViewEditModel<Alumne> by singleAssign()
    var itemAlumne:Alumne?=null
    var indAlumne:Int?=null
    var itemDirtyAlumne:Boolean? = null
    var Alu:Alumne?=null
    var alumneVistaEscollit:Alumne?=null
    var nomAlumneSeleccionat:String?=null


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
        //println("llistat grups: "+llistatGrups)
        llistatUfs = ufscontroler.obteUfs()
        llistatModuls = modulscontroler.obteModuls()
        nomsModuls = modulscontroler.obteModulsNom()
        llistatAlumnes = alumnecontroler.obteAlumnes()
        grupsAlumnes = grupcontroler.obteGrupAlumnes()
        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())
        var u = FXCollections.observableArrayList(llistatUfs.observable())
        var m = FXCollections.observableArrayList(nomsModuls.observable())


        //Cicles
        llistatCicles = ciclesController.obteCicles()
        var eCicles = FXCollections.observableArrayList(llistatCicles.observable())


        //Families
        llistatFamilies = familiacontroler.obteFamilies()
        var eFamilies = FXCollections.observableArrayList(llistatFamilies.observable())
/*
        //Profesor
        llistatProfessor = profesorscontroler.obteProfesors()
        var e = FXCollections.observableArrayList(llistatProfessor.observable())
        //Moduls
        llistatModuls = modulscontroler.obteModuls()
        var m = FXCollections.observableArrayList(llistatModuls.observable())



        //Alumne
        llistatAlumnesNom = alumnecontroler.obteAlumnes()
        /*llistatNomGrups = alumnecontroler.obteNomGrupsAlumnes()
        var cb = FXCollections.observableArrayList(llistatNomGrups.observable())
        comboBoxAlumnes*/

*/


        //var aS = FXCollections.observableArrayList(alumnesSeleccionats.observable())
        with(root){
            with(Tb_grups) {
                with(Tv_grups) {
                    TaulaSeleccionada = false

                    Tv_grups.items = g
                    //t = tableview(g) {
                    column("ID", Grups::idProperty)
                    column("ID Cicle", Grups::idCicleProperty).makeEditable()
                    column("Nom", Grups::nomProperty).makeEditable()
                    column("Descripció", Grups::descripcioProperty).makeEditable()

                    /*contextmenu { //Afegir metode que agregui un nou grup en blanc, i que despres es pugui editar i guardar.
                        item("Esborrar").action {
                            println("Has esborrat el grup seleccionat.")
                            var nouGrup: Grups = Tv_grups.selectedItem!!
                            g.remove(nouGrup)
                        }
                    }*/

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

                        var Gr: Grups? = null
                        Gr = Tv_grups?.selectedItem
                            try {
                                item = Gr!!.copy(
                                    id = Gr.id,
                                    id_cicle = Gr.id_cicle,
                                    nom = Gr.nom,
                                    descripcio = Gr.descripcio
                                ) //Copiem les dades del objecte seleccionat a l'item Alumne, aixo ens permetra verificar canvis.

                                ind =
                                    Tv_grups?.selectionModel?.selectedIndex  //Ens permet saber l'index corresponent al alumne seleccionat al TableView.
                                println("\n\nitem actual: " + item) //Ens mostra l'objecte avans de ser modificat.
                                itemDirty = modelGrup.isDirty(Gr!!)
                                println("L'objecte amb l'index " + ind + " Is dirty?--->" + itemDirty) //Ens mostra si el model ha detectat canvis a l'objecte seleccionat en aquell moment.

                                Gr.id = Tv_grups!!.selectedItem!!.idProperty.get()
                                Gr.id_cicle = Tv_grups!!.selectedItem!!.idCicleProperty.get()
                                Gr.nom = Tv_grups!!.selectedItem!!.nomProperty.get()
                                Gr.descripcio = Tv_grups!!.selectedItem!!.descripcioProperty.get()

                                println("Alumne modificat: " + Gr) //Ens mostra l'objecte modificat.

                                Gru = Gr.copy(
                                    id = Gr.id,
                                    id_cicle = Gr.id_cicle,
                                    nom = Gr.nom,
                                    descripcio = Gr.descripcio
                                )

                                modelGrup.commit(Gru!!)
                            }catch(e:java.lang.NullPointerException){}
                    }

                    Tv_grups.onUserSelect {
                        grupEscollit = Tv_grups.selectedItem
                        println("El grup seleccionat es: " + grupEscollit)
                        llistatAlumnesId = grupcontroler.obteAlumnesIdGrup(grupEscollit!!.id)
                        println("Contingut del array d'alumnes obtinguts per grup: " + llistatAlumnesId)
                        if(llistatAlumnesId.isNullOrEmpty()) {
                            netejaTableview()
                        }else{
                            netejaTableview()
                            Tv_alumne2.items.addAll(llistatAlumnesId!!)
                        }
                    }
                }


                with(Tv_alumne) {
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
                    enableCellEditing()
                    enableDirtyTracking()
                    isEditable = true
                    prefHeight = 311.0
                    prefWidth = 246.0
                    layoutX = 530.0
                    layoutY = 100.0
                    //modelAlumnes1 = editModel
                }


                with(Tv_alumne2) {
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

                    enableCellEditing()
                    enableDirtyTracking()
                    isEditable = true
                    prefHeight = 311.0
                    prefWidth = 198.0
                    layoutX = 530.0
                    layoutY = 100.0
                    //modelAlumnes2 = editModel
                }

                Tv_alumne.onUserSelect {
                    alumneEscollit = Tv_alumne.selectedItem
                    println("Alumne escollit: " + alumneEscollit)
                }

                Bt_afegir.setOnMouseClicked {
                    println("Alumne a afegir: " + alumneEscollit)
                    grupcontroler.afegeixAlumneTaulaAlumneGrup(grupEscollit!!.id, alumneEscollit!!.id)
                    llistatAlumnesId!!.add(alumneEscollit!!)
                    var aS = FXCollections.observableArrayList(llistatAlumnesId!!.observable())
                    Tv_alumne2.items = aS
                    alumneEscollit = null
                }

                Bt_eliminar.setOnMouseClicked {
                    println("Alumne a esborrar: " + alumneEscollit)
                    grupcontroler.esborraAlumneTaulaAlumneGrup(grupEscollit!!.id, alumneEscollit!!.id)
                    llistatAlumnesId!!.remove(alumneEscollit!!)
                    //alumnesSeleccionats.remove(alumneEscollit!!)
                    var aS = FXCollections.observableArrayList(llistatAlumnesId!!.observable())
                    Tv_alumne2.items = aS
                    alumneEscollit = null
                    //Tv_alumne2.items.removeAll(aS)
                }

                Tb_grups.whenSelected { println("Has seleccionat la tab de grups")

                    workspace.refreshButton.setOnMouseClicked {
                        println("RefreshButton de la tab de Grups.")
                        llistatGrups = grupcontroler.obteGrups()
                        g=FXCollections.observableArrayList(llistatGrups.observable())
                        Tv_grups.items=g
                    }

                    workspace.saveButton.setOnMouseClicked {
                        println("saveButton de la tab de grups.")

                        var Gs: Grups? = null
                        Gs = Tv_grups.selectedItem

                        if (itemDirty == false) {
                            try {
                                grupcontroler.afegeixTaulaGrups(Gs!!)
                                modelGrup.commit()
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

                        if (itemDirty == true) {
                            grupcontroler.actualitzarTaulaGrups(Gs!!)
                            modelGrup.commit()
                            alert(
                                Alert.AlertType.INFORMATION,
                                "Grup actualitzat.",
                                "El grup s'ha actualitzat a la base de dades correctament."
                            )
                        }
                    }

                    workspace.deleteButton.setOnMouseClicked {
                        println("deleteButton de la tab de grups.")
                        var Gs: Grups? = null
                        try {
                            Gs = Tv_grups.selectedItem
                            grupcontroler.esborraTaulaGrups(Gs!!)
                            alert(
                                Alert.AlertType.INFORMATION,
                                "Grup esborrat.",
                                "El grup s'ha esborrat de la base de dades."
                            )
                            g.remove(Gs)
                            modelGrup.commit()
                        } catch (ex: SQLIntegrityConstraintViolationException) {
                            alert(
                                Alert.AlertType.ERROR,
                                "No es possible esborrar el grup.",
                                "El grup escollit no es pot esborrar, no es troba a la base de dades."
                            )
                        }
                    }


                    workspace.createButton.setOnMouseClicked {
                        println("CreateButton de la tab de grups.")
                        var nouGrup: Grups = Grups(grupcontroler.obteIdGrupMesGran() + 1, 0, "", "")
                        Tv_grups.items.add(nouGrup)
                    }
                }
            }


            with(Tb_ufs) {
                with(Tv_ufs) {
                    Tv_ufs.items = u

                    comboBoxModuls.items = m

                    column("ID", Ufs::idProperty)
                    column("ID Modul", Ufs::idmodulProperty).makeEditable()
                    column("Nom", Ufs::nomProperty).makeEditable()
                    column("Descripció", Ufs::descripcioProperty).makeEditable()

                    modelUfs=editModel

                    enableCellEditing()
                    enableDirtyTracking()

                    onUserSelect {
                        ufEscollida=Tv_ufs.selectedItem
                        println("Uf seleccionada: " + ufEscollida)
                    }

                    comboBoxModuls.setOnMouseClicked {
                        var modul: String? = null
                        nomModulSeleccionat = comboBoxModuls.selectionModel.selectedItem
                        modul = nomModulSeleccionat
                        println("Item modul seleccionat: " +nomModulSeleccionat)

                        if (nomModulSeleccionat != null) {
                            Tv_ufs.items = null
                            println("Modul seleccionat: " + modul)
                            modulEscollit = ufscontroler.obteModulPerNom(modul!!)
                            println("Modul obtingut: " + modulEscollit)
                            llistatUfs = ufscontroler.obteUfsPerModul(modulEscollit!!)
                            var aS = FXCollections.observableArrayList(llistatUfs!!.observable())
                            Tv_ufs.items = aS
                        }
                    }
                }

                modelUfs.selectedItemDirtyState.onChange {
                    var uF: Ufs? = null
                    uF = Tv_ufs?.selectedItem

                    try {
                        ufs = uF!!.copy( //ufs es un objecte buit de tipus Ufs.
                            id = uF.id,
                            id_modul = uF.id_modul,
                            nom = uF.nom,
                            descripcio = uF.descripcio
                        ) //Copiem les dades del objecte seleccionat a l'item Alumne, aixo ens permetra verificar canvis.

                        induf = Tv_ufs?.selectionModel?.selectedIndex  //Ens permet saber l'index corresponent al alumne seleccionat al TableView.
                        println("\n\nIndex Uf actual: " + induf) //Ens mostra l'objecte avans de ser modificat.
                        itemDirty = modelUfs.isDirty(uF)
                        println("La UF amb l'index " + induf + " Is dirty?--->" + itemDirty) //Ens mostra si el model ha detectat canvis a l'objecte seleccionat en aquell moment.

                        uF!!.id = Tv_ufs!!.selectedItem!!.idProperty.get()
                        uF!!.id_modul = Tv_ufs!!.selectedItem!!.idmodulProperty.get()
                        uF!!.nom = Tv_ufs!!.selectedItem!!.nomProperty.get()
                        uF!!.descripcio = Tv_ufs!!.selectedItem!!.descripcioProperty.get()

                        println("Uf modificada: " + uF) //Ens mostra l'objecte modificat.

                        uf = uF!!.copy( id = uF.id,id_modul = uF.id_modul,nom = uF.nom,descripcio = uF.descripcio)
                    }catch(e:NullPointerException){
                        println("Ha deixat d'haver una uf seleccionada.")
                    }
                    modelUfs.commit(uf!!)
                }

                Tb_ufs.whenSelected { println("Has seleccionat la tab de UFS.")
                    workspace.saveButton.setOnMouseClicked {
                        println("SaveButton de la tab de UFS.")
                        var Us:Ufs? = null
                        Us = ufEscollida
                        if(itemDirty == false){
                            try {
                                induf = ufscontroler.obteIdUfMesGran()
                                try {
                                    ufscontroler.afegeixUfs(ufEscollida!!)
                                    alert(Alert.AlertType.INFORMATION, "Uf afegida.", "La UF s'ha afegit correctament.")
                                }catch(ex:SQLIntegrityConstraintViolationException){
                                    alert(Alert.AlertType.ERROR, "No es pot afegir la Uf.", "")
                                }catch(e:NullPointerException){
                                    alert(Alert.AlertType.ERROR, "No has seleccionat cap Uf.", "")
                                }
                                modelUfs.commit()
                            } catch (ex: SQLIntegrityConstraintViolationException) {
                                alert(
                                    Alert.AlertType.ERROR,
                                    "No es possible guardar el grup.",
                                    "El grup escollit no es pot afegir, ja es troba a la base de dades."
                                )
                            }
                        }else{
                            modelUfs.commit()
                            ufscontroler.actualitzarUfs(ufEscollida!!)
                        }
                    }

                    workspace.refreshButton.setOnMouseClicked {
                        println("RefreshButton de la tab de UFS.")
                        Tv_ufs.items.clear()
                        llistatUfs = ufscontroler.obteUfs()
                        var u = FXCollections.observableArrayList(llistatUfs!!.observable())
                        Tv_ufs.items = u
                    }

                    workspace.createButton.setOnMouseClicked {
                        println("CreateButton de la tab de UFS.")
                        //println("Has afegit una nova uf.")
                        induf = ufscontroler.obteIdUfMesGran()
                        if(modulEscollit != null) { //Si hem filtrat per moduls el id_modul de la nova uf sera el del modul seleccionat.
                            Tv_ufs.items.add(Ufs(induf!! + 1, modulEscollit!!.id, "", ""))
                        }else{//Si no hem filtrat per moduls el id_modul de la nova uf sera zero.
                            Tv_ufs.items.add(Ufs(induf!! + 1, 0, "", ""))
                        }
                    }

                    workspace.deleteButton.setOnMouseClicked {
                        println("deleteButton de la tab de UFS.")
                        u.remove(ufEscollida)
                        ufscontroler.esborraUfs(ufEscollida!!.id)
                        Tv_ufs.items.remove(ufEscollida)
                        modelUfs.commit()
                    }
                }
            }


            with(Tb_cicles) {
                with(Tv_cicles) {
                    Tv_cicles.items = eCicles
                    column("ID", com.example.demo.app.model.Cicle::idProperty)
                    column("Id_Cicles", com.example.demo.app.model.Cicle::idFamiliaProperty)
                    column("Nom", com.example.demo.app.model.Cicle::nomProperty).makeEditable()
                    column("Descripció", com.example.demo.app.model.Cicle::descripcioProperty).makeEditable()

                    enableCellEditing()
                    enableDirtyTracking()
                    isEditable = true

                    modelCicle = editModel

                    modelCicle.selectedItemDirtyState.onChange {

                        var Cs: com.example.demo.app.model.Cicle? = null
                        Cs = Tv_cicles?.selectedItem

                        itemCicle = Cs!!.copy(
                            id = Cs.id,
                            nom = Cs.nom,
                            descripcio = Cs.descripcio
                        )

                        indCicle = Tv_cicles?.selectionModel?.selectedIndex
                        println("\n\nitem actual: " + itemCicle)
                        itemDirtyCicle = modelCicle.isDirty(Cs!!)
                        println("L'objecte amb l'index " + indCicle + " Is dirty?--->" + itemDirtyCicle)

                        Cs.id = Tv_cicles!!.selectedItem!!.idProperty.get()
                        Cs.id_familia = Tv_cicles!!.selectedItem!!.idFamiliaProperty.get()
                        Cs.nom = Tv_cicles!!.selectedItem!!.nomProperty.get()
                        Cs.descripcio = Tv_cicles!!.selectedItem!!.descripcioProperty.get()

                        Cic = Cs.copy(id = Cs.id, id_familia = Cs.id_familia, nom = Cs.nom, descripcio = Cs.descripcio)

                        modelCicle.commit(Cic!!)

                    }
                }
            }

            //Xavi
            with(Tb_families) {
                with(Tv_families) {
                    Tv_families.items = eFamilies
                    column("ID", com.example.demo.app.Familia::idProperty)
                    column("Nom", com.example.demo.app.Familia::nomProperty).makeEditable()
                    column("Descripció", com.example.demo.app.Familia::descripcioProperty).makeEditable()

                    /*contextmenu{
                        item("Afegir familia").action { println("Has afegit una familia nova.")
                            var novaFamilia:Familia = Familia(familiacontroler.obteIdFamiliaMesGran(),"","")
                            eFamilies.add(novaFamilia)
                        }
                    }*/

                    enableCellEditing()
                    enableDirtyTracking()
                    //onUserSelect { workspace.dock<Main>() }
                    isEditable = true

                    modelFamilia = editModel

                    //Detectem si els items han patit canvis.
                    modelFamilia.selectedItemDirtyState.onChange {

                        var Fs: Familia? = null
                        Fs = Tv_families?.selectedItem

                        itemFamilia = Fs!!.copy(
                            id = Fs.id,
                            nom = Fs.nom,
                            descripcio = Fs.descripcio
                        )

                        indFamilia = Tv_families?.selectionModel?.selectedIndex
                        println("\n\nitem actual: " + itemFamilia)
                        itemDirtyFamilia = modelFamilia.isDirty(Fs!!)
                        println("L'objecte amb l'index " + indFamilia +" Is dirty?--->"+itemDirtyFamilia)


                        Fs.id = Tv_families!!.selectedItem!!.idProperty.get()
                        Fs.nom = Tv_families!!.selectedItem!!.nomProperty.get()
                        Fs.descripcio = Tv_families!!.selectedItem!!.descripcioProperty.get()

                        println("Familia modificada: " + Fs)

                        Fam = Fs.copy(id = Fs.id, nom = Fs.nom, descripcio = Fs.descripcio)

                        modelFamilia.commit(Fam!!)
                    }

                    Tb_families.whenSelected {
                        println("Has seleccionat la tab de families")

                        workspace.saveButton.setOnMouseClicked {
                            var Fs:Familia? = null
                            Fs = Tv_families.selectedItem
                            familiacontroler.actualitzarTaulaFamilies(Fs!!)
                            modelFamilia.commit()
                            println("S'ha guardat la familia.")
                        }

                        workspace.createButton.setOnMouseClicked {
                            /*var Fs:Familia? = null
                            Fs = Tv_families.selectedItem
                            familiacontroler.afegeixFamiliaTaula(Fs!!)
                            println("S'ha creat la familia.")*/
                            var novaFamilia:Familia = Familia(familiacontroler.obteIdFamiliaMesGran(),"","")
                            familiacontroler.afegeixFamiliaTaula(novaFamilia)
                            eFamilies.add(novaFamilia)
                            modelFamilia.commit()
                            println("S'ha creat l'espai de la nova familia.")
                        }

                        workspace.deleteButton.setOnMouseClicked {
                            var Fs:Familia? = null

                            Fs = Tv_families.selectedItem
                            familiacontroler.eliminaFamiliaTaula(Fs!!)
                            eFamilies.remove(Fs!!)
                            println("S'ha eliminat la familia.")
                            modelFamilia.commit()

                        }

                        workspace.refreshButton.setOnMouseClicked {
                            println("RefreshButton de la tab de Families.")
                            Tv_families.items.clear()
                            llistatFamilies = familiacontroler.obteFamilies()
                            var fm = FXCollections.observableArrayList(llistatFamilies!!.observable())
                            Tv_families.items = fm
                        }
                    }
                }
            }//Xavi

            //Xavi
            with(Tb_alumnesvista) {
                with(Tv_vistaalumne) {
                    Tv_vistaalumne.items = a
                    column("ID", Alumne::idProperty)
                    column("Nom", Alumne::nomProperty).makeEditable()
                    column("Cognoms", Alumne::cognomsProperty).makeEditable()
                    column("Dni", Alumne::dniProperty).makeEditable()
                    column("Data naixement", Alumne::datanaixementProperty).makeEditable()
                    column("sexe", Alumne::sexeProperty).makeEditable()
                    column("Telefon", Alumne::telefonProperty).makeEditable()
                    column("Email", Alumne::emailProperty).makeEditable()
                    column("Descripció", Alumne::descripcioProperty).makeEditable()

                    enableCellEditing()
                    enableDirtyTracking()
                    isEditable = true

                    modelAlumne = editModel

                    //Detectem si els items han patit canvis.
                    modelAlumne.selectedItemDirtyState.onChange {

                        var Al: Alumne? = null
                        Al = Tv_vistaalumne?.selectedItem

                        itemAlumne = Al!!.copy(
                                id = Al.id,
                                nom = Al.nom,
                                cognoms = Al.cognoms,
                                dni = Al.dni,
                                data_naixement = Al.data_naixement,
                                sexe = Al.sexe,
                                telefon = Al.telefon,
                                email = Al.email,
                                deleted = Al.deleted,
                                descripcio = Al.descripcio
                        )

                        indAlumne = Tv_vistaalumne?.selectionModel?.selectedIndex
                        println("\n\nitem actual: " + itemAlumne)
                        itemDirtyAlumne = modelAlumne.isDirty(Al!!)
                        println("L'objecte amb l'index " + indAlumne +" Is dirty?--->"+itemDirtyAlumne)


                        Al.id = Tv_vistaalumne!!.selectedItem!!.idProperty.get()
                        Al.nom = Tv_vistaalumne!!.selectedItem!!.nomProperty.get()
                        Al.cognoms = Tv_vistaalumne!!.selectedItem!!.cognomsProperty.get()
                        Al.dni = Tv_vistaalumne!!.selectedItem!!.dniProperty.get()

                        var data:String? = Tv_vistaalumne!!.selectedItem!!.datanaixementProperty.get()
                        var formatoDelTexto:SimpleDateFormat = SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                        Al.data_naixement = formatoDelTexto.parse(data)
                        //LocalDate.parse(data)
                        //Al.data_naixement =LocalDate.parse(data)
                        // ("EE MMM dd HH:mm:ss z yyyy",
                        //                        //                                            Locale.ENGLISH)

                        Al.sexe = Tv_vistaalumne!!.selectedItem!!.sexeProperty.get()
                        Al.telefon = Tv_vistaalumne!!.selectedItem!!.telefonProperty.get()
                        Al.email = Tv_vistaalumne!!.selectedItem!!.emailProperty.get()
                        Al.deleted = Tv_vistaalumne!!.selectedItem!!.deletedProperty.get()
                        Al.descripcio = Tv_vistaalumne!!.selectedItem!!.descripcioProperty.get()

                        println("Alumne modificat: " + Al)

                        Alu = Al.copy(id = Al.id, nom = Al.nom, cognoms = Al.cognoms, dni = Al.dni, data_naixement = Al.data_naixement, sexe = Al.sexe, telefon = Al.telefon, email = Al.email, deleted = Al.deleted,descripcio = Al.descripcio)

                        modelAlumne.commit(Alu!!)
                    }

                    Tb_alumnesvista.whenSelected {
                        println("Has seleccionat la tab de Alumnes Vista")

                        workspace.saveButton.setOnMouseClicked {
                            var Al:Alumne? = null
                            Al = Tv_vistaalumne.selectedItem
                            alumnecontroler.actualitzarTaulaAlumnes(Al!!)
                            modelAlumne.commit()
                            println("S'ha guardat l'alumne.")
                        }

                        workspace.createButton.setOnMouseClicked {

                            var pattern:String = "yyyy-MM-dd";
                            var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern);

                            var date: LocalDate = LocalDate.parse("2018-09-09");
                            var defaultZoneId: ZoneId = ZoneId.of("UTC")
                            var dateDate:Date = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
                            var nouAlumne:Alumne = Alumne(alumnecontroler.obteIdAlumneMesGran(),"", "","", dateDate, "H","","", false, "")
                            alumnecontroler.afegeixAlumneTaula(nouAlumne)
                            a.add(nouAlumne)
                            modelAlumne.commit()
                            println("S'ha creat l'espai del nou alumne.")
                        }

                        workspace.deleteButton.setOnMouseClicked {
                            var Al:Alumne? = null

                            Al = Tv_vistaalumne.selectedItem
                            alumnecontroler.eliminaAlumneTaula(Al!!)
                            a.remove(Al!!)
                            println("S'ha eliminat l'alumne.")
                            modelAlumne.commit()

                        }

                        workspace.refreshButton.setOnMouseClicked {
                            println("RefreshButton de la tab de Alumnes.")
                            Tv_vistaalumne.items.clear()
                            llistatAlumnes = alumnecontroler.obteAlumnes()
                            var al = FXCollections.observableArrayList(llistatAlumnes!!.observable())
                            Tv_vistaalumne.items = al
                            txtCercadorAlumnes.clear()
                        }
                    }


                    txtCercadorAlumnes.setOnMouseClicked {
                        var alumne: String? = null
                        nomAlumneSeleccionat = txtCercadorAlumnes.getText().toString()
                        alumne = nomAlumneSeleccionat
                        println("Item alumne seleccionat: " +nomAlumneSeleccionat)

                        if (nomAlumneSeleccionat != null) {
                            Tv_vistaalumne.items = null
                            println("Nom alumne seleccionat: " + alumne)
                            //var nomAlumneEscollit:String = alumnecontroler.obteAlumnePerNom(alumne!!)
                            //println("Nom alumne obtingut: " + nomAlumneEscollit)
                            nomAlumneSeleccionat = txtCercadorAlumnes.getText().toString()
                            alumne = nomAlumneSeleccionat
                            llistatAlumnes = alumnecontroler.obteAlumnesPerNom(alumne!!)
                            var alT = FXCollections.observableArrayList(llistatAlumnes!!.observable())
                            Tv_vistaalumne.items = alT
                        }
                    }

                    /*workspace.saveButton.setOnMouseClicked {
                        var Al:Alumne? = null
                        Al = Tv_vistaalumne.selectedItem
                        alumnecontroler.actualitzarTaulaAlumnes(Al!!)
                        modelAlumne.commit()
                        println("S'ha guardat l'alumne.")
                    }

                    workspace.createButton.setOnMouseClicked {

                        var pattern:String = "yyyy-MM-dd";
                        var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern);

                        var date: LocalDate = LocalDate.parse("2018-09-09");
                        var defaultZoneId: ZoneId = ZoneId.of("UTC")
                        var dateDate:Date = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
                        var nouAlumne:Alumne = Alumne(alumnecontroler.obteIdAlumneMesGran(),"", "","", dateDate, "H","","", false, "")
                        alumnecontroler.afegeixAlumneTaula(nouAlumne)
                        a.add(nouAlumne)
                        modelAlumne.commit()
                        println("S'ha creat l'espai del nou alumne.")
                    }

                    workspace.deleteButton.setOnMouseClicked {
                        var Al:Alumne? = null

                        Al = Tv_vistaalumne.selectedItem
                        alumnecontroler.eliminaAlumneTaula(Al!!)
                        a.remove(Al!!)
                        println("S'ha eliminat l'alumne.")
                        modelAlumne.commit()

                    }

                    workspace.refreshButton.setOnMouseClicked {
                        Tv_vistaalumne.refresh()
                    }*/
                }
            } //Xavi
        }
    }

    fun netejaTableview():Unit{
        for( i in Tv_alumne2.items.indices){
            Tv_alumne2.items.remove(0,Tv_alumne2.items.lastIndex+1)
        }
    }


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

