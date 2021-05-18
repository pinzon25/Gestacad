package com.example.demo.app

import com.example.demo.app.model.Alumnes_grups
import com.example.demo.app.model.Moduls
import com.example.demo.app.model.Ufs
import com.example.demo.controllers.*
import javafx.collections.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.sql.SQLIntegrityConstraintViolationException

class Main: View() {

    override val root: BorderPane by fxml()

    //CONTROLLERS
    val grupcontroler: GrupsController by inject()
    val alumnecontroler: AlumnesController by inject()
    val ufscontroler:UfsController by inject()
    val modulscontroler:ModulsController by inject()
    val profesorscontroler: ProfesorsController by inject()

    //TABS
    val Tb_grups:Tab by fxid("Tb_grups")
    val Tb_ufs:Tab by fxid("Tb_ufs")
    val Tb_moduls:Tab by fxid("Tb_moduls")

    //Buttons
    val Bt_afegir:Button by fxid("grupsBtnAfegirAlumnes")
    val Bt_eliminar:Button by fxid("grupsBtnTreureAlumnes")

    //TEXTFIELDS
    val txtCercadorAlumnes:TextField by fxid("alumnesTfNombre")

    //COMBOBOX

    val comboBoxAlumnes:ComboBox<String?> by fxid("alumnesCbGrupos")
    val comboBoxCicle:ComboBox<String?> by fxid("ufsCbGrupos")


    //TABLEVIEWS
    val Tv_grups:javafx.scene.control.TableView<Grups> by fxid("grupsTableGrups")
    val Tv_alumne:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes")
    val Tv_alumne2:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes2")
    val Tv_ufs:javafx.scene.control.TableView<Ufs> by fxid("ufstableUFS")
    val Tv_moduls:javafx.scene.control.TableView<Moduls> by fxid("modulstableMODULS")
    val Tv_professors: javafx.scene.control.TableView<com.example.demo.app.Professor> by fxid("professorsTableProfesores")


    //COLLECTIONS
    var llistatAlumnes: MutableList<Alumne> = ArrayList()
    var llistatAlumnesId: MutableList<Alumne>? = ArrayList()
    var llistatGrups: MutableList<Grups> = ArrayList()
    var llistatProfessor: MutableList<com.example.demo.app.Professor> = ArrayList()
    var grupsAlumnes : MutableList<Alumnes_grups> = ArrayList()
    var llistatUfs:MutableList<Ufs> = ArrayList()
    var llistatModuls:MutableList<Moduls> = ArrayList()
    var nomsModuls:MutableList<String> = ArrayList()

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
    var modelModuls: TableViewEditModel<Moduls> by singleAssign()
    var indMod:Int?= null
    var modul:Moduls?=null



   /*
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
        llistatProfessor = profesorscontroler.obteProfesors()

        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())
        var u = FXCollections.observableArrayList(llistatUfs.observable())
        var m = FXCollections.observableArrayList(nomsModuls.observable())
        var o = FXCollections.observableArrayList(llistatModuls.observable())
        var e = FXCollections.observableArrayList(llistatProfessor.observable())

/*


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
            with(Tb_grups) {
                with(Tv_grups) {
                    TaulaSeleccionada = false

                    Tv_grups.items = g
                    //t = tableview(g) {
                    column("ID", Grups::idProperty)
                    column("ID Cicle", Grups::idCicleProperty).makeEditable()
                    column("Nom", Grups::nomProperty).makeEditable()
                    column("Descripció", Grups::descripcioProperty).makeEditable()

                    contextmenu { //Afegir metode que agregui un nou grup en blanc, i que despres es pugui editar i guardar.
                        item("Esborrar").action {
                            println("Has esborrat el grup seleccionat.")
                            var nouGrup: Grups = Tv_grups.selectedItem!!
                            g.remove(nouGrup)
                        }
                        /*item("Refrescar").action {
                            //Tv_grups.items=null
                            llistatGrups = grupcontroler.obteGrups()
                            g=FXCollections.observableArrayList(llistatGrups.observable())
                            Tv_grups.items=g
                        }*/
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

                        var Gr: Grups? = null
                        Gr = Tv_grups?.selectedItem

                        item = Gr!!.copy(
                            id = Gr.id,
                            id_cicle = Gr.id_cicle,
                            nom = Gr.nom,
                            descripcio = Gr.descripcio
                        ) //Copiem les dades del objecte seleccionat a l'item Alumne, aixo ens permetra verificar canvis.

                        ind = Tv_grups?.selectionModel?.selectedIndex  //Ens permet saber l'index corresponent al alumne seleccionat al TableView.
                        println("\n\nitem actual: " + item) //Ens mostra l'objecte avans de ser modificat.
                        itemDirty = modelGrup.isDirty(Gr!!)
                        println("L'objecte amb l'index " + ind + " Is dirty?--->" + itemDirty) //Ens mostra si el model ha detectat canvis a l'objecte seleccionat en aquell moment.

                        Gr.id = Tv_grups!!.selectedItem!!.idProperty.get()
                        Gr.id_cicle = Tv_grups!!.selectedItem!!.idCicleProperty.get()
                        Gr.nom = Tv_grups!!.selectedItem!!.nomProperty.get()
                        Gr.descripcio = Tv_grups!!.selectedItem!!.descripcioProperty.get()

                        println("Alumne modificat: " + Gr) //Ens mostra l'objecte modificat.

                        Gru = Gr.copy(id = Gr.id, id_cicle = Gr.id_cicle, nom = Gr.nom, descripcio = Gr.descripcio)

                        modelGrup.commit(Gru!!)
                    }

                    workspace.saveButton.setOnMouseClicked {
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
                            var nouGrup: Grups = Grups(grupcontroler.obteIdGrupMesGran() + 1, 0, "", "")
                            Tv_grups.items.add(nouGrup)
                        }


                    Tv_grups.onUserSelect {
                        grupEscollit = Tv_grups.selectedItem
                        println("El grup seleccionat es: " + grupEscollit)
                        llistatAlumnesId = grupcontroler.obteAlumnesIdGrup(grupEscollit!!.id)
                        println("Contingut del array d'alumnes obtinguts per grup: " + llistatAlumnesId)
                        if(llistatAlumnesId.isNullOrEmpty()) {
                            netejaTableview()
                        }else{
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
            }
            with(Tb_ufs) {
                with(Tv_ufs) {
                    Tv_ufs.items = u

                    comboBoxCicle.items = m

                    column("ID", Ufs::idProperty)
                    column("ID Modul", Ufs::idmodulProperty).makeEditable()
                    column("Nom", Ufs::nomProperty).makeEditable()
                    column("Descripció", Ufs::descripcioProperty).makeEditable()

                    contextmenu {
                        item("Esborrar").action {
                            u.remove(ufEscollida)
                            ufscontroler.esborraUfs(ufEscollida!!.id)
                            Tv_ufs.items.remove(ufEscollida)
                            modelUfs.commit()
                        }

                        item("Afegir nova uf").action {
                            println("Has afegit una nova uf.")
                            induf = ufscontroler.obteIdUfMesGran()
                            if(modulEscollit != null) { //Si hem filtrat per moduls el id_modul de la nova uf sera el del modul seleccionat.
                                Tv_ufs.items.add(Ufs(induf!! + 1, modulEscollit!!.id, "", ""))
                            }else{//Si no hem filtrat per moduls el id_modul de la nova uf sera zero.
                                Tv_ufs.items.add(Ufs(induf!! + 1, 0, "", ""))
                            }
                        }

                        item("Guardar Uf").action {
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
                        }

                        item("Actualitzar").action {
                            //guardaCanvis(Tv_ufs.selectedItem!!)
                            modelUfs.commit()
                            ufscontroler.actualitzarUfs(ufEscollida!!)
                        }
                    }

                    modelUfs=editModel

                    enableCellEditing()
                    enableDirtyTracking()

                    onUserSelect {
                        ufEscollida=Tv_ufs.selectedItem
                        println("Uf seleccionada: " + ufEscollida)
                    }

                    workspace.refreshButton.setOnMouseClicked {
                        Tv_ufs.items.clear()
                        llistatUfs = ufscontroler.obteUfs()
                        var u = FXCollections.observableArrayList(llistatUfs!!.observable())
                        Tv_ufs.items = u
                    }

                    comboBoxCicle.setOnMouseClicked {
                        var modul: String? = null
                        nomModulSeleccionat = comboBoxCicle.selectionModel.selectedItem
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
            }
            with(Tv_moduls) {
                    Tv_moduls.items = o
                    column("ID", Moduls::idProperty)
                    column("ID Cicle", Moduls::idCicleProperty).makeEditable()
                    column("Nom", Moduls::nomProperty).makeEditable()
                    column("Descripció", Moduls::descripcioProperty).makeEditable()

                    contextmenu {
                        item("Esborrar").action {
                            o.remove(modulEscollit)
                            modulscontroler.esborraModuls(modulEscollit!!.id)
                            Tv_moduls.items.remove(modulEscollit)
                            modelModuls.commit()
                        }
                    }

                    modelModuls=editModel

                    enableCellEditing()
                    enableDirtyTracking()
                    onUserSelect {
                        ufEscollida=Tv_ufs.selectedItem
                        println("Uf seleccionada: " + ufEscollida)
                    }

                    workspace.refreshButton.setOnMouseClicked {
                        Tv_moduls.items.clear()
                        llistatModuls = modulscontroler.obteModuls()
                        var o = FXCollections.observableArrayList(llistatModuls!!.observable())
                        Tv_moduls.items = o
                    }
                modelModuls.selectedItemDirtyState.onChange {
                    var mO: Moduls? = null
                    mO = Tv_moduls?.selectedItem

                    try {
                        modul = mO!!.copy(
                            id = mO.id,
                            id_cicle = mO.id_cicle,
                            nom = mO.nom,
                            descripcio = mO.descripcio
                        )

                        indMod = Tv_moduls?.selectionModel?.selectedIndex
                        println("\n\nIndex Modul actual: " + indMod) //Ens mostra l'objecte avans de ser modificat.
                        itemDirty = modelModuls.isDirty(mO)
                        println("EL modul amb l'index " + indMod + " Is dirty?--->" + itemDirty) //Ens mostra si el model ha detectat canvis a l'objecte seleccionat en aquell moment.

                        mO!!.id = Tv_moduls!!.selectedItem!!.idProperty.get()
                        mO!!.id_cicle = Tv_moduls!!.selectedItem!!.idCicleProperty.get()
                        mO!!.nom = Tv_moduls!!.selectedItem!!.nomProperty.get()
                        mO!!.descripcio = Tv_moduls!!.selectedItem!!.descripcioProperty.get()

                        println("Uf modificada: " + mO) //Ens mostra l'objecte modificat.

                        mO = mO!!.copy( id = mO.id,id_cicle = mO.id_cicle,nom = mO.nom,descripcio = mO.descripcio)
                    }catch(e:NullPointerException){
                        println("Ha deixat d'haver un modul seleccionada.")
                    }
                    modelModuls.commit(mO!!)
                }

                }
            with(Tv_professors) {
                Tv_professors.items = e
                column("ID", com.example.demo.app.Professor::idProperty).isEditable
                column("Nom", com.example.demo.app.Professor::nomProperty).makeEditable()
                column("Cognoms", com.example.demo.app.Professor::cognomsProperty).makeEditable()
                column("Dni", com.example.demo.app.Professor::dniProperty).makeEditable()
                column("Data naixement", com.example.demo.app.Professor::datanaixementProperty).makeEditable()
                column("sexe", com.example.demo.app.Professor::sexeProperty).makeEditable()
                column("Telefon", com.example.demo.app.Professor::telefonProperty).makeEditable()
                column("Email", com.example.demo.app.Professor::idProperty).makeEditable()
                column("Descripció", com.example.demo.app.Professor::descripcioProperty).makeEditable()
            }




            }
    }

    fun netejaTableview():Unit{
        for( i in Tv_alumne2.items.indices){
            Tv_alumne2.items.remove(0,Tv_alumne2.items.lastIndex+1)
        }
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

