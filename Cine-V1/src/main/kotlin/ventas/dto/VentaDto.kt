package org.example.ventas.dto

import kotlinx.serialization.Serializable
import org.example.clientes.dto.ClienteDto
import org.example.clientes.models.Cliente
import org.example.ventas.model.LineaVenta

/**
 * Representa la clase de ventaDto
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
@Serializable
data class VentaDto(
    val id: String,
    val cliente: ClienteDto,
    val lineas: List<LineaVentaDto>,
    val total: Double,
    val createdAt: String,
    val updatedAt: String,
)