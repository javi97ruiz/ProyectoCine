package org.example.ventas.repositories

import org.example.clientes.models.Cliente
import org.example.models.ticket.model.Venta
import org.example.productos.repositories.ProductosRepository
import org.example.productos.repositories.ProductosRepositoryImpl
import java.util.*

/**
 * Interfaz que representa todas las funciones del repository de la venta
 * @author Javier Ruiz
 * @since 1.0.0
 * @see VentasRepositoryImpl
 */
interface VentasRepository {
    /**
     * Funcion que devuelve una venta en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @see VentasRepositoryImpl
     * @see VentasRepository
     * @return Devuelve la venta si la encuentra o nulo si no existe
     */
    fun findById(id: UUID): Venta?
    /**
     * Funcion que guarda una venta
     * @author Javier Ruiz
     * @since 1.0.0
     * @see VentasRepositoryImpl
     * @see VentasRepository
     * @return Devuelve la venta guardada
     */
    fun save(venta: Venta): Venta
    /**
     * Funcion que actualiza una venta en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @see VentasRepositoryImpl
     * @see VentasRepository
     * @return Devuelve la venta modificada o nulo si no existe
     */
    fun update(id: UUID, venta: Venta): Venta?
    /**
     * Funcion que elimina logicamente una venta en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @see VentasRepositoryImpl
     * @see VentasRepository
     * @return Devuelve la venta eliminada o un nulo si no existe
     */
    fun delete(id: UUID, venta: Venta): Venta?
}