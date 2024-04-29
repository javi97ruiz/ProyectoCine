package org.example.database.service

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import dev.javiercine.database.AppDatabase
import org.lighthousegames.logging.logging

private val log = logging()

/**
 * Clase que sirve para inicializar la base de datos
 * @author Javier Ruiz
 * @since 1.0.0
 * @param databaseUrl representa la url que tendra la base de datos
 * @param databaseInitData es por si se quiere iniciar la base de datos con datos de prueba
 * @param databaseInMemory sirve para cambiar la base de datos de un archivo a memoria.
 */
class SqlDeLightManager(

    private val _databaseUrl: String = "jdbc:sqlite:cine.db",
    private val _databaseInitData: String = "true",
    private val _databaseInMemory: String = "true"
) {
    private val databaseUrl: String = _databaseUrl
    private val databaseInitData: Boolean = _databaseInitData.toBoolean()
    private val databaseInMemory: Boolean = _databaseInMemory.toBoolean()
    val databaseQueries: DatabaseQueries = initQueries()

    init {
        log.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        // Inicializamos datos de ejemplo, si se ha configurado
        initialize()
    }

    /**
     * Funcion para inicializar la base de datos y crearla
     * @author Javier Ruiz
     * @since 1.0.0
     * @return devuelve las consultas para la base de datos
     */
    private fun initQueries(): DatabaseQueries {

        return if (databaseInMemory) {
            log.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            log.debug { "SqlDeLightClient - File: ${databaseUrl}" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            log.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries

    }

    /**
     * Funcion que borra todas las tablas para que esten en blanco.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    fun initialize() {
        if (databaseInitData) {
            removeAllData()
        }
    }

    /**
     * Funcion que borra todas las tablas
     * @author Javier Ruiz
     * @since 1.0.0
     */
    // limpiamos las tablas
    private fun removeAllData() {
        log.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllVentas()
            databaseQueries.removeAllClientes()
            databaseQueries.removeAllProductos()
        }
    }
}