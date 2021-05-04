package com.example.demo.app

import tornadofx.App
import tornadofx.UIComponent
import tornadofx.Workspace

class MyApp: App(Workspace::class){
    /*fun Main(args: Array<String>) {
        Connexio().database
    }*/
    init{

    }
    override fun onBeforeShow(view: UIComponent) {
        workspace.dock<Main>() //En cas de que dongui error de compilador de kotlin, anar a Settings, compiler, kotlin compiler, i escollir la versio 1.8 de Target JVM version.


    }
}
