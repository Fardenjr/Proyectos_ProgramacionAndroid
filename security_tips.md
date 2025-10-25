# Security Tips — EvaluacionAndroid_Diego

Este documento resume las medidas de seguridad aplicadas en el proyecto Android `EvaluacionAndroid_Diego`, junto con consejos prácticos para proteger datos sensibles, controlar permisos y reforzar la resiliencia de la aplicación frente a amenazas comunes.

---

## Configuración segura

- **Desactivar respaldo automático:**
  - Establecer `android:allowBackup="false"` en el `AndroidManifest.xml` para evitar que los datos de la app se respalden sin control.
  - Complementar con `android:dataExtractionRules` en Android 12+ para restringir exportaciones.

- **Controlar la depuración:**
  - Eliminar `android:debuggable` del manifiesto y gestionar el modo debug desde `build.gradle.kts`.
  - En modo `release`, establecer `isDebuggable = false` y activar `isMinifyEnabled = true` para obfuscación.

- **Declarar componentes internos como no exportados:**
  - Usar `android:exported="false"` en actividades que no deben ser accesibles desde otras apps.

---

## Gestión de permisos

- **Solicitar permisos en tiempo de ejecución:**
  - Verificar y solicitar permisos justo antes de usar funcionalidades sensibles (ubicación, cámara, medios).
  - Manejar correctamente el resultado con `onRequestPermissionsResult`.

- **Separar permisos por versión:**
  - Usar `READ_MEDIA_*` en Android 13+ y `READ_EXTERNAL_STORAGE` con `maxSdkVersion=29` para versiones anteriores.

- **Justificar permisos con hardware:**
  - Declarar `<uses-feature android:required="false" />` para evitar fallos en dispositivos sin cámara u otros sensores.

---

## Cifrado de datos sensibles

- **Evitar claves en texto plano:**
  - No almacenar claves API en `strings.xml` ni en el código fuente directamente.

- **Usar `EncryptedSharedPreferences`:**
  - Cifrar la clave de Google Maps con AES-256 usando `EncryptedSharedPreferences` y `MasterKeys`.
  - Recuperar la clave en tiempo de ejecución desde un contenedor seguro.

- **Evitar exposición en memoria y logs:**
  - No imprimir claves en `Logcat`.
  - Manejar errores de recuperación de claves con mensajes genéricos.

---

## Arquitectura segura

- **Uso de `viewBinding`:**
  - Reemplazar `findViewById` por `viewBinding` para evitar errores de referencia a vistas.

- **Separación de responsabilidades:**
  - Dividir funcionalidades en actividades independientes (mapas, audio, video, descarga).
  - Facilita el control de acceso y la aplicación de medidas específicas por componente.

---

## Compatibilidad y actualización

- **Actualizar SDK:**
  - Usar `compileSdk = 36` y `targetSdk = 36` para compatibilidad con Android 14 y librerías modernas.

- **Evitar permisos obsoletos:**
  - Adaptar el uso de permisos según la versión del sistema operativo para evitar bloqueos y advertencias.

---

## Recomendaciones adicionales

- Implementar `network_security_config.xml` para bloquear conexiones HTTP y certificados no confiables.
- Aplicar cifrado a otros datos sensibles como tokens, credenciales o preferencias privadas.
- Validar y sanitizar entradas del usuario si se incorporan formularios.
- Repetir análisis con MobSF ante cada cambio significativo en el código.

---

**Referencias:**

- [OWASP MASVS](https://owasp.org/www-project-mobile-security/)
- [Android Security Best Practices](https://developer.android.com/topic/security/best-practices)
- [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences)