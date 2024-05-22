
# MUVIT


## Authors

- [@SamuelR13](https://www.github.com/samuelr13)
- [@tm7778](https://www.github.com/tm7778)
- [@HernanDavidA](https://github.com/HernanDavidA)


## UML Diagram

![App Screenshot](https://res.cloudinary.com/dspxw5z85/image/upload/v1716406055/Diagrama-UML_hr2khk.png)


## Trello Board

https://trello.com/b/mKub2o6h/muvit

## MUVIT

Muvit es una Aplicación de Sistemas de Acarreos Personalizados con transportistas en tiempo real.
Una aplicación de sistemas de acarreos personalizados al estilo Uber es una plataforma tecnológica diseñada para conectar a personas que necesitan servicios de transporte de bienes con transportistas disponibles en tiempo real. Esta aplicación facilita el proceso de solicitar, gestionar y realizar acarreos de manera eficiente y segura, utilizando tecnologías móviles y de geolocalización.

Componentes Principales de la Aplicación
Usuarios (Clientes y Transportistas):

### Clientes:
 Personas o empresas que necesitan transportar bienes de un lugar a otro.
Transportistas: Conductores con vehículos adecuados para el transporte de bienes, que se registran en la plataforma para ofrecer sus servicios.
Funcionalidades Clave:

#### Registro y Autenticación:

Los usuarios deben registrarse proporcionando información básica y verificando su identidad.
Los transportistas deben proporcionar detalles adicionales como licencia de conducir, seguro del vehículo, y antecedentes del vehículo.
Solicitud de Servicio:

Los clientes pueden ingresar detalles del acarreo, como punto de recogida y destino, tipo de bienes, y cualquier requerimiento especial.
La aplicación calcula una estimación de costos basada en la distancia, el tipo de bien, y otros factores relevantes.
Asignación de Transportista:
Beneficios de la Aplicación
### Conveniencia:
Solicitud de acarreos rápida y sin complicaciones.
Eficiencia: Optimización de rutas y disponibilidad de trabajo para transportistas.</br>
Transparencia: Costos y estado del acarreo claros y accesibles.</br>
Seguridad: Ambiente seguro y confiable gracias a verificaciones y calificaciones.</br>
Utilizando algoritmos de emparejamiento, la aplicación busca el transportista disponible más cercano y adecuado para el acarreo solicitado.
Los transportistas reciben una notificación y pueden aceptar o rechazar la solicitud.


### Pago y Facturación:

La aplicación ofrece múltiples métodos de pago (tarjeta de crédito, débito, transferencias electrónicas).
Al finalizar el acarreo, se genera automáticamente una factura detallada para el cliente

# DEPLOY  LOCAL
**1. Clonar repositorio Backend (este mismo)**
 https://github.com/tm7778/Backend-Muvit.

 
**2. Clonar repositorio Frontend**
https://github.com/SamuelR13/MuvitFrontend


**3. Crear base de datos en el Localhost desde MySQLWorkbench o DBeaver**
 **IMPORTANTE** LA BASE DE DATOS DEBE LLAMARSE  "muvitdb" para importar la base de datos predeterminada con ejemplos ya creados

 
**4. Configurar SpringBoot**
Abrir en VS el repositorio de Backend y buscar el archivo "application.properties" en la ruta
"../src/main/resources/application.properties"
```java
spring.datasource.url=jdbc:mysql://localhost:3306/${nombre de la base de datos}
spring.datasource.username=${Usuario del localhost}
spring.datasource.password=${Contraseña del localhost}
```
Asegurarse que el nombre de la base de datos coincida con el nombre de la base de datos que se creò anteriormente y que el username y password sean los correctos 



**5. Importar base de datos de predeterminada IMPORTANTE!** 
Descargar el .zip "MuvitDB.zip" y descomprimirlo en la ubicacion deseada
(https://github.com/tm7778/Backend-Muvit/blob/main/MuvitDB.zip)
Seleccionar la base de datos e importar la carpeta con los datos 
(https://res.cloudinary.com/dspxw5z85/video/upload/v1716409280/Screencast_from_22-05-24_15_16_49_kz3jht.webm)


**6. Ejecutar SpringBoot desde VisualStudio**
Con el repositorio backend abierto en VS, ejecutar SpringBoot desde la extencion SpringBoot Dashboard
**7. Ejecturar el FrontEnd con LiveServer**
Con el repositorio frontend abierto en VS, ejecutar LiverServer para tener el frontend en el navegar 


**ALGUNAS FUNCIONALIDADES BACKEND NO ESTAN IMPLEMENTADAS EN EL FRONTEND**
**TODOS LOS ENDPOINT SE PUEDEN PROBAR DESDE POSTMAN O SWAGGER**
**COLECCION DE POSTMAN** : (https://github.com/tm7778/Backend-Muvit/blob/main/MUVIT.postman_collection.json)





