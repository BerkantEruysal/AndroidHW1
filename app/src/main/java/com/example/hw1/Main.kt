package com.example.hw1

open class Item(val name: String, val price: Double)

class Food(val kilo: String, name: String, price: Double) : Item(name, price)

class Cloth(val type: String, name: String, price: Double) : Item(name, price)

class ShoppingList {
    private val itemList = mutableListOf<Item>()

    fun addItem(item: Item) {
        itemList.add(item)
    }

    fun displayItems() {
        if (itemList.isEmpty()) {
            println("Listeniz boş.")
            return
        }

        println("Listenizdeki ürünler:")
        itemList.forEachIndexed { index, item ->
            println("${index + 1}) ${item.name} - ${item.price} Tl")
            when (item) {
                is Food -> println("   Weight: ${item.kilo}")
                is Cloth -> println("   Type: ${item.type}")
                else -> println("   Bilinmeyen tip")
            }
        }
    }

    fun deleteItem(index: Int) {
        if (itemList.isEmpty()){
            println("Sepet boş")
            return
        }
        if (index in 1..itemList.size) {
            itemList.removeAt(index - 1)
            println("Ürün başarıyla silindi.")
        } else {
            println("Hatalı giriş! Lütfen geçerli bir numara girin.")
        }
    }
}

fun main() {
    val shoppingList = ShoppingList()
    var isExit = false

    while (!isExit) {
        println("Lütfen Yapmak istediğiniz işlemi seçin:")
        println("1- Add item")
        println("2- Display item")
        println("3- Delete item")
        println("4- Exit")
        println("Cevabınız: ")

        var response = readLine()
        while (response !in listOf("1", "2", "3", "4")) {
            println("Lütfen geçerli bir cevap giriniz!!")
            println("Cevabınız: ")
            response = readLine()
        }

        when (response) {
            "1" -> shoppingList.addItem(createItem())
            "2" -> shoppingList.displayItems()
            "3" -> deleteItem(shoppingList)
            "4" -> isExit = true
        }
    }
}

fun createItem(): Item {
    println("Ürün çeşidini seçiniz")
    println("1- Food")
    println("2- Cloth")
    println("Seçiminiz: ")
    var choice = readLine()
    while (choice !in listOf("1", "2")) {
        println("Lütfen geçerli bir seçim yapınız !")
        println("Seçiminiz: ")
        choice = readLine()
    }

    return if (choice == "1") {
        createFood()
    } else {
        createCloth()
    }
}

fun createFood(): Food {
    println("Name: ")
    val name = readLine() ?: ""

    var price: Double = 0.0
    var priceValid = false
    while (!priceValid) {
        println("Price: ")
        val priceInput = readLine()
        try {
            price = priceInput?.toDouble() ?: 0.0
            priceValid = true
        } catch (e: NumberFormatException) {
            println("Girdiğiniz değer bir double değil. Lütfen tekrar girin:")
        }
    }

    println("Kilo: ")
    val weight = readLine() ?: ""

    return Food(weight, name, price)
}

fun createCloth(): Cloth {
    println("Name: ")
    val name = readLine() ?: ""

    var price: Double = 0.0
    var priceValid = false
    while (!priceValid) {
        println("Price: ")
        val priceInput = readLine()
        try {
            price = priceInput?.toDouble() ?: 0.0
            priceValid = true
        } catch (e: NumberFormatException) {
            println("Girdiğiniz değer bir double değil. Lütfen tekrar girin:")
        }
    }

    println("Type: ")
    val type = readLine() ?: ""

    return Cloth(type, name, price)
}


fun deleteItem(shoppingList: ShoppingList) {
    shoppingList.displayItems()

    println("Lütfen silmek istediğiniz ürünün numarasını girin:")
    val index = readLine()?.toIntOrNull() ?: -1
    shoppingList.deleteItem(index)
}
