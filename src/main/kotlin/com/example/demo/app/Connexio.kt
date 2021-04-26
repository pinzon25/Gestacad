package com.example.demo.app
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import java.lang.reflect.InvocationTargetException
import java.sql.Connection
import java.sql.DriverManager
import org.ktorm.database.*


class Connexio {
    var database:Database?=null

    init{
        database=callDatabase()
    }
}

fun callDatabase():Database{
    val database = Database.connect(
        url = "jdbc:mysql://localhost:3306/gestacad",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "administrador1",
        password = "admin1"
    )
    return database
}


