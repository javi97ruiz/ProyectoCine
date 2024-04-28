package org.example.models.ticket.storage

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.models.ticket.model.Venta
import org.example.ventas.dto.VentaDto
import org.example.ventas.errors.VentaError
//import org.example.ventas.mappers.toVentaDto
import org.lighthousegames.logging.logging
import java.io.File
/*
private val log=logging()
class VentasStorageJsonImpl : VentasStorage {
    override fun export(venta: Venta, file: File): Result<Unit, VentaError> {
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(file.writeText(json.encodeToString<VentaDto>(venta.toVentaDto())))
        } catch (e: Exception) {
            log.error { "Error al salvar ventas a fichero: ${file.absolutePath}. ${e.message}" }
            Err(VentaError.VentaStorageError("Error al salvar ventas a fichero: ${file.absolutePath}. ${e.message}"))
        }
    }
}*/