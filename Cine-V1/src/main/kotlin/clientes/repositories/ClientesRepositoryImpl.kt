package org.example.clientes.repositories

import org.example.clientes.models.Cliente
import org.example.database.service.SqlDeLightManager
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val log = logging()

/**
 * Clase que realiza todas las funciones del repository
 * @author Javier Ruiz
 * @since 1.0.0
 * @see ClientesRepository
 * @param db representa las queries que vamos a realizar.
 */
@Singleton
class ClientesRepositoryImpl(
    private val dbManager: SqlDeLightManager
) : ClientesRepository {
    private val db = dbManager.databaseQueries

    /**
     * Funcion que nos devuelve todos los clientes de la base de datos.
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve una lista con todos los clientes.
     */
    override fun findAll(): List<Cliente> {
        log.debug { "Buscando todos los clientes" }
        return db.selectAllClientes().executeAsList().map { it.toCliente() }
    }

    /**
     * Funcion que busca un cliente en base a un id dado
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve el cliente con id espcificdo o un nulo si no existe.
     */
    override fun findById(id: String): Cliente? {
        log.debug { "Buscando cliente por id: $id" }
        return db.selectClienteById(id).executeAsOneOrNull()?.toCliente()
    }

    /**
     * Funcion que guarda un nuevo cliente
     * @author Javier Ruiz
     * @since 1.0.0
     * @param timestamp nos da la fecha para guardar la creacion.
     * @return Devuelve el cliente creado.
     */

    override fun save(cliente: Cliente): Cliente {
        log.debug { "Guardando cliente: $cliente" }

        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertCliente(
                nombre = cliente.nombre,
                created_at = timeStamp,
                updated_at = timeStamp,
            )
        }

        return db.selectClienteLastInserted().executeAsOne().toCliente()
    }

    /**
     * Funcion que actualiza la informacion de un cliente segun un id dado
     * @author Javier Ruiz
     * @since 1.0.0
     * @return Devuelve el cliente actualizado
     */
    override fun update(id: String, cliente: Cliente): Cliente? {
        log.debug { "Actualizando cliente por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        result = result.copy(
            nombre = cliente.nombre,
            isDeleted = cliente.isDeleted,
            updatedAt = timeStamp
        )

        db.updateCliente(
            nombre = result.nombre,
            updated_at = timeStamp.toString(),
            is_deleted = if (result.isDeleted) 1 else 0,
            id = id,
        )
        return result
    }

    /**
     * Funcion que elimina un cliente segun un borrado logico
     * @author Javier Ruiz
     * @since 1.0.0
     * @return devuelve el cliente si lo ha borrado o un nulo si no existe ese cliente.
     */
    override fun delete(id: String): Cliente? {
        log.debug { "Borrando cliente por id: $id" }
        val result = this.findById(id) ?: return null
        // Esto es borrado lógico
        val timeStamp = LocalDateTime.now()
        db.updateCliente(
            nombre = result.nombre,
            email = result.email,
            is_deleted = 1,
            updated_at = timeStamp.toString(),
            id = result.id,
        )
        return result.copy(isDeleted = true, updatedAt = timeStamp)
    }


}