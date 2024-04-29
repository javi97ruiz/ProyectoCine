package org.example.ventas.repositories

import org.example.clientes.repositories.ClientesRepository
import org.example.database.service.SqlDeLightManager
import org.example.models.ticket.model.Venta
import org.example.productos.repositories.ProductosRepository
import org.example.ventas.mappers.toLineaVenta
import org.example.ventas.mappers.toVenta
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.util.*

    private val log = logging()
@Singleton
class VentasRepositoryImpl(
    private val dbManager: SqlDeLightManager,
    private val productosRepository: ProductosRepository,
    private val clientesRepository: ClientesRepository
    ) : VentasRepository {

    private val db = dbManager.databaseQueries

    override fun findById(id: UUID): Venta? {
    log.debug { "Obteniendo venta por id: $id" }
        if (db.existsVenta(id.toString()).executeAsOne()) {
            // Obtenemos la venta en bruto
            val ventaEntity = db.selectVentaById(id.toString()).executeAsOne()
            // Obtenemos el cliente
            val cliente = clientesRepository.findById(ventaEntity.cliente_id.toString())!!
             // obtenemos las lineas de venta con los productos
            val lineasVenta = db.selectAllLineasVentaByVentaId(ventaEntity.id).executeAsList()
                .map { it.toLineaVenta(productosRepository.findById(it.producto_id.toString())!!) }
                // Mapeamos la venta
                return ventaEntity.toVenta(cliente, lineasVenta)
            }
            return null
        }

        override fun save(venta: Venta): Venta {
            log.debug { "Guardando venta: $venta" }
            db.transaction {
                db.insertVenta(
                    id = venta.id.toString(),
                    cliente_id = venta.cliente.id,
                    total = venta.total,
                    created_at = venta.createdAt.toString(),
                    updated_at = venta.updatedAt.toString()
                )
            }
            venta.lineas.forEach {
                db.transaction {
                    db.insertLineaVenta(
                        id = it.id.toString(),
                        venta_id = venta.id.toString(),
                        producto_id = it.producto.id,
                        cantidad = it.cantidad.toLong(),
                        precio = it.precio,
                        created_at = it.createdAt.toString()
                    )
                }
            }
            return venta
        }

        override fun update(id: UUID, venta: Venta): Venta? {
            findById(id)
            log.debug { "Actualizando venta: $venta" }
            // Guardamos la venta
            db.transaction {
                db.updateVenta(
                    id = venta.id.toString(),
                    cliente_id = venta.cliente.id,
                    total = venta.total,
                    is_deleted = 0,
                    updated_at = venta.updatedAt.toString()

                )
            }
            venta.lineas.forEach {
                db.transaction {
                    db.updateLineaVenta(
                        id = it.id.toString(),
                        venta_id = venta.id.toString(),
                        producto_id = it.producto.id,
                        cantidad = it.cantidad.toLong(),
                        precio = it.precio,
                        is_deleted = 0

                    )
                }
            }
            return venta
        }

            override fun delete(id: UUID, venta: Venta): Venta? {
                findById(id)
                log.debug { "Borrando venta con id: $id" }
                db.transaction {
                    db.deleteVenta(
                        id = id.toString()
                    )
                }
                venta.lineas.forEach {
                    db.transaction {
                        db.deleteLineaVenta(
                            id = it.id.toString(),
                            venta_id = venta.id.toString(),
                            is_deleted = 0

                        )
                    }
                }
                return venta
            }
    }