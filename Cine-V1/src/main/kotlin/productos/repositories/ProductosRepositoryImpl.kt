package org.example.productos.repositories

import org.example.database.service.SqlDeLightManager
import org.example.productos.models.Producto
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val log = logging()

@Singleton
class ProductosRepositoryImpl(
    private val dbManager: SqlDeLightManager
) : ProductosRepository {

    private val db = dbManager.databaseQueries

    /**
     * Obtiene todos los productos de la base de datos
     */
    override fun findAll(): List<Producto> {
        log.debug { "Obteniendo todos los productos" }
        return db.selectAllProductos()
            .executeAsList()
            .map { it.toProducto() }
    }

    /**
     * Obtiene un producto por id
     */
    override fun findById(id: String): Producto? {
        log.debug { "Obteniendo producto por id: $id" }
        return db.selectProductoById(id)
            .executeAsOneOrNull()
            ?.toProducto()
    }

    /**
     * Obtiene productos por categoría
     */
    override fun findByCategoria(categoria: String): List<Producto> {
        log.debug { "Obteniendo productos por categoría: $categoria" }
        return db.selectAllProductosByCategoria(categoria)
            .executeAsList()
            .map { it.toProducto() }
    }

    /**
     * Guarda un producto en la base de datos
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
     * Actualiza un producto en la base de datos
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
     * Borra un producto de la base de datos
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