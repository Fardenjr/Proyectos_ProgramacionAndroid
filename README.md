# Proyectos_ProgramacionAndroid
# 📍 Evaluación Android - Proyecto con Google Maps

Este proyecto es una aplicación Android desarrollada en Android Studio que integra Google Maps y servicios de ubicación. Permite al usuario visualizar su ubicación actual en el mapa y agregar marcadores personalizados tocando la pantalla.

## 🚀 Funcionalidades principales

- Muestra el mapa de Google dentro de la app.
- Solicita permisos de ubicación en tiempo real.
- Detecta y muestra la ubicación actual del usuario.
- Permite agregar marcadores personalizados tocando el mapa.
- Incluye botón flotante para activar/desactivar el modo de marcación.

## 🛠️ Tecnologías utilizadas

- **Lenguaje:** Java
- **IDE:** Android Studio
- **SDK:** API 26 (mínimo) hasta API 35 (target)
- **Librerías:**
  - `com.google.android.gms:play-services-maps`
  - `com.google.android.gms:play-services-location`
  - Material Design Components

## 📂 Estructura del proyecto

- `MainActivity.java`: Lógica principal de la app, manejo del mapa y ubicación.
- `AndroidManifest.xml`: Configuración de permisos y clave de API.
- `activity_main.xml`: Diseño visual con mapa y botón flotante.
- `strings.xml`: Clave de API de Google Maps y textos de la interfaz.

## 🔐 Permisos requeridos

La app solicita los siguientes permisos:

- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`
- `INTERNET`
- `ACCESS_NETWORK_STATE`

## 🧠 Reflexión del desarrollo

Durante el desarrollo se aprendió a manejar permisos en tiempo de ejecución, integrar servicios de ubicación, y trabajar con eventos del mapa. El mayor desafío fue configurar correctamente la clave de API y asegurar que el mapa se cargara sin errores.

## 📸 Vista previa

> El mapa se muestra en pantalla con un marcador en la ubicación actual. Al activar el modo de marcación, el usuario puede tocar el mapa para agregar nuevos puntos.

## 📌 Autor

**Diego**  
Estudiante de Analista Programador  
IP-CFT Sede Ovalle

---

