package org.example.productos.models

import org.example.sala.models.Sala

class Butacas(sala: Sala): Producto() {
    private val sala = Sala().sala
    private var columna = 0
    private var fila = 0
    val tipo: Tipo = Tipo.random()
    val mantenimiento: Mantenimiento = Mantenimiento.random()
    val estado: Estado = seleccionarButaca()

    fun seleccionarButaca(): Estado{
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
        TODO("Not yet implemented")
    }

    fun seleccionButaca(): Pair<Int, Int>{
        println("Por favor seleccione la butaca en el siguiente formato (fila:columna)")
        val fila = readln().split(":")[0].toInt()
        val columna = readln().split(":")[1].toInt()

        return Pair(fila, columna)
    }

}

enum class Tipo(val categoria: String) {
    NORMAL("NORMAL"),
    VIP("VIP");

    companion object {
        fun random(): Tipo {
            return random()
        }
    }
}

enum class Mantenimiento(val categoria: String) {
    ACTIVA("ACTIVA"),
    MANTENIMIENTO("MANTENIMIENTO"),
    FUERA_DE_SERVICIO("FUERA_DE_SERVICIO");

    companion object {
        fun random(): Mantenimiento {
            return random()
        }
    }
}

enum class Estado(val categoria: String) {
    LIBRE("LIBRE"),
    RESERVA("RESERVA"),
    OCUPADA("OCUPADA");

}