package com.example.firstapp

open class Persona(name: String = "Peter", val age: Int = 10) {
    var name: String = name
        get() = "Name: $field"
        set(value) {
            if (value != field) {
                field = value
            }
        }
}

//class Developer(name: String) : Persona(name, 20)
class Developer : Persona {
    constructor(name: String) : super(name)
    constructor(age: Int) : super(age = age)

}

fun test() {
    var person = Persona("edison", 38)
    var peter = Persona(age = 20)
    var tom = Persona("tom")
    var developer = Developer("carlos")

    val age = person.age
    val name = person.name
}