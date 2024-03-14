# USER API RESTful

Esta API esta creada con la version 3.2.3 de Spring Boot, Java 17 y H2 DataBase.

## Modo de Uso

Para poder ejecutar la aplicación es necesario contar con la maquina virtual de java en nuestro local, el ruta de nuestra aplicacion es http://localhost:8080/user.

Descargar el código de la Branch “main”, descomprimir el archivo de ser necesario, ingresar a la carpeta y dirigirse a la carpeta target, ejemplo:
C:\Users\ltahu\Downloads\ user-main\target
Confirmamos que dentro se encuentre el JAR, user-0.0.1-SNAPSHOT.jar

Para ejecutar la aplicación necesitamos abrir una terminal e ingresar el siguiente comando y presionar enter, teniendo en cuenta actualizar la ruta por nuestra ruta local para iniciar con la ejecución.
java -jar miruta\target\user-0.0.1-SNAPSHOT.jar
Ejemplo: java -jar C:\Users\ltahu\Downloads\ user-main\target\user-0.0.1-SNAPSHOT.jar

Tener en cuenta que esta ruta de ejemplo esta basada en un sistema operativo Windows y que debe ser adecuada, según el sistema operativo que estemos utilizando.

Cuando ya tengamos listo ejecutándose nuestra aplicación podemos ir nuevamente a nuestra carpeta principal y ubicar el archivo UserApi.postman_collection.json el cual es una colección de Postman la cual podemos importar para iniciar con el llamado a nuestras API RESTful.

Dentro de la colección se encuentra el llamado a los siguientes servicios:
+	Crear Usuario: API principal para la creación de los usuarios.
+	Get List: API para obtener el listado de todos los Usuarios.
+	Set Regular Expression: API para realizar la configuración de la expresión regular para validar el correo y también la contraseña.
```
{
  "type": "email",
  "expression": "^[0-9,$]*$"
}
```
```
{
  "type": "password",
  "expression": "^[0-9,$]*$"
}
```

Por default la validación del correo tiene la expresion regular ``` ^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$ ```

Por default la validación de la contraseña tiene la expresion regular ``` \p{javaLowerCase}+ ```

+	Update User: API para actualizar la información del Usuario.

También puede realizar las pruebas a través de la interfaz de Swagger accediendo desde el navegador a http://localhost:8080/swagger-ui/index.html.