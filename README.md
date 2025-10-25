#  EvaluacionAndroid_Diego

# Descripción  
Este proyecto es una aplicación Android desarrollada como parte de la evaluación final del módulo de Programación Android en IP-CFT Sede Ovalle. La aplicación implementa medidas de seguridad para proteger contra vulnerabilidades comunes en entornos móviles, alineándose con los estándares de OWASP MASVS y validando su cumplimiento mediante análisis estático con MobSF.

---

##  Vulnerabilidades Identificadas

- Inyección SQL (potencial en entradas no validadas)
- Comunicación no segura (uso de HTTP sin cifrado)
- Exposición de datos sensibles (claves en texto plano, respaldo activado)
- Firma con certificado de depuración
- Permisos obsoletos o mal gestionados
- Exportación no controlada de componentes

---

##  Mejoras Implementadas

- Cifrado de datos sensibles con `EncryptedSharedPreferences` y `MasterKeys`
- Comunicación segura mediante HTTPS
- Validación y sanitización de entradas del usuario
- Firma del APK en modo `release` con clave personalizada
- Desactivación de `allowBackup` y control de `dataExtractionRules`
- Gestión de permisos por versión (`READ_MEDIA_*`, `maxSdkVersion`)
- Declaración explícita de `android:exported` en componentes internos
- Separación de responsabilidades en actividades independientes
- Uso de `viewBinding` para evitar errores de referencia a vistas

---

##  Documentación

- [`vulnerabilities.md`](vulnerabilities.md) — Listado detallado de hallazgos detectados por MobSF y su severidad.
- [`best_practices.md`](best_practices.md) — Buenas prácticas aplicadas por categoría (permisos, cifrado, arquitectura).
- [`security_tips.md`](security_tips.md) — Consejos prácticos aplicados directamente en el código.
- [`security_improvement_program.md`](security_improvement_program.md) — Propuestas futuras para reforzar la seguridad del proyecto.

---

##  Cómo Ejecutar la Aplicación de Forma Segura

1. Clonar el repositorio:
- Importar el proyecto en Android Studio.
- Ejecutar la aplicación en un dispositivo físico o emulador con Android 10+.
- Verificar que los permisos requeridos estén correctamente concedidos (ubicación, almacenamiento, cámara si aplica).
- Asegurarse de compilar en modo release para evitar el uso de certificados de depuración.

--- 

##  Reporte de Vulnerabilidades
El reporte detallado de las pruebas de seguridad está disponible en el archivo vulnerability_report.pdf. Este informe fue generado con MobSF y sirvió como base para identificar y corregir las vulnerabilidades presentes en la aplicación.

---

##  Estructura del Repositorio
EvaluacionAndroid_Diego/
├── app/                               # Código fuente Android
├── README.md                          # Este archivo
├── informe_buenas_practicas.docx      # Informe académico en Word
├── vulnerability_report.pdf           # Informe de MobSF
├── vulnerabilities.md                 # Detalle de vulnerabilidades detectadas
├── best_practices.md                  # Buenas prácticas por categoría
├── security_tips.md                   # Consejos aplicados en el proyecto
├── security_improvement_program.md    # Propuestas futuras de mejora
├── EvaluacionAndroid.apk              # APK firmado en modo release

---

##  Ver más a detalle
Para revisar en profundidad cada aspecto del proyecto, puedes consultar los siguientes archivos dentro del repositorio:
-  informe_buenas_practicas.docx: Documento académico con explicaciones técnicas, fragmentos de código y justificación de cada mejora aplicada.
-  vulnerability_report.pdf: Informe generado por MobSF con hallazgos de seguridad y severidad.
-  security_tips.md: Consejos prácticos aplicados directamente en el código.
-  best_practices.md: Resumen técnico por categoría (permisos, cifrado, arquitectura).
-  security_improvement_program.md: Propuestas futuras para reforzar la seguridad del proyecto.

---

##  Autor
Diego Rojo
Estudiante de Analista Programador
IP Santo Tomas Sede Ovalle
Año: 2025
