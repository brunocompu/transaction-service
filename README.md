# Transaction Service

Servicio web RESTful desarrollado con Java y Spring Boot para administrar transacciones, clasificarlas por tipo y calcular sumas de transacciones relacionadas jerárquicamente.

## Tecnologías

- Java 17
- Spring Boot
- Maven
- JUnit 5
- Docker

## Requisitos

Para ejecutar la aplicación localmente:
- Java 17 o superior
- Maven

Para ejecutarla con Docker:
- Docker Desktop o Docker Engine

## Ejecución local

Compilar el proyecto y ejecutar los tests:

```bash
mvn clean package
```

Ejecutar el JAR generado:

```bash
java -jar target/transaction-service-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

## Ejecución con Docker

Primero, generar el JAR:

```bash
mvn clean package
```

Construir la imagen Docker:

```bash
docker build -t transaction-service .
```

Ejecutar el contenedor:

```bash
docker run -p 8080:8080 transaction-service
```

La API estará disponible en `http://localhost:8080`.

## Endpoints

### Guardar o actualizar una transacción

`PUT /transactions/{transaction_id}`

Cuerpo de la solicitud:

```json
{
  "amount": 5000,
  "type": "cars",
  "parent_id": null
}
```

El campo `parent_id` es opcional.

Respuesta:

```json
{
  "status": "ok"
}
```

### Buscar transacciones por tipo

`GET /transactions/types/{type}`

Ejemplo:

```text
GET /transactions/types/cars
```

Respuesta:

```json
[10]
```

Devuelve una lista json de todos los ids de las transacciones para el tipo especificado.

### Calcular la suma de una transacción

`GET /transactions/sum/{transaction_id}`

Ejemplo:

```text
GET /transactions/sum/10
```

Respuesta:

```json
{
  "sum": 20000.0
}
```

Calcula la suma de la transacción indicada y de todas las transacciones que están transitivamente conectadas por su parent_id al $transaction_id, incluyendo los niveles anidados.

## Ejemplo de uso

Crear las siguientes transacciones:

| ID | Monto | Tipo | ID del padre |
|---|---:|---|---|
| 10 | 5000 | cars | null |
| 11 | 10000 | shopping | 10 |
| 12 | 5000 | shopping | 11 |

Solicitudes:

```text
PUT /transactions/10
PUT /transactions/11
PUT /transactions/12

GET /transactions/types/cars
GET /transactions/sum/10
GET /transactions/sum/11
```

Respuestas esperadas:

```json
[10]
```

```json
{"sum": 20000.0}
```

```json
{"sum": 15000.0}
```

## Ejecución de tests

Ejecutar todos los tests con:

```bash
mvn test
```

El proyecto incluye tests unitarios para la capa de servicios y tests de integración para la API REST.

## Almacenamiento de datos

Las transacciones se almacenan en memoria utilizando una colección de Java. No se requiere una base de datos externa.

**Importante:** Los datos no son persistentes y se pierden cuando se reinicia la aplicación.