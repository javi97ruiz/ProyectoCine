package org.example.ventas.mappers

import database.LineaVentaEntity
import database.VentaEntity
//import org.example.clientes.mappers.toClienteDto
import org.example.clientes.models.Cliente
import org.example.models.ticket.model.Venta
//import org.example.productos.mapper.toProductoDto
import org.example.ventas.model.LineaVenta
import org.example.productos.models.Producto
import org.example.ventas.dto.LineaVentaDto
import org.example.ventas.dto.VentaDto
import java.time.LocalDateTime
import java.util.*

fun LineaVentaEntity.toLineaVenta(producto: Producto): LineaVenta {
    return LineaVenta(
        id = UUID.fromString(this.id),
        producto = producto,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDateTime.parse(this.created_at)
    )
}

fun VentaEntity.toVenta(cliente: Cliente, lineas: List<LineaVenta>): Venta {
    return Venta(
        id = UUID.fromString(this.id),
        cliente = cliente,
        lineas = lineas,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}
/*
fun LineaVenta.toLineaVentaDto(): LineaVentaDto {
    return LineaVentaDto(
        id = this.id.toString(),
        producto = this.producto.toProductoDto(),
        cantidad = this.cantidad,
        precio = this.precio,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}

fun Venta.toVentaDto(): VentaDto {
    return VentaDto(
        id = this.id.toString(),
        cliente = this.cliente.toClienteDto(),
        lineas = this.lineas.map { it.toLineaVentaDto() },
        total = this.lineas.sumOf { it.precio },
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}*/