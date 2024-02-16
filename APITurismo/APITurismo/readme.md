README - API Turismo
Esta documentación proporciona información sobre la API Turismo, que gestiona hoteles, museos y vuelos 
para una aplicación de turismo. 

A continuación se detallan los recursos utilizados, los atributos de cada clase y los endpoints disponibles.

Recursos Utilizados:

Lenguaje de Programación: Java
Framework: Spring Boot
Persistencia de Datos: JPA (Java Persistence API)
Base de Datos: Se utiliza un repositorio JPA para interactuar con la base de datos.

Clases y Atributos
1. Hotel
   Clase: Hotel

   Atributos:
   id (Integer): Identificador único del hotel.
   nombre (String): Nombre del hotel.
   precio (Double): Precio del hotel.
   direccion (String): Dirección del hotel.
   estrellas (Integer): Calificación del hotel en estrellas (valores de 0 a 5).

2. Museo
   Clase: Museo

   Atributos:
   id (Integer): Identificador único del museo.
   nombre (String): Nombre del museo.
   precio (Double): Precio de entrada al museo.
   direccion (String): Dirección del museo.
   descripcion (String): Breve descripción del museo.

3. Vuelo
   Clase: Vuelo

   Atributos:
   id (Integer): Identificador único del vuelo.
   nombre (String): Nombre del vuelo.
   precio (Double): Precio del vuelo.
   compañia (String): Compañía aérea del vuelo.

   Endpoints

1. HotelController
   Obtener todos los hoteles:

Método: GET
Endpoint: /api/turismo/hotel
Obtener hotel por ID:

Método: GET
Endpoint: /api/turismo/hotel/id/{id}
Obtener hotel por nombre:

Método: GET
Endpoint: /api/turismo/hotel/nombre/{nombre}
Obtener hoteles por precio inferior a:

Método: GET
Endpoint: /api/turismo/hotel/precio/{precio}
Obtener hoteles por dirección:

Método: GET
Endpoint: /api/turismo/hotel/direccion/{direccion}
Obtener hoteles por calificación (estrellas):

Método: GET
Endpoint: /api/turismo/hotel/estrellas/{estrellas}
Crear nuevo hotel:

Método: POST
Endpoint: /api/turismo/hotel/post
Parámetros: Hotel (en el cuerpo de la solicitud), token (en los parámetros)
Actualizar hotel existente:

Método: PUT
Endpoint: /api/turismo/hotel/put/{id}
Parámetros: id (en la URL), Hotel (en el cuerpo de la solicitud), token (en los parámetros)
Eliminar hotel por ID:

Método: DELETE
Endpoint: /api/turismo/hotel/delete/{id}
Parámetros: id (en la URL), token (en los parámetros)

2. MuseoController
   Obtener todos los museos:

Método: GET
Endpoint: /api/turismo/museo
Obtener museo por ID:

Método: GET
Endpoint: /api/turismo/museo/id/{id}
Obtener museo por nombre:

Método: GET
Endpoint: /api/turismo/museo/nombre/{nombre}
Obtener museos por precio de entrada inferior a:

Método: GET
Endpoint: /api/turismo/museo/precio/{precio}
Obtener museos por dirección (temática):

Método: GET
Endpoint: /api/turismo/museo/direccion/{direccion}
Crear nuevo museo:

Método: POST
Endpoint: /api/turismo/museo/post
Parámetros: Museo (en el cuerpo de la solicitud), token (en los parámetros)
Actualizar museo existente:

Método: PUT
Endpoint: /api/turismo/museo/put/{id}
Parámetros: id (en la URL), Museo (en el cuerpo de la solicitud), token (en los parámetros)
Eliminar museo por ID:

Método: DELETE
Endpoint: /api/turismo/museo/delete/{id}
Parámetros: id (en la URL), token (en los parámetros)

3. VueloController
   Obtener todos los vuelos:

Método: GET
Endpoint: /api/turismo/vuelo
Obtener vuelo por ID:

Método: GET
Endpoint: /api/turismo/vuelo/id/{id}
Obtener vuelo por nombre:

Método: GET
Endpoint: /api/turismo/vuelo/nombre/{nombre}
Obtener vuelos por precio inferior a:

Método: GET
Endpoint: /api/turismo/vuelo/precio/{precio}
Obtener vuelo por compañía:

Método: GET
Endpoint: /api/turismo/vuelo/compañia/{compañia}
Crear nuevo vuelo:

Método: POST
Endpoint: /api/turismo/vuelo/post
Parámetros: Vuelo (en el cuerpo de la solicitud), token (en los parámetros)
Actualizar vuelo existente:

Método: PUT
Endpoint: /api/turismo/vuelo/put/{id}
Parámetros: id (en la URL), Vuelo (en el cuerpo de la solicitud), token (en los parámetros)
Eliminar vuelo por ID:

Método: DELETE
Endpoint: /api/turismo/vuelo/delete/{id}
Parámetros: id (en la URL), token (en los parámetros)
Notas Importantes
La autenticación se realiza mediante el parámetro token en las solicitudes que lo requieren. Asegúrese de incluir el token correspondiente para acceder a funciones protegidas.

Los datos de entrada y salida de cada endpoint deben seguir la estructura definida para cada clase (Hotel, Museo, Vuelo). Consulte la documentación detallada de cada clase para obtener información sobre los atributos y sus tipos.