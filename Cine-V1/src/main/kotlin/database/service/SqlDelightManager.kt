package org.example.database.service

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import dev.javiercine.database.AppDatabase
import org.lighthousegames.logging.logging

private val log = logging()

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

    fun initialize() {
        if (databaseInitData) {
            removeAllData()
        }
    }

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