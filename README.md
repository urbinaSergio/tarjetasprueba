Hola, muy buenas tardes.

Comparto la prueba técnica correspondiente al servicio de gestión de tarjetas y transacciones.

Ejecución del Proyecto

Para iniciar el proyecto es necesario:

Tener instalado JDK 25.

Tener instalado y configurado PostgreSQL.

Restaurar la base de datos utilizando el archivo .tar incluido (backup).

Ejecutar el siguiente comando desde la raíz del proyecto:


mvn spring-boot:run

Base de Datos

El proyecto contiene dos tablas principales:

tarjetas

transacciones

Se adjunta el backup en formato .tar para facilitar la restauración.

tarjetasservicedb.tar

Frontend

El frontend se encuentra dentro del mismo proyecto, en la carpeta "Pagina Web", y fue desarrollado en JavaScript puro (sin frameworks).


Se incluye el archivo:

tarjetas-service.postman_collection.json

Este archivo contiene la colección de Postman para probar los endpoints REST y validar el funcionamiento de la API.


Atte. Sergio Urbina

Espero que les guste
