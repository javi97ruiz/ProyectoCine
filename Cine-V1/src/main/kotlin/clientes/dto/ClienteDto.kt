package org.example.clientes.dto

import kotlinx.serialization.Serializable

/**
 * Clase que representa la dto de un cliente
 * @see clientes.models.Cliente
 * @author Javier Ruiz
 * @since 1.0.0
 * @property id representa el id de un cliente.
 * @property nombre representa el nombre de un cliente.
 * @property email representa el email de un cliente.
 * @property createdAt representa cuando se creo el cliente.
 * @property updatedAt representa cuando se actualizo la informacion del cliente.
 * @property isDeleted representa cuando se dio de baja un socio a traves de un borrado logico.
 */
@Serializable
data class ClienteDto(
    val id: String,
    val nombre: String,
    val email: String,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: Boolean = false
)