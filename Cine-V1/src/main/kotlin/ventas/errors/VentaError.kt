package org.example.ventas.errors

/**
 * Clase que representa los errores en la venta
 * @author Javier Ruiz
 * @since 1.0.0
 */
sealed class VentaError(val message: String) {
    /**
     * Clase que representa el error si no se encuentra la venta
     * @author Javier Ruiz
     * @since 1.0.0
     */
    class VentaNoEncontrada(message: String) : VentaError(message)
    /**
     * Clase que representa el error si hay datos invalidos en la venta.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    class VentaNoValida(message: String) : VentaError(message)
    /**
     * Clase que representa si no existe una venta.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    class VentaNoAlmacenada(message: String) : VentaError(message)
    /**
     * Clase que representa el error al guardar la venta.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    class VentaStorageError(message: String) : VentaError(message)
}