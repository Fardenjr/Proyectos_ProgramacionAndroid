# Informe de Vulnerabilidades

## Resumen

- Total de vulnerabilidades encontradas: 8  
- Críticas: 1  
- Altas: 3  
- Medias: 3  
- Bajas: 1  

---

## Detalles de los hallazgos

### 1. Uso de permisos peligrosos: `READ_EXTERNAL_STORAGE`

- **Descripción:** Permite leer el almacenamiento externo del dispositivo, lo que puede exponer archivos personales si no se gestiona correctamente.
- **Severidad:** Alta
- **Impacto:** Riesgo de exposición de datos sensibles.
- **Pasos para reproducir:** Revisar `AndroidManifest.xml`.
- **Remediación:** Validar y cifrar archivos antes de acceder. Evaluar si el permiso es necesario.

---

### 2. Permiso de ubicación precisa: `ACCESS_FINE_LOCATION`

- **Descripción:** Permite acceder a la ubicación GPS del usuario.
- **Severidad:** Crítica
- **Impacto:** Riesgo de rastreo y pérdida de privacidad.
- **Pasos para reproducir:** Verificar uso en el manifiesto y código.
- **Remediación:** Solicitar solo cuando sea necesario y con consentimiento explícito.

---

### 3. Permiso definido sin referencia: `DYNAMIC_RECEIVE_NOT_EXPORTED_PERMISSION`

- **Descripción:** Permiso personalizado sin documentación ni referencia clara.
- **Severidad:** Media
- **Impacto:** Exposición innecesaria de componentes internos.
- **Pasos para reproducir:** Revisar `AndroidManifest.xml`.
- **Remediación:** Documentar o eliminar si no es necesario. Restringir componentes.

---

### 4. `android:debuggable` habilitado

- **Descripción:** Permite depurar la app incluso en producción.
- **Severidad:** Alta
- **Impacto:** Acceso a clases internas y manipulación del comportamiento.
- **Pasos para reproducir:** Buscar `android:debuggable="true"` en el manifiesto.
- **Remediación:** Establecer `android:debuggable="false"` en producción.

---

### 5. `android:allowBackup` habilitado

- **Descripción:** Permite respaldar y restaurar datos de la app.
- **Severidad:** Media
- **Impacto:** Riesgo de fuga de datos si no se controla el respaldo.
- **Pasos para reproducir:** Buscar `android:allowBackup="true"` en el manifiesto.
- **Remediación:** Desactivar si no es necesario o cifrar datos sensibles.

---

### 6. Actividades no protegidas

- **Descripción:** Actividades como `AudioActivity`, `DecarcaActivity` y `MapActivity` están expuestas sin restricciones.
- **Severidad:** Baja
- **Impacto:** Acceso no autorizado desde otras apps.
- **Pasos para reproducir:** Verificar `exported="true"` sin permisos asociados.
- **Remediación:** Establecer `exported="false"` o proteger con permisos definidos.

---

### 7. Aplicación firmada con certificado de depuración

- **Descripción:** La app está firmada con un certificado de depuración (`debug`), lo que facilita su modificación.
- **Severidad:** Alta
- **Impacto:** Riesgo de ingeniería inversa y manipulación del código.
- **Pasos para reproducir:** Revisar sección "Certificate Analysis" en MobSF Live.
- **Remediación:** Firmar con certificado de producción antes de distribuir.

---

### 8. Uso de permisos comúnmente abusados por malware

- **Descripción:** La app solicita permisos que son frecuentemente abusados por malware:
  - `ACCESS_FINE_LOCATION`
  - `ACCESS_COARSE_LOCATION`
  - `INTERNET`
  - `ACCESS_NETWORK_STATE`
  - `READ_EXTERNAL_STORAGE`
- **Severidad:** Media
- **Impacto:** Aumenta el riesgo de malinterpretación por sistemas de seguridad.
- **Pasos para reproducir:** Revisar sección "Abused Permissions" en MobSF Live.
- **Remediación:** Justificar cada permiso, limitar su uso y aplicar controles de acceso.
