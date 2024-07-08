# Sistema de Validación de Inventario Patito

[![Build Status](https://travis-ci.org/patito/patito-inventory-system.svg?branch=master)](https://travis-ci.org/patito/patito-inventory-system)
[![Coverage Status](https://coveralls.io/repos/github/diegomtzcdo/back-patito/badge.svg?branch=master)](https://coveralls.io/github/diegomtzcdo/back-patito?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![Java](https://img.shields.io/badge/Java-11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.7.RELEASE-brightgreen)

Un sistema integral para validar el inventario de las camionetas antes de realizar pedidos para las tiendas Patito.

## Requisitos

Para construir y ejecutar la aplicación, necesitas:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Gradle](https://gradle.org/install/)

## Descripción del Proyecto

El Sistema de Validación de Inventario Patito garantiza que se verifique el inventario antes de realizar un pedido. El sistema incluye las siguientes características:

- Una interfaz de usuario para registrar pedidos de camionetas.
- Una pantalla de resumen que muestra el estado del pedido al finalizar.
- Almacenamiento de datos para auditoría (fecha del evento, IP del usuario), identificador del producto (HAWA), identificador de la tienda, detalles del cliente, nombre del vendedor y descuento del producto (si lo hay).
- Verificación en tiempo real del inventario de productos HAWA, mostrando el precio de lista, descuento y disponibilidad en stock.
- Una pantalla de confirmación que muestra los productos, el nombre del vendedor y los detalles del cliente.
- Una pantalla de listado de pedidos que muestra todos los pedidos en la base de datos con vistas detalladas.
- Estados del pedido: pendiente, entregado y cancelado. Solo se pueden modificar los pedidos pendientes. Los pedidos no pueden ser cancelados si han pasado más de 10 minutos desde su creación.
- Funcionalidad opcional: Incremento/decremento automático de stock basado en el estado del pedido.

## Ejecución de la Aplicación Localmente

Para ejecutar la aplicación Spring Boot en tu máquina local, ejecuta el método `main` en la clase `com.patito.cdod.PruebaTecnicaApplication` desde tu IDE.

Alternativamente, puedes usar el plugin de Gradle para Spring Boot:

```shell
./gradlew bootRun
```
## Seguridad

La aplicación utiliza Spring Security para autenticación y autorización.

## Endpoints de la API

- **/orders**: Endpoint para crear y listar pedidos.
- **/orders/{id}**: Endpoint para recuperar, actualizar y eliminar un pedido específico.
- **/inventory**: Endpoint para verificar el inventario de productos.

## Contacto

Para cualquier consulta, por favor contacta al desarrollor en cdodiegomtz@gmail.com.