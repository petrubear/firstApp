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


data class Person(val name: String, val age: Int)

fun testDataClass(list: List<Person>) {
    for ((name, age) in list) {
        print(name)
        print(age)
    }
}


//// lambda
fun ltest() {
    val sum = { x: Int, y: Int -> x + y }
    val mul = { x: Int, y: Int -> x * y }
    applyOp(3, 5, sum)
    applyOp(3, 5, mul)
    applyOp(5, 3) { a, b -> a - b }
}

fun applyOp(a: Int, b: Int, function: (Int, Int) -> Int): Int {
    return function(a, b)
}


interface Callback {
    fun onCallback(result: String)
}

fun doAsync(x: Int, callback: Callback) {
    callback.onCallback("finished")
}

fun doAsyncLambda(x: Int, func: (String) -> Unit) {
    func("Finished")
}

fun cbTest() {
    doAsync(20, object : Callback {
        override fun onCallback(result: String) {
            print(result)
        }
    })

    doAsyncLambda(20) { h -> print(h) }
}


/// Collections

fun testCollection(items: List<MediaItem>) {
    //val photos = items.filter { item -> item.type == MediaItem.MediaType.PHOTO }.sortedBy { item -> item.title }

    // esta lista es inmutable
    val photoUrls: List<String> = items
        .filter { it.type == MediaItem.MediaType.PHOTO }
        .sortedBy { it.title }
        .map { it.thumbUrl }

    val mutableList = mutableListOf<Int>(1, 2, 3, 4)
}