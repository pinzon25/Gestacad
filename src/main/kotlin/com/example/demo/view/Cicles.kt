package com.example.demo.view

import com.example.demo.app.MyApp
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.Workspace

class Cicles: View() {
    override val root: AnchorPane by fxml()
    val tf_familia: TextField by fxid("Tf_familia_cicle")
    val work:Workspace = MyApp().workspace
    init {

    }
}
