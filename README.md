# Franchise Management API (Reactive Stack)
Esta solución proporciona un sistema de gestión jerárquica para franquicias, sucursales y productos, construida sobre un paradigma no bloqueante para garantizar alta concurrencia y eficiencia.

## Especificaciones Técnicas
Lenguaje: Java 21 (LTS).

Framework: Spring Boot 3.5.14 (WebFlux).

Persistencia: MongoDB (Driver Reactivo).

Documentación: Swagger / OpenAPI 3.

Arquitectura: Clean Architecture / Hexagonal.

## Instrucciones de Ejecución
1. Preparar el Entorno (Docker)
La aplicación requiere MongoDB para persistir los datos. Utiliza el archivo docker-compose.yml para levantar la base de datos en el puerto 27018:

Bash
docker-compose up -d
2. Compilar y Ejecutar
Una vez que el contenedor de la base de datos esté listo, inicia la API en el puerto 8081:

Bash
./mvnw clean spring-boot:run
## Guía de Evaluación
Pruebas Automatizadas
Se han implementado pruebas unitarias y de integración que validan flujos reactivos mediante StepVerifier. Para ejecutarlas:

Bash
./mvnw test
Exploración con Swagger
Para probar los endpoints de manera interactiva, accede a la interfaz de Swagger UI:
🔗 http://localhost:8081/webjars/swagger-ui/index.html

## Pruebas con Postman y JSON
A continuación, se detallan los ejemplos de carga para las operaciones principales.

1. Gestión de Estructura
Crear Franquicia (POST /api/franchises):

JSON
{
  "name": "Franquicia de Alimentos S.A."
}
Agregar Sucursal (POST /api/franchises/{id}/branches):

JSON
{
  "name": "Sucursal Centro"
}
2. Operaciones de Inventario
Agregar Producto a Sucursal (POST /api/branches/{id}/products):

JSON
{
  "name": "Café Premium 500g",
  "stock": 100
}
Actualizar Stock (PATCH /api/products/{id}/stock):

JSON
{
  "stock": 150
}
## Endpoint de Lógica Avanzada (Punto 7)
GET /api/franchises/{id}/max-stock

Este endpoint resuelve una agregación compleja de forma reactiva: identifica el producto con mayor stock por cada sucursal perteneciente a una franquicia específica.

Ejemplo de Respuesta:

JSON
[
  {
    "branchName": "Sucursal Centro",
    "productName": "Café Premium 500g",
    "stock": 150
  },
  {
    "branchName": "Sucursal Norte",
    "productName": "Té Verde Orgánico",
    "stock": 92
  }
]
## Detalles de Implementación Senior
Manejo de Excepciones: Se implementó un @ControllerAdvice global para gestionar errores de negocio y transformarlos en respuestas HTTP semánticas.

## Seguridad en Tests: Se ajustó la configuración de seguridad reactiva para permitir la validación de endpoints POST y PATCH durante los tests de integración.

## Mapeo Desacoplado: Uso de MapStruct para garantizar que las entidades de persistencia de MongoDB nunca se filtren a la capa de presentación.

# Guía de Ejecución de Pruebas
La suite de pruebas está diseñada para validar tanto la lógica de negocio en la capa de dominio como la integración de los servicios en la capa de infraestructura.

### Comandos Principales de Maven
El proyecto utiliza el Maven Wrapper (mvnw), lo que garantiza que las pruebas se ejecuten con la versión correcta de Maven sin necesidad de instalaciones globales.

1. Ejecución Completa
Para limpiar artefactos previos y ejecutar toda la suite de pruebas del proyecto:

Bash
./mvnw clean test
Este comando activará tanto los tests unitarios como los de integración localizados en src/test/java.

2. Ejecución Selectiva
Si estás trabajando en una funcionalidad específica y deseas ahorrar tiempo, puedes filtrar la ejecución:

### Por Clase:
./mvnw test -Dtest=NombreDeLaClaseTest

### Por Método:
./mvnw test -Dtest=NombreDeLaClaseTest#nombreDelMetodo

### Análisis de Tecnologías Utilizadas
### Validación Reactiva con StepVerifier
Debido a que el proyecto utiliza Spring WebFlux, las pruebas de los casos de uso no usan aserciones simples. Se emplea ### StepVerifier para inspeccionar el flujo de señales:

Expectativas de emisión: Valida que se emitan los objetos correctos (onNext).

Finalización del flujo: Asegura que el flujo termine correctamente (onComplete).

Manejo de errores: Verifica que las excepciones de negocio sean lanzadas cuando corresponde (onError).

Pruebas de API con WebTestClient
Para los controladores REST, se utiliza WebTestClient, el cual permite simular peticiones HTTP de forma no bloqueante.

Se validan los Status Codes (201 Created, 200 OK, 404 Not Found).

Se verifica la estructura del JSON de respuesta.

## Reportes y Resultados
Al finalizar, Maven mostrará un resumen en la terminal. Si necesitas un detalle técnico más profundo (por ejemplo, para depurar un error de integración), puedes consultar los archivos generados automáticamente en:
target/surefire-reports/

Tip Profesional: Antes de realizar un git push o un despliegue, siempre es recomendable ejecutar mvn clean test para asegurar que las nuevas modificaciones no hayan roto funcionalidades existentes (regresión).


#Despliegue en la Nube (AWS)
La solución se encuentra desplegada y operativa en una instancia EC2 de AWS, orquestada mediante Docker Compose para garantizar la paridad entre los entornos de desarrollo y producción.

Host: 3.14.87.73 (Región: Ohio - us-east-2).

Arquitectura de Red: El microservicio y la base de datos coexisten en una red virtual de Docker, exponiendo únicamente los puertos necesarios hacia el exterior para mantener la seguridad del entorno.

Infraestructura como Código: Se incluyen archivos de configuración de Terraform en el repositorio (src/terraform) para la provisión automatizada de los recursos de red y cómputo.

# Acceso a la API en Nube
Puedes realizar pruebas directamente contra la instancia de AWS utilizando los siguientes puntos de enlace:

Swagger UI (Nube): [http://3.14.87.73:8081/webjars/swagger-ui/index.html](http://3.14.87.73:8081/webjars/swagger-ui/index.html)

Base URL API: [http://3.14.87.73:8081/api/v1](http://3.14.87.73:8081/api/v1)

## Actualización del "Leeme" actual
Para que el resto de tu documento sea coherente con la nueva sección, te sugiero ajustar la parte de Instrucciones de Ejecución para diferenciar entre el modo local y el modo contenedor completo:

## 1. Ejecución con Docker (Recomendado)
Si deseas levantar la infraestructura completa (API + MongoDB) sin necesidad de tener Java instalado localmente:

### Bash
docker-compose up -d --build
Esto compilará el código dentro de un contenedor de construcción y desplegará el microservicio en el puerto 8081.



# Despliegue en la Nube (AWS)
La solución se encuentra desplegada y operativa en una instancia EC2 de AWS, orquestada mediante Docker Compose para garantizar la paridad entre los entornos de desarrollo y producción.

Detalles de Infraestructura
Host: 3.14.87.73 (Región: Ohio - us-east-2).

Entorno: Contenedores Docker sobre Linux (Amazon Linux 2023).

Red: El microservicio y la base de datos coexisten en una red interna de Docker, exponiendo únicamente el puerto 8081 para el tráfico de la API y el puerto 27018 para conexiones externas de base de datos (según configuración de seguridad).

IaC (Infrastructure as Code): Se incluyen manifiestos de Terraform en src/terraform/ para la provisión automatizada de la VPC, Security Groups e instancias.

Acceso y Monitoreo
Para validar el funcionamiento en el entorno real, puedes utilizar los siguientes puntos de enlace:

Swagger UI (Nube): [http://3.14.87.73:8081/webjars/swagger-ui/index.html](http://3.14.87.73:8081/webjars/swagger-ui/index.html).

Base URL API: [http://3.14.87.73:8081/api/v1](http://3.14.87.73:8081/api/v1).

## Gestión del Ciclo de Vida (CI/CD Manual)
Dado que el microservicio se encuentra dockerizado, la actualización en la nube sigue un flujo de entrega continua simplificado:

Sincronización: Se realiza un pull de los cambios desde la rama main de GitHub directamente en la instancia de Ohio.

Reconstrucción: Uso de docker-compose up -d --build para compilar el código fuente dentro de la imagen y reiniciar los servicios con el nuevo artefacto.

Monitoreo: Seguimiento de flujos reactivos mediante docker-compose logs -f para asegurar que el contexto de Spring Boot y el driver de MongoDB Reactivo levanten correctamente.

## Notas Adicionales de Implementación Senior
Estrategia de Ramas: El desarrollo se centralizó en la rama franquicias y se integró mediante push --force a la rama main para mantener un historial de despliegue lineal y limpio.

Seguridad: Se implementó un archivo .gitignore estricto para prevenir la fuga de credenciales sensibles (como la llave .pem de AWS) y artefactos de compilación local (target/).
