package com.example.demo.app

import com.example.demo.view.MainView
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import tornadofx.App
import tornadofx.UIComponent
import tornadofx.Workspace
import java.sql.DriverManager

class MyApp: App(Workspace::class){
    /*fun main(args: Array<String>) {
        Connexio().database
    }*/
    init{

    }
    override fun onBeforeShow(view: UIComponent) {
        workspace.dock<main>() //En cas de que dongui error de compilador de kotlin, anar a Settings, compiler, kotlin compiler, i escollir la versio 1.8 de Target JVM version.


    }
}
