package org.example.sala.models

import org.example.productos.models.Estado
import org.example.productos.models.Producto
import org.example.productos.repositories.ProductosRepository
import org.example.productos.repositories.ProductosRepositoryImpl

/**
 * Clase que representa la sala del cine
 * @author Javier Ruiz
 * @since 1.0.0
 * @param sala representa la matriz que representa la sala.
 */
class Sala {

    val sala: Array<Array<Producto?>> = Array(7){Array (5) {null} }

    /**
     * Funcion que imprime la sala
     * @author Javier Ruiz
     * @since 1.0.0
     */
        fun printSala(){
            for (i in sala.indices) {
                println()
                for (j in sala[i].indices){
                    print("[ ]")
                }
            }
        }

}