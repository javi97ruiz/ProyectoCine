package org.example.ventas.model

import org.example.productos.models.Producto
import java.time.LocalDateTime
import java.util.*

data class LineaVenta(
    val id: UUID = UUID.randomUUID(),
    val producto: Producto,
    val cantidad: Int,
    val precio: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)