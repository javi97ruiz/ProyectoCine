package org.example.models.ticket.storage

import com.github.michaelbull.result.Result
import org.example.models.ticket.model.Venta
import org.example.ventas.errors.VentaError
import java.io.File

interface VentasStorage {
    fun export(venta: Venta, file: File): Result<Unit, VentaError>
}