# Prueba GCA

## Descripción

La app visualiza por medio de un recyclerView la lista de los miembreos del equipo con el atributos de
nombre, apellido, cargo y foto, adicionalmente cuando se selecciona alguno de los miembros se puede
visualizar el detalle personalizado de los datos anteriormente mencionados agregando la lista de regalos.

La app muestra la información a partir del consumo de dos servicios y contiene base de datos para la
persistencia de la información cuando no hay internet.


## Instalación

Descarga e instalación del APK

## Tecnjologías utilizadas

Retrofit: Librería para el consumo de servicios.
Room: Librería para persistencia de la información.
Glide: Librería para cargar imágenes.
Binding: Librería para acceder a las vistas.

## Mejoras

La lista debe ser cíclica (si termina vuelve a mostrar los primeros elementos)

## servicios consumidos

http://20.197.225.77:8080/api/members
http://20.197.225.77:8080/api/members/{id}