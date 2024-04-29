package org.example.clientes.models

import java.time.LocalDateTime


/**
 * Clase que representa un cliente
 * @author Javier Ruiz
 * @since 1.0.0
 * @property id representa el id del cliente
 * @property nombre representa el nombre del cliente
 * @property isDeleted representa el borrado logico al darse de baja un cliente
 */
data class Cliente(
    val id: String,
    val nombre: String,
    val isDeleted: Boolean = false
)