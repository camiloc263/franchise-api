---

## ✅ Criterios de Aceptación Cumplidos

*   [x] **Spring Boot & WebFlux:** Stack 100% reactivo.
*   [x] **Persistencia:** Integración con MongoDB mediante contenedores.
*   [x] **Dockerizado:** Orquestación de servicios lista para despliegue.
*   [x] **Punto 7 (Lógica Senior):** Consulta optimizada de stocks máximos por sucursal.
*   [x] **Plus - CRUD Extendido:** Endpoints adicionales para actualizar nombres de franquicias, sucursales y productos.

---

## 👤 Autor
*   **Camilo Caicedo** - *Systems Engineer*

---

### Un último detalle para ti, Camilo:
He incluido la sección del **Punto 7** con un ejemplo de respuesta claro, ya que es lo que más mirará el evaluador para validar tu capacidad de análisis. Como ya tienes el repo en GitHub, asegúrate de que el archivo se llame exactamente `README.md` (con mayúsculas) para que se renderice automáticamente en la página principal del repositorio.

¿Quieres que te ayude a generar el archivo de la colección de Postman enAquí tienes el contenido completo y definitivo para tu **`README.md`**. Está diseñado con un estándar de **Arquitectura Limpia**, resaltando el enfoque reactivo y facilitando la vida del evaluador con ejemplos claros.

Solo tienes que copiar y pegar el siguiente bloque:

---

# 🚀 Franchise Management API (Reactive Stack)

Esta aplicación es una solución de alto rendimiento diseñada para la gestión jerárquica de **Franquicias, Sucursales y Productos**. Implementada bajo un paradigma de **programación reactiva** con **Spring WebFlux** y **MongoDB**, garantiza un manejo de datos no bloqueante y eficiente.

---

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Java 21
*   **Framework:** Spring Boot 3.5.14 (WebFlux)
*   **Base de Datos:** MongoDB (Reactive Driver)
*   **Documentación:** Swagger / OpenAPI 3 (springdoc-openapi)
*   **Contenedor:** Docker & Docker Compose
*   **Arquitectura:** Clean Architecture / Hexagonal

---

## 🏗️ Arquitectura y Diseño
El proyecto sigue los principios de **Clean Architecture**, lo que facilita el mantenimiento y la escalabilidad:
*   **Domain:** Entidades de negocio y reglas de dominio.
*   **Application:** Casos de uso y orquestación de lógica.
*   **Infrastructure:** Adaptadores externos (Controladores, Repositorios, Configuraciones).

---

## 📦 Guía de Despliegue Local

Para levantar el entorno completo (API + Base de Datos), asegúrate de tener instalado **Docker** y **Maven**.

### 1. Clonar y Compilar
```bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd franchise-api

# Compilar el JAR ejecutable
./mvnw clean package -DskipTests
2. Iniciar con Docker Compose
Este comando levantará la API en el puerto 8081 y MongoDB en el puerto 27018.

Bash
docker-compose up --build -d
3. Verificar Estado
Bash
docker-compose logs -f api
📖 Documentación de la API (Swagger)
Una vez iniciada la aplicación, accede a la interfaz interactiva aquí:
🔗 http://localhost:8081/webjars/swagger-ui/index.html

🕹️ Endpoints y Ejemplos de Uso
A continuación, se detallan los payloads para importar en herramientas como Postman o Insomnia.

1. Franquicias
POST /api/franchises - Crear una nueva franquicia.

JSON
{
  "name": "Franquicia Global"
}
2. Sucursales
POST /api/franchises/{franchiseId}/branches - Agregar sucursal.

JSON
{
  "name": "Sucursal Centro"
}
3. Productos
POST /api/branches/{branchId}/products - Agregar producto a sucursal.

JSON
{
  "name": "Producto A",
  "stock": 150
}
PATCH /api/products/{productId}/stock - Modificar stock.

JSON
{
  "newStock": 200
}
DELETE /api/products/{productId} - Eliminar producto.

4. Consultas Especializadas (Punto 7)
GET /api/franchises/{franchiseId}/max-stock
Este endpoint retorna el producto con mayor stock por cada sucursal de la franquicia.

Respuesta de ejemplo:

JSON
[
  {
    "branchName": "Sucursal Norte",
    "productName": "Camisetas",
    "stock": 500
  },
  {
    "branchName": "Sucursal Sur",
    "productName": "Zapatos",
    "stock": 320
  }
]
✅ Criterios de Aceptación Cumplidos
[x] Spring Boot & WebFlux: Stack 100% reactivo.

[x] Persistencia: Integración con MongoDB mediante contenedores.

[x] Dockerizado: Orquestación de servicios lista para despliegue.

[x] Punto 7 (Lógica Senior): Consulta optimizada de stocks máximos por sucursal.

[x] Plus - CRUD Extendido: Endpoints adicionales para actualizar nombres de franquicias, sucursales y productos.

👤 Autor
Camilo Caicedo - Systems Engineer