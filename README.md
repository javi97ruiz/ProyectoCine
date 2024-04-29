# Proyecto de GESTIÓN DE CINE.

## Descripción

Tenemos nuestro cine que tiene una estructura de butacas agrupadas en **5 filas** y **7 columnas**. Las filas están numeradas de la **A** a la **E** y las columnas de **1** la **7**. Por lo tanto, una butaca queda identificada como **A4** (fila 1, columna 4).

Además, las butacas pueden ser **normales**, cuyo precio es de **5€** la entrada o **VIP**, cuyo precio es **8€**. Y una butaca puede estar **activa**, en **mantenimiento** o **fuera de servicio**. Además, obviamente puede estar **libre**, en **reserva** y **ocupada**.

Por otro lado tenemos complementos, que pueden ser de las categorías **bebida**, **comida** y **otros**. Como bebida tenemos **agua (2€)** y **refrescos (3€)** y como comida tenemos **palomitas (3€)**, **frutos secos (2€)** y **patatas (2,5€)**.

## Objetivo

Queremos realizar un programa para gestionar nuestro cine usando bases de datos con **SQLite** y ficheros.

## Funcionalidades

Nuestra aplicación hará uso de un menú para:

1. **Comprar entrada**: si hay butacas libres reservará una butaca con el número de socio. Se podrá añadir un máximo de 3 complementos. El número de socio es LLLNNN (L es letra y N es número). Se obtiene un fichero llamado entrada_Butaca_NSocio_Fecha.html (entrada, más el identificador de la butaca, más el identificador del socio, más la fecha de la compra).
2. **Devolver entrada**: devuelve una entrada liberando los recursos asociados.
3. **Estado del cine**: Muestra el estado del cine
4. **Obtener recaudación**: se obtiene la recaudación dada una fecha válida dada en formato AAAA/MM/DD.
5. **Importar complementos**: Importa complementos en base a un fichero CSV dado.
6. **Exportar estado del cine**: exporta el estado del cine dada una fecha válida AAAA/MM/DD en un fichero json dado.
7. **Configurar butacas**: Configura las butacas en base a un fichero CSV dado.
8. **Actualizar butaca**: Cambia la información de una butaca dado su identificador: LN.

## Tablas a tener en cuenta

- Butacas
- Complementos
- Ventas (y asociadas)
- Otras que tú puedas considerar.

No olvides la integridad de claves primaria y referencial donde sea necesario. Debes razonar cuando es necesario delegar la creación de la clave primaria en la bases de datos y cuándo la propia lógica de la aplicación sea la encargada de ello.

## Otras consideraciones

- Al menos un servicio debe usar excepciones adaptadas al dominio.
- Al menos un servicio debe usar Mónadas, ROP y Errores orientados al dominio.
- Al menos un repositorio debe usar un manejador de bases de datos y mapeo manual.
- Al menos un repositorio debe usar SQLDelight.
- Todos los repositorios y servicios deben estar totalmente testeados con los casos correctos e incorrectos.
- Realiza una arquitectura orientada al dominio.
- Usa mecanismos automatizados de inyección de dependencias.