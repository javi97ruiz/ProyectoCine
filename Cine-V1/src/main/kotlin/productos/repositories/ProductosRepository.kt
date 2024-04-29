package org.example.productos.repositories

import org.example.productos.models.Producto

/**
 * Interfaz que representa el repository para las operaciones
 * @author Javier Ruiz
 * @since 1.0.0
 * @see ProductosRepositoryImpl
 */
interface ProductosRepository {
    /**
     * Funcion que sirve para buscar todos los productos
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return devuelve la lista de todos los productos
     */
    fun findAll(): List<Producto>
    /**
     * Funcion que devuelve un producto buscado por un id o un nulo si no existe
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return devuelve el producto con el id establecido o un nulo si no existe
     */
    fun findById(id: String): Producto?
    /**
     * Funcion que devuelve los productos de una categoria
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return Devuelve todos los productos de una categoria
     */
    fun findByCategoria(categoria: String): List<Producto>
    /**
     * Funcion que guarda un nuevo producto
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return Devuelve el producto guardado
     */
    fun save(producto: Producto): Producto
    /**
     * Funcion que actualiza un producto en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return Devuelve el producto modificado o nulo si no existe
     */
    fun update(id: String, producto: Producto): Producto?
    /**
     * Funcion que elimina un producto en base a un id.
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return devuelve el producto borrado o nulo si no existe.
     */
    fun delete(id: String): Producto?
}