# Bangkok App - Tienda de Ropa Online

## 📱 Presentación del Proyecto

Estimado profesor, le presento el proyecto final de desarrollo de aplicaciones móviles: **Bangkok App**, una aplicación móvil de comercio electrónico (e-commerce) desarrollada para Android que permite a los usuarios explorar y comprar productos de ropa. 

Este proyecto ha sido desarrollado como trabajo final del curso, implementando una arquitectura moderna con Jetpack Compose, Room Database y siguiendo las mejores prácticas de desarrollo Android. La aplicación incluye un sistema completo de autenticación, catálogo de productos, carrito de compras, panel de administración y, como funcionalidad destacada de esta versión final, un sistema de geolocalización de tiendas físicas con búsqueda visual por cámara.

### 🎉 Funcionalidades Implementadas

**Versión Final - Sistema Completo de E-commerce con Geolocalización**

Esta versión final incluye todas las funcionalidades base más las nuevas características implementadas:

**Funcionalidades Base:**
- **Vista Previa del Producto**: Muestra la imagen del producto en tamaño grande con todas sus etiquetas y descuentos
- **Selector de Tallas**: Permite elegir entre diferentes tallas (XS, S, M, L, XL, XXL) antes de agregar al carrito
- **Descripción Detallada**: Información completa del producto incluyendo características, materiales e instrucciones de cuidado
- **Botón de Añadir al Carrito**: Con validación que asegura que se seleccione una talla antes de agregar
- **Productos Similares**: Muestra una lista horizontal de productos relacionados de la misma categoría
- **Gestión de Carrito Mejorada**: El carrito guarda la talla seleccionada para cada producto

**Nuevas Funcionalidades Implementadas (Versión Final):**
- **Sistema de Geolocalización de Tiendas**: Implementación de un mapa interactivo usando OpenStreetMap (OSMDroid) que muestra la ubicación de nuestras tiendas físicas
- **Búsqueda Visual con Cámara**: Funcionalidad de búsqueda de productos mediante código de barras usando la cámara del dispositivo (implementación visual)
- **Pantalla de Tiendas**: Nueva pantalla dedicada que muestra un mapa con marcadores de las tiendas, información detallada de cada sucursal (dirección, teléfono, horarios) y acceso a la búsqueda por cámara
- **Integración en HomeScreen**: Sección de tiendas integrada en la pantalla principal con navegación directa
- **Mejoras Visuales**: Pulido general del diseño, espaciados y consistencia visual en toda la aplicación

## 🛠️ Tecnologías Utilizadas

Esta aplicación está construida con tecnologías modernas de Android:

### Lenguaje y Framework Principal
- **Kotlin**: Lenguaje de programación oficial para Android
- **Jetpack Compose**: Framework moderno de Google para crear interfaces de usuario
- **Material Design 3**: Sistema de diseño de Google para una experiencia visual consistente

### Almacenamiento de Datos
- **Room Database**: Base de datos local para almacenar productos, usuarios y carrito de compras
- **SQLite**: Motor de base de datos que Room utiliza internamente
- **TypeConverters**: Para convertir objetos complejos (listas, enums) a formato JSON

### Arquitectura y Patrones
- **MVVM (Model-View-ViewModel)**: Arquitectura que separa la lógica de negocio de la interfaz
- **Repository Pattern**: Para manejar el acceso a datos de manera centralizada
- **Dependency Injection**: Usando Koin para gestionar las dependencias del proyecto

### Librerías Adicionales
- **Coil**: Para cargar y mostrar imágenes desde URLs de internet
- **Navigation Compose**: Para navegar entre diferentes pantallas de la app
- **Coroutines**: Para manejar operaciones asíncronas de manera eficiente
- **Gson**: Para convertir objetos a JSON y viceversa
- **KSP (Kotlin Symbol Processing)**: Para procesar anotaciones de Room
- **OSMDroid**: Librería para mostrar mapas de OpenStreetMap (implementada en la versión final)
- **CameraX**: Framework de Google para funcionalidad de cámara (implementada en la versión final)
- **Accompanist Permissions**: Para manejar permisos de runtime de ubicación y cámara
- **Google Play Services Location**: Para obtener la ubicación del usuario

### Version
- **Compile SDK**: 34
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34
- **Gradle**: 8.x
- **Kotlin**: 1.9.22
- **Compose BOM**: 2024.02.00
- **Compose Compiler**: 1.5.8

## 🏗️ ¿Cómo Funciona?

La aplicación sigue una arquitectura limpia y organizada:

### Estructura de Capas

1. **Capa de Presentación (UI)**
   - Pantallas: Home, Productos, Detalle de Producto, Carrito, Perfil, Login, etc.
   - Componentes reutilizables: Botones, Cards, Selectores
   - ViewModels: Gestionan el estado y la lógica de cada pantalla

2. **Capa de Dominio (Lógica de Negocio)**
   - Repositorios: Acceden a los datos y ejecutan operaciones
   - Modelos: Estructuran la información (Producto, Usuario, Carrito)

3. **Capa de Datos**
   - Room Database: Almacena datos localmente
   - DAOs (Data Access Objects): Consultas a la base de datos
   - Entities: Representación de las tablas en la base de datos

### Flujo de Datos

1. El usuario interactúa con la interfaz (toca un botón, selecciona un producto)
2. La UI llama al ViewModel correspondiente
3. El ViewModel usa el Repository para obtener o guardar datos
4. El Repository accede a la base de datos a través de los DAOs
5. Los datos se actualizan y fluyen de vuelta a la UI automáticamente

### Base de Datos

La aplicación usa una base de datos local SQLite gestionada por Room Database con las siguientes tablas:

- **users**: Información de los usuarios registrados (id, nombre, email, contraseña, rol, etc.)
- **products**: Catálogo de productos con descripciones, precios, tallas disponibles y detalles
- **categories**: Categorías de productos (NEW, HOODIES, TOP, BOTTOMS, ACCESSORIES)
- **cart_items**: Items en el carrito de compras con la talla seleccionada y cantidad

**Nota sobre las Tiendas**: Las tiendas físicas están implementadas como modelos de datos en memoria (no se almacenan en la base de datos) ya que son solo dos ubicaciones fijas. Esto permite una implementación más simple y eficiente para este caso de uso específico.

## 🚀 Cómo Iniciar el Proyecto desde Android Studio

### Paso 1: Preparar el Entorno

1. **Descargar Android Studio**
   - Ve a [developer.android.com/studio](https://developer.android.com/studio)
   - Descarga e instala Android Studio (versión más reciente recomendada)
   - Durante la instalación, asegúrate de instalar el SDK de Android y las herramientas necesarias

2. **Configurar el SDK**
   - Abre Android Studio
   - Ve a `File > Settings > Appearance & Behavior > System Settings > Android SDK`
   - Asegúrate de tener instalado:
     - Android SDK Platform 34
     - Android SDK Build-Tools
     - Android Emulator (si planeas usar un emulador)

### Paso 2: Abrir el Proyecto

1. **Clonar o Descargar el Proyecto**
   - Si el proyecto está en Git, clónalo con: `git clone [https://github.com/MURRCIA/app]`
   - O descarga el proyecto y extrae los archivos

2. **Abrir en Android Studio**
   - Abre Android Studio
   - Selecciona `File > Open`
   - Navega a la carpeta del proyecto y selecciona la carpeta raíz (donde está el archivo `build.gradle.kts`)
   - Haz clic en `OK`

3. **Sincronizar el Proyecto**
   - Android Studio detectará automáticamente que es un proyecto Gradle
   - Aparecerá una notificación pidiendo sincronizar el proyecto
   - Haz clic en `Sync Now` o espera a que se sincronice automáticamente
   - Esto descargará todas las dependencias necesarias (puede tomar algunos minutos la primera vez)

### Paso 3: Configurar el Emulador o Dispositivo Físico

**Opción A: Usar un Emulador (Recomendado para empezar)**

1. **Crear un AVD (Android Virtual Device)**
   - Haz clic en el icono de "Device Manager" en la barra de herramientas
   - O ve a `Tools > Device Manager`
   - Haz clic en `Create Device`
   - Selecciona un dispositivo (por ejemplo, Pixel 5)
   - Selecciona una imagen del sistema (recomendado: API 34, Android 14)
   - Haz clic en `Finish`

2. **Iniciar el Emulador**
   - En Device Manager, haz clic en el botón de "play" (▶) junto al emulador creado
   - Espera a que el emulador se inicie (puede tomar un minuto)

**Opción B: Usar un Dispositivo Físico**

1. **Habilitar Opciones de Desarrollador**
   - Ve a `Configuración > Acerca del teléfono`
   - Toca 7 veces en "Número de compilación"
   - Esto habilitará las opciones de desarrollador

2. **Habilitar Depuración USB**
   - Ve a `Configuración > Opciones de desarrollador`
   - Activa "Depuración USB"
   - Conecta tu dispositivo al computador con un cable USB

3. **Verificar Conexión**
   - En Android Studio, deberías ver tu dispositivo en la lista de dispositivos disponibles

### Paso 4: Ejecutar la Aplicación

1. **Seleccionar el Dispositivo**
   - En la barra superior de Android Studio, verás un menú desplegable con los dispositivos disponibles
   - Selecciona tu emulador o dispositivo físico

2. **Ejecutar la App**
   - Haz clic en el botón verde de "Run" (▶) en la barra de herramientas
   - O presiona `Shift + F10` (Windows/Linux) o `Control + R` (Mac)
   - Android Studio compilará el proyecto y lo instalará en tu dispositivo

3. **Esperar la Compilación**
   - La primera vez puede tomar varios minutos
   - Verás el progreso en la barra inferior de Android Studio
   - Cuando termine, la app se abrirá automáticamente en tu dispositivo

### Paso 5: Probar la Aplicación

1. **Pantalla de Bienvenida**
   - La app iniciará en la pantalla de bienvenida (Splash Screen)
   - Luego verás la pantalla de inicio de sesión

2. **Iniciar Sesión**
   - Puedes registrarte creando una cuenta nueva
   - O usar las credenciales de administrador:
     - Email: `admin@bangkok.com`
     - Contraseña: `admin123`

3. **Explorar Funcionalidades**
   - Navega por el catálogo de productos
   - Toca un producto para ver su detalle completo
   - Selecciona una talla y agrega productos al carrito
   - Revisa tu carrito de compras
   - Si eres administrador, accede al panel de administración

## 📁 Estructura del Proyecto

```
app/
├── src/
│   └── main/
│       ├── java/com/bangkok/app/
│       │   ├── data/
│       │   │   ├── database/          # Base de datos Room
│       │   │   │   ├── entities/      # Tablas de la base de datos
│       │   │   │   ├── dao/           # Consultas a la base de datos
│       │   │   │   └── converters/    # Convertidores de tipos
│       │   │   ├── models/            # Modelos de datos
│       │   │   ├── repository/        # Repositorios de datos
│       │   │   └── SessionManager.kt  # Gestión de sesión
│       │   ├── ui/
│       │   │   ├── components/        # Componentes reutilizables
│       │   │   ├── screens/           # Pantallas de la app
│       │   │   │   ├── home/          # Pantalla principal
│       │   │   │   ├── products/      # Lista y detalle de productos
│       │   │   │   ├── cart/          # Carrito de compras
│       │   │   │   ├── profile/       # Perfil de usuario
│       │   │   │   ├── auth/          # Login y registro
│       │   │   │   ├── admin/         # Panel de administración
│       │   │   │   └── stores/        # Pantalla de tiendas con geolocalización
│       │   │   └── theme/             # Tema y estilos
│       │   └── MainActivity.kt        # Actividad principal
│       └── res/                       # Recursos (imágenes, strings, etc.)
└── build.gradle.kts                   # Configuración del proyecto
```

## ✨ Características Principales

### Para Usuarios Regulares
- ✅ Registro e inicio de sesión
- ✅ Explorar catálogo de productos por categorías
- ✅ Ver detalles completos de productos
- ✅ Seleccionar tallas de productos
- ✅ Agregar productos al carrito
- ✅ Ver y gestionar carrito de compras
- ✅ Ver perfil de usuario
- ✅ **Localizar tiendas físicas en un mapa interactivo** (Nueva)
- ✅ **Búsqueda de productos mediante cámara** (Nueva - implementación visual)
- ✅ **Ver información detallada de tiendas** (dirección, teléfono, horarios) (Nueva)

### Para Administradores
- ✅ Todas las funciones de usuario regular
- ✅ Panel de administración
- ✅ Crear, editar y eliminar productos
- ✅ Gestionar categorías
- ✅ Ver estadísticas (próximamente)

## 🔧 Solución de Problemas Comunes

### Error: "SDK not found"
- **Solución**: Ve a `File > Settings > Android SDK` y asegúrate de tener el SDK instalado

### Error: "Gradle sync failed"
- **Solución**: 
  1. Ve a `File > Invalidate Caches / Restart`
  2. Selecciona `Invalidate and Restart`
  3. Espera a que Android Studio reinicie y sincronice de nuevo

### La app no se instala en el dispositivo
- **Solución**: 
  1. Verifica que la depuración USB esté habilitada
  2. Acepta el permiso de depuración en tu dispositivo
  3. Verifica que el dispositivo esté conectado correctamente

### Error de compilación relacionado con Room
- **Solución**: 
  1. Limpia el proyecto: `Build > Clean Project`
  2. Reconstruye el proyecto: `Build > Rebuild Project`
  3. Si persiste, elimina la carpeta `.gradle` y sincroniza de nuevo

## 📝 Notas Importantes para el Profesor

- **Base de Datos**: La primera vez que se ejecuta la app, se crea automáticamente la base de datos y se insertan datos de ejemplo mediante un callback
- **Usuario Administrador**: Se crea automáticamente con las credenciales: `admin@bangkok.com` / `admin123`
- **Imágenes**: Las imágenes de productos se cargan desde URLs externas, se requiere conexión a internet
- **Migraciones**: La base de datos implementa un sistema de migraciones que actualiza automáticamente el esquema cuando cambia la versión (actualmente en versión 3)
- **Permisos**: La aplicación solicita permisos de ubicación y cámara en runtime (Android 6.0+)
- **Mapas**: Se utiliza OpenStreetMap a través de OSMDroid, no requiere API key de Google Maps
- **Búsqueda por Cámara**: La funcionalidad de búsqueda por cámara está implementada visualmente (abre la cámara) pero no procesa códigos de barras. Para una implementación completa se requeriría integrar ML Kit o ZXing

## 🎓 Explicación Técnica del Proyecto

### Implementación de la Funcionalidad de Tiendas

Como parte del trabajo final, he implementado un sistema completo de geolocalización de tiendas que incluye:

1. **Modelo de Datos**: Creé un modelo `Store` que contiene información de cada tienda (nombre, dirección, coordenadas GPS, teléfono, horarios)

2. **Pantalla de Tiendas (StoresScreen)**: 
   - Implementé un mapa interactivo usando OSMDroid que muestra las tiendas físicas
   - Cada tienda se muestra como un marcador en el mapa con su ubicación exacta
   - La pantalla incluye una lista detallada de cada tienda con toda su información
   - Integré el componente de búsqueda por cámara en esta pantalla

3. **Búsqueda Visual con Cámara**:
   - Implementé un componente reutilizable `CameraSearchButton` que abre la cámara del dispositivo
   - Utiliza CameraX para acceder a la cámara
   - Maneja permisos de runtime para acceso a la cámara
   - **Nota importante**: Esta funcionalidad es solo visual (abre la cámara) ya que el procesamiento de códigos de barras requeriría librerías adicionales de reconocimiento de imágenes

4. **Geolocalización**:
   - Implementé el uso de Google Play Services Location para obtener la ubicación del usuario
   - El mapa puede mostrar la ubicación del usuario si se conceden los permisos
   - Los marcadores de las tiendas se posicionan usando coordenadas GPS (latitud y longitud)

5. **Integración en la Navegación**:
   - Agregué una sección destacada en el HomeScreen que permite navegar a la pantalla de tiendas
   - Incluí la opción "Tiendas" en el menú de navegación lateral (Drawer)
   - Configuré la navegación usando Navigation Compose

### Arquitectura y Patrones Utilizados

Este proyecto implementa:
- **Arquitectura MVVM**: Separación clara entre la UI (Compose), la lógica de negocio (ViewModel) y los datos (Repository)
- **Room Database**: Base de datos local para persistencia de datos
- **Dependency Injection con Koin**: Para gestionar las dependencias del proyecto
- **Navigation Compose**: Para la navegación entre pantallas
- **StateFlow y Coroutines**: Para el manejo reactivo del estado
- **Material Design 3**: Para una experiencia visual moderna y consistente

## 📊 Comandos SQL Utilizados en el Proyecto

A continuación explico los comandos SQL principales que Room Database genera y utiliza en este proyecto:

### CREATE TABLE - Creación de Tablas

**Tabla de Productos (products):**
```sql
CREATE TABLE IF NOT EXISTS `products` (
    `id` TEXT NOT NULL, 
    `name` TEXT NOT NULL, 
    `description` TEXT NOT NULL, 
    `price` REAL NOT NULL, 
    `category` TEXT NOT NULL, 
    `imageUrl` TEXT, 
    `tags` TEXT NOT NULL, 
    `isFeatured` INTEGER NOT NULL, 
    `isNewArrival` INTEGER NOT NULL, 
    `discountPercentage` INTEGER, 
    `detailedDescription` TEXT, 
    `availableSizes` TEXT NOT NULL, 
    `createdAt` INTEGER NOT NULL, 
    `updatedAt` INTEGER NOT NULL, 
    PRIMARY KEY(`id`)
)
```
**Explicación**: Crea la tabla de productos con todos sus campos. Los campos `tags` y `availableSizes` se almacenan como JSON (TEXT) y se convierten usando TypeConverters. Los campos booleanos se almacenan como INTEGER (0 o 1).

**Tabla de Categorías (categories):**
```sql
CREATE TABLE IF NOT EXISTS `categories` (
    `id` TEXT NOT NULL, 
    `name` TEXT NOT NULL, 
    `imageUrl` TEXT NOT NULL, 
    `productCount` INTEGER NOT NULL, 
    `createdAt` INTEGER NOT NULL, 
    PRIMARY KEY(`id`)
)
```
**Explicación**: Tabla simple que almacena las categorías de productos con su nombre, imagen y contador de productos.

**Tabla de Usuarios (users):**
```sql
CREATE TABLE IF NOT EXISTS `users` (
    `id` TEXT NOT NULL, 
    `fullName` TEXT NOT NULL, 
    `email` TEXT NOT NULL, 
    `password` TEXT NOT NULL, 
    `phone` TEXT NOT NULL, 
    `profileImageUrl` TEXT, 
    `registrationDate` TEXT NOT NULL, 
    `isEmailVerified` INTEGER NOT NULL, 
    `role` TEXT NOT NULL, 
    `preferences` TEXT NOT NULL, 
    `createdAt` INTEGER NOT NULL, 
    PRIMARY KEY(`id`)
)
```
**Explicación**: Almacena la información de los usuarios. El campo `role` permite distinguir entre usuarios normales y administradores. El campo `preferences` almacena un objeto JSON con las preferencias del usuario.

**Tabla de Carrito (cart_items):**
```sql
CREATE TABLE IF NOT EXISTS `cart_items` (
    `id` TEXT NOT NULL, 
    `userId` TEXT NOT NULL, 
    `productId` TEXT NOT NULL, 
    `quantity` INTEGER NOT NULL, 
    `addedAt` INTEGER NOT NULL, 
    `selectedSize` TEXT, 
    PRIMARY KEY(`id`), 
    FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE, 
    FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
```
**Explicación**: Tabla que relaciona usuarios con productos en el carrito. Usa claves foráneas para mantener la integridad referencial. Si se elimina un usuario o producto, se eliminan automáticamente los items del carrito relacionados (ON DELETE CASCADE).

### SELECT - Consultas de Lectura

**Obtener todos los productos:**
```sql
SELECT * FROM products ORDER BY createdAt DESC
```
**Explicación**: Recupera todos los productos ordenados por fecha de creación (más recientes primero).

**Obtener productos por categoría:**
```sql
SELECT * FROM products WHERE category = ? ORDER BY name ASC
```
**Explicación**: Filtra productos por categoría específica. El `?` es un parámetro que se reemplaza con el valor de la categoría buscada.

**Obtener items del carrito de un usuario:**
```sql
SELECT * FROM cart_items 
WHERE userId = ? 
ORDER BY addedAt DESC
```
**Explicación**: Obtiene todos los items del carrito de un usuario específico, ordenados por fecha de adición.

**JOIN para obtener productos del carrito con su información:**
```sql
SELECT cart_items.*, products.name, products.price, products.imageUrl 
FROM cart_items 
INNER JOIN products ON cart_items.productId = products.id 
WHERE cart_items.userId = ?
```
**Explicación**: Combina información del carrito con los datos completos del producto usando un INNER JOIN. Esto permite mostrar el nombre, precio e imagen del producto junto con la cantidad y talla seleccionada.

### INSERT - Inserción de Datos

**Insertar un nuevo producto:**
```sql
INSERT INTO products (id, name, description, price, category, imageUrl, tags, isFeatured, isNewArrival, discountPercentage, detailedDescription, availableSizes, createdAt, updatedAt) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
```
**Explicación**: Inserta un nuevo producto en la base de datos. Todos los parámetros `?` se reemplazan con los valores reales del producto.

**Agregar item al carrito:**
```sql
INSERT INTO cart_items (id, userId, productId, quantity, addedAt, selectedSize) 
VALUES (?, ?, ?, ?, ?, ?)
```
**Explicación**: Agrega un nuevo item al carrito de un usuario con la cantidad y talla seleccionada.

### UPDATE - Actualización de Datos

**Actualizar un producto:**
```sql
UPDATE products 
SET name = ?, description = ?, price = ?, category = ?, imageUrl = ?, tags = ?, isFeatured = ?, isNewArrival = ?, discountPercentage = ?, detailedDescription = ?, availableSizes = ?, updatedAt = ? 
WHERE id = ?
```
**Explicación**: Actualiza los campos de un producto existente identificado por su ID. El campo `updatedAt` se actualiza para registrar cuándo se modificó.

**Actualizar cantidad en el carrito:**
```sql
UPDATE cart_items 
SET quantity = ? 
WHERE id = ?
```
**Explicación**: Modifica la cantidad de un item específico en el carrito.

### DELETE - Eliminación de Datos

**Eliminar un producto:**
```sql
DELETE FROM products WHERE id = ?
```
**Explicación**: Elimina un producto de la base de datos. Debido a la clave foránea con CASCADE, también se eliminan automáticamente los items del carrito que referencian este producto.

**Eliminar item del carrito:**
```sql
DELETE FROM cart_items WHERE id = ?
```
**Explicación**: Elimina un item específico del carrito de compras.

### ALTER TABLE - Migraciones de Base de Datos

**Agregar columna de rol a usuarios (Migración 1 a 2):**
```sql
ALTER TABLE users ADD COLUMN role TEXT NOT NULL DEFAULT 'USER'
```
**Explicación**: Agrega una nueva columna `role` a la tabla de usuarios con un valor por defecto 'USER'. Esta migración se ejecutó cuando se actualizó la versión de la base de datos de 1 a 2.

**Agregar campos a productos y carrito (Migración 2 a 3):**
```sql
ALTER TABLE products ADD COLUMN detailedDescription TEXT
ALTER TABLE products ADD COLUMN availableSizes TEXT NOT NULL DEFAULT '[]'
ALTER TABLE cart_items ADD COLUMN selectedSize TEXT
```
**Explicación**: Agrega nuevas columnas para soportar descripciones detalladas, tallas disponibles (almacenadas como JSON) y la talla seleccionada en el carrito. Esta migración permite actualizar el esquema sin perder datos existentes.

### Consideraciones Importantes

- **TypeConverters**: Los campos complejos (listas, enums, objetos) se convierten a JSON usando Gson antes de almacenarse como TEXT
- **Claves Foráneas**: Las relaciones entre tablas se mantienen mediante FOREIGN KEY constraints que aseguran la integridad referencial
- **CASCADE DELETE**: Cuando se elimina un usuario o producto, los items relacionados en el carrito se eliminan automáticamente
- **Índices**: Room puede crear índices automáticamente en campos frecuentemente consultados para mejorar el rendimiento

## 📞 Soporte

Si tienes preguntas o encuentras algún problema:
1. Revisa la sección de "Solución de Problemas Comunes" arriba
2. Verifica que tengas todas las dependencias instaladas
3. Asegúrate de estar usando una versión compatible de Android Studio

## 📄 Licencia

Este proyecto es educativo y está destinado para fines de aprendizaje.

---

**Desarrollado con ❤️ usando Kotlin y Jetpack Compose**

