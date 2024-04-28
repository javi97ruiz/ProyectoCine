package org.example.sala.models

import org.example.productos.models.Estado
import org.example.productos.models.Producto

class Sala {

    val sala: Array<Array<Producto?>> = Array(7){Array (5) {null} }

        fun printSala(){
            for (i in sala.indices) {
                println()
                for (j in sala[i].indices){
                    print("[ ]")
                }
            }
        }

    fun seleccionarButaca(): Estado {
        println(
            "Seleccione que desea hacer:" +
                    "\nReservar Butaca," +
                    "\nDeseleccionar Butaca"
        )
        val opcion = readln()
        when (opcion) {
            ("Reservar") -> return reservarButaca()
            ("Deseleccionar") -> return deseleccionarButaca(sala)
        }
        return Estado.LIBRE
    }


    private fun deseleccionarButaca(sala: Array<Array<Producto?>>): Estado {
        val posicion = seleccionButaca()
        sala[posicion.first][posicion.second] =
            return Estado.LIBRE
    }

    private fun reservarButaca(): Estado {
        val posicion = seleccionButaca()
        sala[posicion.first][posicion.second] =
            return Estado.RESERVA
    }

    fun seleccionButaca(): Pair<Int, Int>{
        println("Por favor seleccione la butaca en el siguiente formato (fila:columna)")
        val fila = readln().split(":")[0].toInt()
        val columna = readln().split(":")[1].toInt()
        return Pair(fila, columna)
    }


}