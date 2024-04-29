package org.example.clientes.repositories

import org.example.clientes.models.Cliente

/**
 * Interfaz base del repository de clientes
 * @author Javier Ruiz
 * @since 1.0.0
 * @see ClientesRepositoryImpl
 */
    interface ClientesRepository {
        fun findAll(): List<Cliente>
        fun findById(id: String): Cliente?
        fun save(cliente: Cliente): Cliente
        fun update(id: String, cliente: Cliente): Cliente?
        fun delete(id: String): Cliente?
    }