package com.example.demo.app.Tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

/*class familia(families:String): Table<Nothing>("families") {



}*/

object Familia : Table<Nothing>("families") {
    val id_familia = int("id_familia").primaryKey()
    val nom_familia = varchar("nom_familia")//Objecte de tipus taula, en el que tenim els dos camps que composen la taula a la BBDD..
}

/*
* sealed class Employees(tableName: String) : Table<Nothing>(tableName) {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val job = varchar("job")
    val managerId = int("manager_id")
    val hireDate = date("hire_date")
    val salary = long("salary")
    val departmentId = int("department_id")
}

object RegularEmployees : Employees("t_regular_employee")

object FormerEmployees : Employees("t_former_employee")
* */