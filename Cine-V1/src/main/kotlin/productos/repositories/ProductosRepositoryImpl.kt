package org.example.productos.repositories

import org.example.database.service.SqlDeLightManager
import org.example.productos.models.Producto
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val log = logging()

/**
 * Clase que realiza las operacion del repositorio relacionadas con los productos
 * @author Javier Ruiz
 * @since 1.0.0
 * @see ProductosRepository
 * @see findAll
 * @see findById
 * @see findByCategoria
 * @see save
 * @see update
 * @see delete
 * @param db representa las queries de la base de datos
 */
@Singleton
class ProductosRepositoryImpl(
    private val dbManager: SqlDeLightManager
) : ProductosRepository {

    private val db = dbManager.databaseQueries

    /**
     * Funcion que busca todos los productos
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve una lista con todos los productos
     */
    override fun findAll(): List<Producto> {
        log.debug { "Obteniendo todos los productos" }
        return db.selectAllProductos()
            .executeAsList()
            .map { it.toProducto() }
    }

    /**
     * Funcion que busca un producto en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve el producto si lo ha encontrado o nulo si no existe
     */
    override fun findById(id: String): Producto? {
        log.debug { "Obteniendo producto por id: $id" }
        return db.selectProductoById(id)
            .executeAsOneOrNull()
            ?.toProducto()
    }

    /**
     * Funcion que busca en base a una categoria
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve la lista de productos de una categoria
     */
    override fun findByCategoria(categoria: String): List<Producto> {
        log.debug { "Obteniendo productos por categoría: $categoria" }
        return db.selectAllProductosByCategoria(categoria)
            .executeAsList()
            .map { it.toProducto() }
    }

    /**
     * Funcion que guarda un producto
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return Devuelve el producto guardado
     */
    override fun save(producto: Producto): Producto {
        log.debug { "Guardando producto: $producto" }

        val timeStamp = LocalDateTime.now().toString()

        // Las transacciones son necesarias para que se hagan todas las operaciones juntas
        // Si no se hace así, se pueden hacer operaciones intermedias y no se garantiza la atomicidad
        // esto es así porque insertamos y luego seleccionamos el último insertado para el id
        db.transaction {
            db.insertProducto(
                nombre = producto.nombre,
                precio = producto.precio,
                stock = producto.stock.toLong(),
                categoria = producto.categoria.name,
                created_at = timeStamp,
                updated_at = timeStamp
            )
        }
        return db.selectProductoLastInserted()
            .executeAsOne()
            .toProducto()
    }

    /**
     * Funcion que actualiza un producto en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve el producto modificado o nulo si no existe
     */
    override fun update(id: String, producto: Producto): Producto? {
        log.debug { "Actualizando producto por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()

        result = result.copy(
            nombre = producto.nombre,
            precio = producto.precio,
            stock = producto.stock,
            categoria = producto.categoria,
            isDeleted = producto.isDeleted,
            updatedAt = timeStamp
        )

        db.updateProducto(
            nombre = result.nombre,
            precio = result.precio,
            stock = result.stock.toLong(),
            categoria = result.categoria.name,
            is_deleted = if (result.isDeleted) 1 else 0,
            updated_at = timeStamp.toString(),
            id = producto.id,
        )
        return result
    }

    /**
     * Funcion que elimina un producto en base a un id
     * @author Javier Ruiz
     * @since 1.0.0
     * @see ProductosRepositoryImpl
     * @see ProductosRepository
     * @return Devuelve el producto eliminado o nulo si no existe
     */
    override fun delete(id: String): Producto? {
        log.debug { "Borrando producto por id: $id" }
        val result = this.findById(id) ?: return null
        // Esto es borrado fisico, pero lo ideal sería un borrado lógico si se quiere mantener la integridad
        // db.deleteProducto(id)
        // Borrado lógico
        val timeStamp = LocalDateTime.now()
        db.updateProducto(
            nombre = result.nombre,
            precio = result.precio,
            stock = result.stock.toLong(),
            categoria = result.categoria.name,
            is_deleted = 1,
            updated_at = timeStamp.toString(),
            id = result.id,
        )
        return result.copy(isDeleted = true, updatedAt = timeStamp)
    }
}