# Proyecto de Aplicación en Docker

Este proyecto utiliza Docker para compilar y ejecutar una aplicación Java construida con Gradle. A continuación, se
detallan los pasos necesarios para construir, etiquetar y enviar la imagen Docker a un registro.

## Requisitos previos

- Docker instalado: [Instrucciones para instalar Docker](https://docs.docker.com/get-docker/)
- Cuenta en DockerHub (para empujar la imagen)

## Pasos para construir y ejecutar la imagen

### 1. Compilación de la imagen Docker

El primer paso es construir la imagen Docker a partir de un `Dockerfile`. Ejecuta el siguiente comando en el directorio
donde se encuentra el `Dockerfile`:

```
docker build -t true .
```

Utiliza el Dockerfile para construir una imagen llamada true.

### 2. Ejecutar la imagen Docker

Una vez que la imagen ha sido creada, puedes ejecutar un contenedor basado en esa imagen con el siguiente comando

```
docker run true
```

Ejecuta un contenedor basado en la imagen true.

### 3. Iniciar sesión en DockerHub

Antes de poder empujar la imagen a DockerHub, necesitas iniciar sesión en tu cuenta de DockerHub:

bash

```
docker login
```

Sigue las instrucciones para ingresar tu usuario y contraseña.

### 4. Etiquetar la imagen

Debes etiquetar la imagen para subirla a tu repositorio en DockerHub. Utiliza el siguiente comando:

```
docker tag true tomasvj/true:latest
```

Este comando:

Etiqueta la imagen local true como tomasvj/true:latest, que se subirá a tu repositorio en DockerHub.

### 5. Empujar la imagen a DockerHub

Finalmente, empuja la imagen etiquetada a DockerHub utilizando el siguiente comando:

bash

```
docker push tomasvj/true:latest
```

Este comando:
Sube la imagen etiquetada a tu repositorio en DockerHub (tomasvj/true:latest).

Dockerfile

El Dockerfile utilizado en este proyecto está dividido en dos etapas: compilación y ejecución.

dockerfile

```

# Etapa de compilación, usa una imagen específica de Gradle con JDK 21

FROM gradle:jdk21 AS build

# Directorio de trabajo

WORKDIR /app

# Copia los archivos de configuración de Gradle y el código fuente del proyecto

COPY build.gradle.kts .
COPY gradlew .
COPY gradle gradle
COPY src src

# Compila el proyecto con Gradle

RUN ./gradlew build

# Etapa de ejecución, usa una imagen de OpenJDK 21

FROM openjdk:21-jdk AS run

# Directorio de trabajo

WORKDIR /app

# Copia el archivo JAR desde la etapa de compilación

COPY --from=build /app/build/libs/*.jar /app/my-app.jar

# Ejecuta el JAR de la aplicación

ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]
```

Este Dockerfile:
Compila el código fuente utilizando Gradle en la primera etapa.

En la segunda etapa, utiliza una imagen de OpenJDK para ejecutar la aplicación empaquetada en un archivo JAR.
