package org.example.productos.models


class Complementos(elegible: Elegible): Producto() {
    val categoria: Elegible = elegirComplemento()

    fun elegirComplemento(): Elegible{
        println("Por favor elija si quiere un producto de la lista o si desea continuar sin complementos:")
        println("\n1.- Agua" +
                "\n2.- Refrescos" +
                "\n3.- Palomitas" +
                "\n4.- Frutos secos" +
                "\n5.- Patatas" +
                "\n6.- Salir")
        val opcion=readln()
        when (opcion){
            ("1") -> return Elegible.AGUA
            ("Agua") -> return Elegible.AGUA
           ("2") -> return Elegible.REFRESCOS
           ("Refrescos") -> return Elegible.REFRESCOS
           ("3") -> return Elegible.PALOMITAS
           ("Palomitas") -> return Elegible.PALOMITAS
           ("4") -> return Elegible.FRUTOS_SECOS
           ("Frutos secos") -> return Elegible.FRUTOS_SECOS
           ("5") -> return Elegible.PATATAS
           ("Patatas") -> return Elegible.PATATAS
            else -> return Elegible.NULO
        }
    }
}

enum class Elegible(val Precio: String) {
    AGUA("2"),
    REFRESCOS("3"),
    PALOMITAS("3"),
    FRUTOS_SECOS("2"),
    PATATAS("2,5"),
    NULO("0")
}