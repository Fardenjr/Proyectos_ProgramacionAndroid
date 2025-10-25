# EvaluacionAndroid_Diego

Aplicación Android desarrollada como parte de una evaluación académica, que integra funcionalidades multimedia, mapas, descarga de imágenes y navegación entre actividades. El proyecto ha sido reforzado con buenas prácticas de seguridad, compatibilidad y mantenibilidad, alineadas con estándares internacionales como OWASP MASVS.

---

## Funcionalidades principales

- Visualización de mapas con ubicación actual y marcadores personalizados.
- Reproducción de audio local.
- Reproducción de video local.
- Descarga de imágenes desde la web.
- Navegación entre actividades mediante botones del menú principal.

---

## Buenas prácticas aplicadas

### Seguridad

- `android:allowBackup="false"` para evitar respaldo no controlado de datos sensibles.
- `android:dataExtractionRules` para compatibilidad con Android 12+.
- `android:debuggable` gestionado desde `build.gradle.kts`, evitando exposición en producción.
- Activación de ProGuard (`isMinifyEnabled = true`) en modo `release` para obfuscación del código.
- Cifrado de la clave de API de Google Maps usando `EncryptedSharedPreferences`.

### Gestión de permisos

- Declaración explícita de permisos peligrosos (`ACCESS_FINE_LOCATION`, `CAMERA`, `READ_MEDIA_*`) en `AndroidManifest.xml`.
- Justificación de hardware con `<uses-feature android:required="false" />`.
- Separación de permisos por versión (`READ_EXTERNAL_STORAGE` limitado a `maxSdkVersion=29`).
- Solicitud de permisos en tiempo de ejecución desde `MainActivity`, antes de abrir `MapaActivity`.

### Arquitectura y componentes

- Uso de `viewBinding` para acceso seguro a vistas.
- Separación clara de entornos `debug` y `release` con configuraciones específicas.
- Declaración de `exported="false"` en actividades internas para evitar exposición innecesaria.
- Recuperación segura de la clave de API en `MapaActivity`, evitando exposición directa en `strings.xml`.

### Compatibilidad

- `compileSdk` y `targetSdk` actualizados a 36 para compatibilidad con Android 14.
- Uso de permisos `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO` para acceso granular a medios en Android 13+.

---

## Estructura del proyecto
EvaluacionAndroid_Diego/ ├── app/ │   ├── src/ │   │   ├── main/ │   │   │   ├── java/com/example/evaluacionandroid_diego/ │   │   │   │   ├── MainActivity.java │   │   │   │   ├── MapaActivity.java │   │   │   │   ├── AudioActivity.java │   │   │   │   ├── VideoActivity.java │   │   │   │   └── DescargaActivity.java │   │   │   ├── res/ │   │   │   │   ├── layout/ │   │   │   │   ├── values/strings.xml │   │   │   │   └── xml/file_paths.xml │   │   │   └── AndroidManifest.xml │   ├── build.gradle.kts ├── best_practices.md ├── security_tips.md ├── security_improvement_program.md └── README.md


---

## Recomendaciones futuras

- Implementar `network_security_config.xml` para bloquear conexiones HTTP y certificados no confiables.
- Integrar autenticación segura si se agregan funcionalidades de login.
- Validar y sanitizar entradas si se incorporan formularios o campos de texto.
- Repetir análisis con MobSF ante cada cambio significativo en el código.
- Aplicar cifrado a otros datos sensibles si se almacenan tokens, credenciales o preferencias privadas.

---

Este documento resume las decisiones técnicas y de seguridad aplicadas en el proyecto. Para más detalle, revisa los archivos complementarios:
- `security_tips.pdf`
