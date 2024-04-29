package org.example.clientes.mappers

import database.ClienteEntity
import org.example.clientes.dto.ClienteDto
import org.example.clientes.models.Cliente
import java.time.LocalDateTime

/**
 * Funcion que nos mapea un cliente entity a un cliente
 */
fun ClienteEntity.toCliente(): Cliente {
    return Cliente(
        id = this.id,
        nombre = this.nombre,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}

/**
 * Funcion que nos mapea un cliente a un clienteDto
 */

fun Cliente.toClienteDto(): ClienteDto {
    return ClienteDto(
        id = this.id,
        nombre = this.nombre,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        email = this.email,
        isDeleted = this.isDeleted
    )
}