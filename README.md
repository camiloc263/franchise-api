

Esta aplicación es una solución de alto rendimiento diseñada para la gestión jerárquica de Franquicias, Sucursales y Productos. Implementada bajo un paradigma de programación reactiva con Spring WebFlux y MongoDB, esta API garantiza un manejo de datos no bloqueante y eficiente.

Stack Tecnológico
Lenguaje: Java 21

Framework: Spring Boot 3.5.14 (WebFlux)

Base de Datos: MongoDB (Reactive Driver)

Documentación: Swagger / OpenAPI 3 (springdoc-openapi)

Contenedor: Docker & Docker Compose

Arquitectura: Clean Architecture / Hexagonal

Arquitectura del Proyecto
El proyecto sigue los principios de Arquitectura Limpia, lo que facilita el mantenimiento y la escalabilidad:

Domain: Contiene las entidades de negocio y las reglas principales.

Application: Maneja los casos de uso y la lógica de orquestación.

Infrastructure: Implementa los adaptadores externos (Controladores REST, Repositorios MongoDB y configuraciones).

Guía de Despliegue Local
Para levantar el entorno completo (API + Base de Datos), asegúrate de tener instalado Docker y Maven.

1. Clonar y Compilar
Bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd franchise-api

# Compilar el JAR (Saltando tests para agilizar)
./mvnw clean package -DskipTests
2. Iniciar con Docker Compose
Este comando levantará la API en el puerto 8081 y MongoDB en el 27018.

Bash
docker-compose up --build -d
3. Verificar Estado
Puedes monitorear que la aplicación haya iniciado correctamente con:

Bash
docker-compose logs -f api
Documentación de la API (Swagger)
Una vez que la aplicación esté corriendo, la documentación interactiva estará disponible en la siguiente ruta:

🔗 http://localhost:8081/webjars/swagger-ui/index.html

Nota: Se ha configurado SecurityWebFilterChain para permitir el acceso público a Swagger sin necesidad de autenticación.

Funcionalidades Implementadas
Gestión de Franquicias y Sucursales
[x] POST /api/franchises: Crear una nueva franquicia.

[x] POST /api/franchises/{id}/branches: Agregar una sucursal a una franquicia.

[x] PATCH /api/franchises/{id}: Actualizar nombre de la franquicia (Plus).

[x] PATCH /api/branches/{id}: Actualizar nombre de la sucursal (Plus).

Gestión de Productos e Inventario
[x] POST /api/branches/{id}/products: Agregar un producto a una sucursal.

[x] DELETE /api/products/{id}: Eliminar un producto.

[x] PATCH /api/products/{id}/stock: Modificar el stock de un producto.

[x] PATCH /api/products/{id}/name: Actualizar nombre del producto (Plus).

Lógica Senior (Punto 7)
[x] GET /api/franchises/{id}/max-stock: Retorna el producto con mayor stock por cada sucursal para una franquicia específica.

Notas de Configuración
Bean Overriding: Se habilitó SPRING_MAIN_ALLOW_BEAN_DEFINITION_OVERRIDING=true en el entorno para permitir la personalización de la seguridad reactiva.

Resiliencia: El servicio de la API espera a que el contenedor de MongoDB pase el healthcheck antes de iniciar.