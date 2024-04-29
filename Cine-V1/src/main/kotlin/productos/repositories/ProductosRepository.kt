package org.example.productos.repositories

import org.example.productos.models.Producto

interface ProductosRepository {
    fun findAll(): List<Producto>
    fun findById(id: String): Producto?
    fun findByCategoria(categoria: String): List<Producto>
    fun save(producto: Producto): Producto
    fun update(id: String, producto: Producto): Producto?
    fun delete(id: String): Producto?
}