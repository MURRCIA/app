# Bangkok App - Tienda de Ropa Online

## 📱 ¿Qué es este proyecto?

Bangkok App es una aplicación móvil de comercio electrónico (e-commerce) desarrollada para Android que permite a los usuarios explorar y comprar productos de ropa. La aplicación incluye un sistema completo de autenticación, catálogo de productos, carrito de compras y panel de administración.

### 🎉 Nuevas Funcionalidades en esta Versión

**Versión 1.0 - Pantalla de Detalle de Producto con Carrito**

Esta nueva versión incluye una pantalla completa de detalle de producto con las siguientes características:

- **Vista Previa del Producto**: Muestra la imagen del producto en tamaño grande con todas sus etiquetas y descuentos
- **Selector de Tallas**: Permite elegir entre diferentes tallas (XS, S, M, L, XL, XXL) antes de agregar al carrito
- **Descripción Detallada**: Información completa del producto incluyendo características, materiales e instrucciones de cuidado
- **Botón de Añadir al Carrito**: Con validación que asegura que se seleccione una talla antes de agregar
- **Productos Similares**: Muestra una lista horizontal de productos relacionados de la misma categoría
- **Gestión de Carrito Mejorada**: Ahora el carrito guarda la talla seleccionada para cada producto

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

### Version
- **Compile SDK**: 34
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34
- **Gradle**: 8.x
- **Kotlin**: 1.8+

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

La aplicación usa una base de datos local con las siguientes tablas:

- **users**: Información de los usuarios registrados
- **products**: Catálogo de productos con descripciones, precios y tallas disponibles
- **categories**: Categorías de productos (Camisetas, Sudaderas, Chaquetas, etc.)
- **cart_items**: Items en el carrito de compras con la talla seleccionada

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
   - Si el proyecto está en Git, clónalo con: `git clone [URL del repositorio]`
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
│       │   │   │   └── admin/         # Panel de administración
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

## 📝 Notas Importantes

- **Base de Datos**: La primera vez que ejecutes la app, se creará automáticamente la base de datos y se insertarán datos de ejemplo
- **Usuario Administrador**: Se crea automáticamente con las credenciales mencionadas anteriormente
- **Imágenes**: Las imágenes de productos se cargan desde URLs externas, necesitas conexión a internet
- **Migraciones**: La base de datos tiene un sistema de migraciones que actualiza automáticamente el esquema cuando cambias la versión

## 🎓 Para Estudiantes

Este proyecto es un excelente ejemplo de:
- Desarrollo Android moderno con Jetpack Compose
- Arquitectura MVVM
- Uso de Room Database
- Navegación entre pantallas
- Gestión de estado con ViewModel
- Inyección de dependencias
- Buenas prácticas de desarrollo Android

## 📞 Soporte

Si tienes preguntas o encuentras algún problema:
1. Revisa la sección de "Solución de Problemas Comunes" arriba
2. Verifica que tengas todas las dependencias instaladas
3. Asegúrate de estar usando una versión compatible de Android Studio

## 📄 Licencia

Este proyecto es educativo y está destinado para fines de aprendizaje.

---

**Desarrollado con ❤️ usando Kotlin y Jetpack Compose**

