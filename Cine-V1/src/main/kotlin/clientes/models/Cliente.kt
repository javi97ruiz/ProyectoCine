package org.example.clientes.models

import java.time.LocalDateTime

data class Cliente(
    val id: String,
    val nombre: String,
    val isDeleted: Boolean = false
)