package org.example.models.ticket.model

import org.example.clientes.models.Cliente
import org.example.ventas.model.LineaVenta
import java.time.LocalDateTime
import java.util.*

/**
 * Representa la clase de venta
 * @author Javier Ruiz
 * @since 1.0.0
 * @param id representa el id de la venta
 * @param cliente representa el cliente que realizado la venta
 * @see Cliente
 * @param lineas representa cada una de las lineas de venta de la venta
 * @see LineaVenta
 * @param createdAt representa cuando fue creada la compra
 * @param updatedAt representa cuando se actualizo la compra
 */
data class Venta(
    val id: UUID = UUID.randomUUID(),
    val cliente: Cliente,
    val lineas: List<LineaVenta>,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    val total: Double
        get() = lineas.sumOf { it.precio * it.cantidad }

    override fun toString(): String {
        return "Venta(id=$id, cliente=$cliente, lineas=$lineas, createdAt=$createdAt, updatedAt=$updatedAt, total=$total)"
    }
}