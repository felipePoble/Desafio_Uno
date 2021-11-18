Spring BootSpring Boot# Solución desafío 1

- Nivel escogido: 3
- Lenguaje utilizado: Java, utilizando el framework Spring Boot.

## Instrucciones de compilación y ejecución

Se debe tener maven instalado.

Para ejecutar el proyecto, se debe tener libre el puerto 8081.

> Si el puerto no está disponible en su máquina, puede elegir otro puerto modificando el archivo `src\main\resources\application.properties`, cambiando la línea `server.port=8081` .

Teniendo el puerto a usar libre, basta con ejecutar el siguiente comando (en una terminal) en la carpeta raíz del proyecto:

```
mvn package
```

Posteriormente, se debe ejecutar el archivo JAR creado en la carpeta `target ` desde una terminal:

```
java -jar target/desafio-1.0.0.jar
```

Una vez finalizado, se puede entrar a la URL de Swagger local: http://127.0.0.1:8081/swagger-ui.html

> Si cambiaste el puerto, modificalo en la URL para ingresar a Swagger:
>
> - http://127.0.0.1: {tu puerto} /swagger-ui.html

Posteriormente hay dos caminos posibles

1. Se debe levantar el proyecto GDD localmente y consultarlo.
2. Modificar la propiedad `rutaGdd` del archivo `src\main\resources\config.properties` para que apunte hacia la URI donde esté disponible el servicio REST de GDD.

Hecho esto, se sugieren estas dos formas para obtener la respuesta del servicio REST solicitado por el desafío:

1. Utilizar Swagger para ejecutar la petición `/desafio/api`del `MainController`.
2. Utilizar un navegador u otra aplicación para consultar la ruta  http://127.0.0.1:8081/desafio/api/

> En la carpeta raíz del desafio va un archivo JSON (respuestaDesafio.JSON) con una de las respuestas obtenidas por el servicio creado.