package org.example.productos.dto

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Clase que representa el objeto de transporte para el producto
 * @author Javier Ruiz
 * @since 1.0.0
 * @see productos.models.Producto
 * @param id representa el id del producto
 * @param nombre representa el nombre del producto
 * @param precio representa el precio del producto
 * @param stock representa el stock del producto
 * @param categoria representa la categoria a la que pertenece el producto
 * @param createdAt representa la fecha en la que se creo el producto
 * @param updatedAt representa la fecha en la que se actualizo el producto
 * @param isDeleted representa si se ha realizado el borrado logico de un producto.
 */
@Serializable
data class ProductoDto(
    val id: Long = -1,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String = LocalDateTime.now().toString(),
    val isDeleted: Boolean = false
)