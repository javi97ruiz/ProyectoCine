package org.example.ventas.dto

import org.example.productos.dto.ProductoDto
import kotlinx.serialization.Serializable

/**
 * Clase que representa el dto de transporte para la linea de venta
 * @author Javier Ruiz
 * @since 1.0.0
 * @see ventas.model.LineaVenta
 * @param id representa el id de la linea de venta
 * @param producto representa el productoDto de la linea de venta
 * @param cantidad representa la cantidad del producto en la linea de venta
 * @param precio representa el precio del producto
 * @param createdAt representa cuando se creo esa linea de venta
 * @param updatedAt representa cuando se actualizo la linea de venta
 */
@Serializable
data class LineaVentaDto(
    val id: String,
    val producto: ProductoDto,
    val cantidad: Int,
    val precio: Double,
    val createdAt: String,
    val updatedAt: String,
)