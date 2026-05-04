API de gestión de franquicias (Pila reactiva)
Esta solución proporciona un sistema de gestión jerárquica para franquicias, sucursales y productos, construido sobre un paradigma no bloqueante para garantizar alta concurrencia y eficiencia.

Especificaciones Técnicas
Idioma: Java 21 (LTS).

Framework: Spring Boot 3.5.14 (WebFlux).

Persistencia: MongoDB (Driver Reactivo).

Documentación: Swagger / OpenAPI 3.

Arquitectura: Arquitectura limpia / hexagonal.

Instrucciones de Ejecución
Preparar el entorno (Docker) La aplicación requiere MongoDB para persistir los datos. Utilice el archivo docker-compose.yml para levantar la base de datos en el puerto 27018:
Bash docker-compose up -d 2. Compilar y ejecutar Una vez que el contenedor de la base de datos esté listo, inicia la API en el puerto 8081:

Bash ./mvnw clean spring-boot:run

Guía de Evaluación
Pruebas Automatizadas Se han implementado pruebas unitarias y de integración que validan flujos reactivos mediante StepVerifier. Para ejecutarlas:

Bash ./mvnw test Exploración con Swagger Para probar los endpoints de manera interactiva, acceda a la interfaz de Swagger UI: 🔗 http://localhost:8081/webjars/swagger-ui/index.html

Pruebas con Postman y JSON
A continuación, se detallan los ejemplos de carga para las operaciones principales.

Gestión de Estructura Crear Franquicia (POST /api/franquicias):
JSON { "name": "Franquicia de Alimentos SA" } Agregar Sucursal (POST /api/franquicias/{id}/branches):

JSON { "name": "Sucursal Centro" } 2. Operaciones de Inventario Agregar Producto a Sucursal (POST /api/branches/{id}/products):

JSON { "name": "Café Premium 500g", "stock": 100 } Actualizar Stock (PATCH /api/products/{id}/stock):

JSON { "stock": 150 }

Punto final de Lógica Avanzada (Punto 7)
GET /api/franchises/{id}/max-stock

Este punto final resuelve una agregación compleja de forma reactiva: identifica el producto con mayor stock por cada sucursal perteneciente a una franquicia específica.

Ejemplo de Respuesta:

JSON [ { "branchName": "Sucursal Centro", "productName": "Café Premium 500g", "stock": 150 }, { "branchName": "Sucursal Norte", "productName": "Té Verde Orgánico", "stock": 92 } ]

Detalles de Implementación Senior
Manejo de Excepciones: Se implementó un @ControllerAdvice global para gestionar errores de negocio y transformarlos en respuestas HTTP semánticas.

Seguridad en Pruebas: Se ajustó la configuración de seguridad reactivada para permitir la validación de endpoints POST y PATCH durante las pruebas de integración.
Mapeo Desacoplado: Uso de MapStruct para garantizar que las entidades de persistencia de MongoDB nunca se filtren a la capa de presentación.
Guía de Ejecución de Pruebas
La suite de pruebas está diseñada para validar tanto la lógica de negocio en la capa de dominio como la integración de los servicios en la capa de infraestructura.

Comandos Principales de Maven
El proyecto utiliza el Maven Wrapper (mvnw), lo que garantiza que las pruebas se ejecuten con la versión correcta de Maven sin necesidad de instalaciones globales.

Ejecución Completa Para limpiar artefactos previos y ejecutar toda la suite de pruebas del proyecto:
Bash ./mvnw clean test Este comando activará tanto los tests unitarios como los de integración localizados en src/test/java.

Ejecución Selectiva Si estás trabajando en una funcionalidad específica y deseas ahorrar tiempo, puedes filtrar la ejecución:
Por Clase:
./mvnw test -Dtest=NombreDeLaClaseTest

Por Método:
./mvnw test -Dtest=NombreDeLaClaseTest#nombreDelMetodo

Análisis de Tecnologías Utilizadas
Validación Reactiva con StepVerifier
Debido a que el proyecto utiliza Spring WebFlux, las pruebas de los casos de uso no usan aserciones simples. Se emplea ### StepVerifier para inspeccionar el flujo de señales:

Expectativas de emisión: Valida que se emiten los objetos correctos (onNext).

Finalización del flujo: Asegura que el flujo termine correctamente (onComplete).

Manejo de errores: Verifica que las excepciones de negocio sean lanzadas cuando corresponda (onError).

Pruebas de API con WebTestClient Para los controladores REST, se utiliza WebTestClient, el cual permite simular solicitudes HTTP de forma no bloqueante.

Se validan los Status Codes (201 Created, 200 OK, 404 Not Found).

Se verifica la estructura del JSON de respuesta.

Informes y Resultados
Al finalizar, Maven mostrará un resumen en la terminal. Si necesita un detalle técnico más profundo (por ejemplo, para depurar un error de integración), puede consultar los archivos generados automáticamente en: target/surefire-reports/

Consejo profesional: Antes de realizar un git push o un despliegue, siempre es recomendable ejecutar mvn clean test para asegurar que las nuevas modificaciones no hayan roto funcionalidades existentes (regresión).

☁️ Despliegue en la Nube (Ambiente de Evaluación)
Como valor agregado y para facilitar la revisión técnica, la API ha sido desplegada en una instancia de AWS (Amazon Web Services) , configurada con una pila productiva que incluye Docker y gestión de memoria optimizada.

Acceso a la Documentación Interactiva
La documentación completa de los endpoints, esquemas de datos y la capacidad de realizar pruebas en tiempo real está disponible en el siguiente enlace:

<<<<<<< Actualizado en sentido ascendente 🔗 Swagger UI Cloud: http://3.14.87.73:8081/webjars/swagger-ui/index.html (Nota: Una vez en el sitio, asegúrese de que la barra de exploración apunte a /v3/api-docs) . Es decir http://3.14.87.73:8081/v3/api-docs
🔗 Swagger UI Cloud: [ http://3.14.87.73:8081/v3/api-docs ] (Nota: Una vez en el sitio, asegúrese de que la barra de exploración apunte a /v3/api-docs) .

Cambios ocultos

Seguridad y Autenticación
Para garantizar la integridad de las operaciones de escritura (POST, PATCH, DELETE), se ha implementado Spring Security con autenticación básica. Utilice las siguientes credenciales para las pruebas:

Credencial	Valor
Usuario	admin
Contraseña	admin123
<<<<<<< Actualizado en la fuente original

Nota Técnica: El despliegue se realizó utilizando una arquitectura de contenedores, asegurando que el entorno de nube sea idéntico al entorno de desarrollo local, garantizando así la portabilidad de la solución. ======= Nota Técnica: El despliegue se realizó utilizando una arquitectura de contenedores, asegurando que el entorno de nube sea idéntico al entorno de desarrollo local, garantizando así la portabilidad de la solución.