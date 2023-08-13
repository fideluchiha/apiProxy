**Nombre de la Aplicación: apiProxy Spring Boot**

Este es el archivo README para la aplicación Java Spring Boot llamada apiProxy. Esta aplicación utiliza Gradle como herramienta de construcción y gestión de dependencias.

## Descripción

apiProxy es una aplicación de ejemplo desarrollada en Java utilizando el framework Spring Boot. La aplicación muestra cómo configurar un proyecto de Spring Boot utilizando Gradle como gestor de dependencias y herramienta de construcción. La aplicación incluye una funcionalidad básica para gestionar elementos, como crear, leer, actualizar y eliminar elementos.

## Requisitos Previos

- Java JDK 8 o superior instalado
- Gradle instalado (la versión incluida en el proyecto es suficiente)
- Conexión a internet para descargar dependencias

## Instrucciones de Uso

1. Clona este repositorio en tu máquina local o descárgalo como archivo ZIP.

2. Abre una terminal y navega hasta el directorio raíz de la aplicación:

   ```sh
   cd ruta/del/proyecto/apiProxy
   ```

3. Compila y ejecuta la aplicación usando Gradle:

   ```sh
   ./gradlew bootRun
   ```

   Esto compilará la aplicación y la iniciará en un servidor integrado de Spring Boot. Para detener la aplicación, simplemente presiona `Ctrl + C` en la terminal.

4. Abre un navegador web y accede a la siguiente URL para interactuar con la aplicación:

   ```
   [http://127.0.0.1:8080/categories/MLA1367]
   ```

   Aquí encontrarás la interfaz de usuario de la aplicación, donde podrás realizar las operaciones básicas de gestión de elementos.

## Estructura del Proyecto

La estructura del proyecto está organizada de la siguiente manera:

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   │   ├── proxy
│   │   │   │   │   │   ├── api
│   │   │   │   │   │   │    ├── config
│   │   │   │   │   │   │    │    ├── RestTemplateConfig.java
│   │   │   │   │   │   │    ├── controllers
│   │   │   │   │   │   │    │    ├── ApiController.java
│   │   │   │   │   │   │    │    ├── ConsumosController.java
│   │   │   │   │   │   │    │    ├── LimitRequestController.java
│   │   │   │   │   │   │    ├── DTO
│   │   │   │   │   │   │    │    ├── EstadisticaDTO.java
│   │   │   │   │   │   │    │    ├── LimitRequestDTO.java
│   │   │   │   │   │   │    │    ├── LimitResponseDTO.java
│   │   │   │   │   │   │    ├── entity
│   │   │   │   │   │   │    │    ├── TblConsumos.java
│   │   │   │   │   │   │    │    ├── TblLimitRequest.java
│   │   │   │   │   │   │    ├── repository
│   │   │   │   │   │   │    │    ├── ConsumosRepository.java
│   │   │   │   │   │   │    │    ├── LimitRequestRepository.java
│   │   │   │   │   │   │    ├── repository
│   │   │   │   │   │   │   │    ├── implementation
│   │   │   │   │   │   │   │    │    ├── ConsumosImplements.java
│   │   │   │   │   │   │   │    │    ├── LimitRequestImplements.java
│   │   │   │   │   │   │   │    ├── interfaces
│   │   │   │   │   │   │   │    │    ├── IConsumosServices.java
│   │   │   │   │   │   │   │    │    ├── ILimitRequestServices.java
│   │   ├── resources
│   │   │   ├── application.properties
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

## Contribuciones

Las contribuciones a esta aplicación son bienvenidas. Si encuentras algún error, tienes ideas para mejoras o deseas agregar nuevas características, no dudes en crear una solicitud de extracción.


¡Disfruta experimentando con apiProxy Spring Boot! Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos.

## Docker 

Cuenta con un archivo docker-compose.yml

solo seria ejecutar el comando  docker-compose up --build y compilaria la imagen ademas creando el contenedor 

## Postman 

Cuenta con una coleccion de ejemplo de consumos en la carpeta Postman en la raiz del proyecto


