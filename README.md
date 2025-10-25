#  EvaluacionAndroid_Diego

Proyecto académico desarrollado como parte de la evaluación final del módulo de Programación Android en IP-CFT Sede Ovalle. La aplicación integra buenas prácticas de seguridad, compatibilidad y arquitectura, alineadas con estándares internacionales como OWASP MASVS y validadas mediante análisis estático con MobSF.

---

##  Objetivo del proyecto

Desarrollar una aplicación Android funcional y segura, aplicando medidas concretas para proteger datos sensibles, controlar permisos, estructurar el código de forma modular y garantizar compatibilidad con versiones modernas del sistema operativo.

---

##  Buenas prácticas aplicadas

- **Cifrado de claves sensibles** con `EncryptedSharedPreferences` y `MasterKeys`.
- **Gestión segura de permisos** usando `READ_MEDIA_*` en Android 13+ y control condicional para versiones anteriores.
- **Desactivación de respaldo automático** (`allowBackup="false"`) y control de extracción de datos.
- **Firma segura del APK** en modo `release`, evitando certificados de depuración.
- **Separación de responsabilidades** en actividades independientes (mapas, audio, video, descarga).
- **Uso de `viewBinding`** para evitar errores de referencia a vistas.
- **Compatibilidad con Android 13+** mediante `compileSdk = 36` y `targetSdk = 36`.

---

##  Vulnerabilidades detectadas y corregidas

Durante el análisis estático realizado con **MobSF**, se identificaron las siguientes vulnerabilidades relevantes, las cuales fueron corregidas en el código y documentadas en el informe técnico:

| Vulnerabilidad | Severidad | Descripción | Corrección aplicada | Referencia MASVS |
|----------------|-----------|-------------|----------------------|-------------------|
| Aplicación firmada con certificado de depuración | Alta | El APK fue firmado con `AndroidDebugKey`, lo que indica que es una versión de desarrollo. | Se generó el APK en modo `release`, firmado con clave personalizada. | MASVS-ARCH |
| Permisos obsoletos (`READ_EXTERNAL_STORAGE`) | Media | Uso de permisos genéricos no compatibles con Android 13+. | Se reemplazó por `READ_MEDIA_*` y se aplicó `maxSdkVersion=29` para compatibilidad. | MASVS-P2 |
| Exportación no controlada de componentes | Media | Actividades sin `android:exported` definido, lo que puede permitir acceso externo. | Se agregó `android:exported="false"` en componentes internos. | MASVS-ARCH |
| Clave API en texto plano | Media | La clave de Google Maps estaba expuesta en `strings.xml`. | Se cifró usando `EncryptedSharedPreferences` y `MasterKeys`. | MASVS-STORAGE |
| Respaldo automático activado | Baja | `allowBackup="true"` permite que los datos de la app se respalden sin control. | Se desactivó con `allowBackup="false"` y se agregó `dataExtractionRules`. | MASVS-ENV |

> El informe completo de MobSF se encuentra en `vulnerability_report.pdf`.

---

##  Análisis de seguridad

Se realizó un análisis estático con **MobSF**, detectando y corrigiendo:

- Uso de certificado de depuración.
- Permisos obsoletos.
- Configuraciones inseguras en el manifiesto.
- Exposición de claves sensibles.

Cada hallazgo fue documentado y corregido conforme a OWASP MASVS. El informe técnico se encuentra en el archivo `informe_buenas_practicas.docx`.

---

##  Ver más a detalle

Para revisar en profundidad cada aspecto del proyecto, puedes consultar los siguientes archivos dentro del repositorio:

-  **`informe_buenas_practicas.docx`**: Documento académico con explicaciones técnicas, fragmentos de código y justificación de cada mejora aplicada.
-  **`vulnerability_report.pdf`**: Informe generado por MobSF con hallazgos de seguridad y severidad.
-  **`security_tips.md`**: Consejos prácticos aplicados directamente en el código.
-  **`best_practices.md`**: Resumen técnico por categoría (permisos, cifrado, arquitectura).
-  **`security_improvement_program.md`**: Propuestas futuras para reforzar la seguridad del proyecto.

---

## Recomendaciones futuras

- Implementar `network_security_config.xml` para bloquear conexiones HTTP.
- Aplicar cifrado a otros datos sensibles como tokens y preferencias.
- Validar entradas del usuario si se agregan formularios.
- Repetir análisis con MobSF ante cada cambio significativo.
- Documentar cada mejora aplicada dentro del repositorio.

---

## 👨‍🎓 Autor

**Diego Rojo**  
Estudiante de Analista Programador  
IP-CFT Sede Ovalle  
Año: 2025

---

## 📚 Referencias

- [OWASP MASVS](https://owasp.org/www-project-mobile-security/)
- [Android Security Best Practices](https://developer.android.com/topic/security/best-practices)
- [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences)
- [Network Security Config](https://developer.android.com/training/articles/security-config)
