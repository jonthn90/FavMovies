# FavMovies

Observaciones especificas:

- Se consumio la API https://developers.themoviedb.org/3/movies/get-top-rated-movies

- Se hizo el paginado con Paging Arch de Jetpack, el cual no permite hacer update individuales de los items, por lo cual al eliminar desde la pestaña de Movies una favorita, se tiene que recargar toda la lista para validar contra lo guardado en la BD de favoritas.

- Debido a la logica, se vio que era mejor usar un solo ViewModel del Main Activity, sin embargo cada seccion (Movies y Fav Movies), esta lista con su ViewModel ligado a su NavGraph.

- Por mejorar: no esta totalmente optimizado manejar los escenarios sin internet, faltan algunos mensajes de alerta.Botones para reintentar recarga.

- ADICIONAL: se agrego la funconalidad de si se le da clic en la pelicula se pasa a la pantala de detalle de la misma

- ADICIONAL: se configuro Firebase Crashlytics y Analytics.


Instrucciones para correr el codigo.

- Opción 1: Clonar el repositorio y abrir el la carpeta principal en Android Studio. Ejecutar de forma normal, no requiere nada extra.

- Opción 2: en la raiz del repositorio esta el archivo favmovies.apk, el cual se puede isntalar en un dipositivo android que se le hayan concedido permisos de instalacion de origines desconocidos.

