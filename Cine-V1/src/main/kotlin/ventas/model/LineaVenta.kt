package org.example.ventas.model

import org.example.productos.models.Producto
import java.time.LocalDateTime
import java.util.*

/**
 * Clase que representa el dto de transporte para la linea de venta
 * @author Javier Ruiz
 * @since 1.0.0
 * @see LineaVentaDto
 * @param id representa el id de la linea de venta
 * @param producto representa el productoDto de la linea de venta
 * @param cantidad representa la cantidad del producto en la linea de venta
 * @param precio representa el precio del producto
 * @param createdAt representa cuando se creo esa linea de venta
 * @param updatedAt representa cuando se actualizo la linea de venta
 */
data class LineaVenta(
    val id: UUID = UUID.randomUUID(),
    val producto: Producto,
    val cantidad: Int,
    val precio: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)