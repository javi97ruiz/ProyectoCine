package org.example.productos.models

import org.example.sala.models.Sala

/**
 * Clase que representa las butacas y sus estados
 * @author Javier Ruiz
 * @since 1.0.0
 * @see Producto
 * @param sala representa la sala del cine para colocar las butacas
 * @param columna representa la columna de la butaca en la sala
 * @param fila representa la fila en la que se encuentra la butaca
 * @param tipo representa el tipo de butaca que es
 * @see Tipo
 * @param mantenimiento representa si una butaca se puede utilizar o no
 * @see Mantenimiento
 * @param estado representa si la butaca esta reservada o no
 * @see Estado
 */
class Butacas(sala: Sala): Producto() {
    private val sala = Sala().sala
    private var columna = 0
    private var fila = 0
    val tipo: Tipo = Tipo.random()
    val mantenimiento: Mantenimiento = Mantenimiento.random()
    val estado: Estado = seleccionarButaca()


    /**
     * Funcion que representa la seleccion de una butaca para realizar la reserva o devolucion de la misma
     * @author Javier Ruiz
     * @since 1.0.0
     * @return devuelve el estado libre si no se selecciona nada.
     */
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


    /**
     * Funcion que devuelve una butaca en caso de tenerla reservada
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve un estado libre si no se ha encontrado la butaca
     */
    private fun deseleccionarButaca(sala: Array<Array<Producto?>>): Estado {
        val posicion = seleccionButaca()
        sala[posicion.first][posicion.second] =
        return Estado.LIBRE


    }

    /**
     * Funcion que sirve para reservar una butaca
     * @author Javier Ruiz
     * @since 1.0.0
     * @return devuelve el estado libre si no se ha encontrado la butaca
     */
    private fun reservarButaca(): Estado {
        TODO("Not yet implemented")
    }

    /**
     * Funcion que sirve para seleccionar una butaca
     * @author Javier Ruiz
     * @since 1.0.0
     * @return devuelve la fila y columna de la butaca.
     */
    fun seleccionButaca(): Pair<Int, Int>{
        println("Por favor seleccione la butaca en el siguiente formato (fila:columna)")
        val fila = readln().split(":")[0].toInt()
        val columna = readln().split(":")[1].toInt()

        return Pair(fila, columna)
    }

}

/**
 * Clase que representa la categoria de la butaca
 * @author Javier Ruiz
 * @since 1.0.0
 * @see Butacas
 */
enum class Tipo(val categoria: String) {
    NORMAL("NORMAL"),
    VIP("VIP");

    companion object {
        fun random(): Tipo {
            return random()
        }
    }
}

/**
 * Clase que representa si una butaca esta en disposicion de utilizarse
 * @author Javier Ruiz
 * @since 1.0.0
 * @see Butacas
 */
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

/**
 * Clase que representa la disponibilidad de una butaca
 * @author Javier Ruiz
 * @since 1.0.0
 * @see Butacas
 */
enum class Estado(val categoria: String) {
    LIBRE("LIBRE"),
    RESERVA("RESERVA"),
    OCUPADA("OCUPADA");

}