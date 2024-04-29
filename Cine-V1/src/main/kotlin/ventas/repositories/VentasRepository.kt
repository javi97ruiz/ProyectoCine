package org.example.ventas.repositories

import org.example.clientes.models.Cliente
import org.example.models.ticket.model.Venta
import java.util.*

interface VentasRepository {
    fun findById(id: UUID): Venta?
    fun save(venta: Venta): Venta
    fun update(id: UUID, venta: Venta): Venta?
    fun delete(id: UUID, venta: Venta): Venta?
}