
# DiccionarioApp

Aplicación móvil para Android desarrollada en **Java** utilizando **Android Studio** y **SQLite**. Permite registrar, consultar, buscar, editar y eliminar palabras junto con sus definiciones de manera local en el dispositivo.

## Características principales

* **Pantalla de inicio (`PantallaCarga`):** Muestra el logotipo de la aplicación sobre un fondo personalizado durante unos segundos antes de redirigir automáticamente a la pantalla principal.
* **Listado y búsqueda en tiempo real (`MainActivity`):** Muestra todas las palabras almacenadas mediante un `RecyclerView` y permite filtrarlas dinámicamente usando un `SearchView`.
* **Gestión completa de registros (CRUD):**
  * **Agregar (`MainActivity2`):** Formulario para guardar nuevas palabras y sus definiciones.
  * **Visualizar (`VerActivity`):** Pantalla de detalle para consultar la información completa de una palabra seleccionada.
  * **Editar y eliminar (`editarActivity` / `VerActivity`):** Opciones para modificar el contenido existente o eliminar registros de la base de datos.
* **Almacenamiento local:** Persistencia de datos en el dispositivo mediante SQLite sin necesidad de conexión a internet.

## Estructura del proyecto

```text
app/src/main/java/com/example/myapplication/
├── adaptadores/
│   └── listaPalabrasAdapter.java   # Adaptador para el RecyclerView y lógica de filtrado
├── db/
│   ├── DbHelper.java               # Creación y actualización de la base de datos SQLite
│   └── DbPalabras.java             # Operaciones CRUD (insertar, mostrar, editar, eliminar)
├── entidades/
│   └── Palabras.java               # Modelo de datos (ID, palabra y definición)
├── PantallaCarga.java              # Actividad inicial (Launcher / Splash)
├── MainActivity.java               # Pantalla principal con lista y buscador
├── MainActivity2.java              # Pantalla para agregar nuevos registros
├── VerActivity.java                # Pantalla de detalle y opción de borrado
└── editarActivity.java             # Pantalla para editar palabras existentes
```

## Tecnologías utilizadas

* **Lenguaje:** Java
* **Entorno de desarrollo:** Android Studio
* **Base de datos:** SQLite (`SQLiteOpenHelper`)
* **Componentes de interfaz:** `ConstraintLayout`, `RelativeLayout`, `RecyclerView`, `SearchView`, `FloatingActionButton`

## Cómo ejecutar el proyecto

1. Clona este repositorio en tu computadora:
   ```bash
   git clone https://github.com/Joaquinb-b/DiccionarioApp.git
   ```
2. Abre **Android Studio** y selecciona **File > Open** buscando la carpeta `DiccionarioApp`.
3. Espera a que **Gradle** termine de sincronizar las dependencias del proyecto.
4. Conecta un dispositivo Android (con depuración USB activada) o inicia un emulador y presiona el botón **Run** (`Shift + F10`).
