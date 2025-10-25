# Security Improvement Program — EvaluacionAndroid_Diego

Este documento presenta un conjunto de mejoras técnicas recomendadas para reforzar la seguridad de la aplicación Android `EvaluacionAndroid_Diego`, más allá de las buenas prácticas ya implementadas. Las propuestas están alineadas con estándares como OWASP MASVS y responden a riesgos detectados o potenciales en el análisis estático realizado con MobSF.

---

## 1. Firma segura del APK

- **Situación actual:** El APK fue firmado en modo `debug` durante el desarrollo, lo que generó una alerta de severidad alta en MobSF.
- **Mejora propuesta:** Generar el APK en modo `release`, firmado con una clave personalizada.
- **Justificación:** Evita que la app sea identificada como versión de desarrollo, lo que facilita ingeniería inversa.
- **Referencia:** MASVS-ARCH — "La aplicación no debe estar firmada con certificados de depuración."

---

## 2. Configuración de seguridad de red

- **Mejora propuesta:** Implementar `network_security_config.xml` para bloquear conexiones HTTP y certificados no confiables.
- **Justificación:** Previene ataques de intermediario (MITM) y asegura que las comunicaciones se realicen solo por HTTPS.
- **Referencia:** MASVS-NETWORK — "Las comunicaciones deben estar cifradas y autenticadas."

---

## 3. Cifrado de otros datos sensibles

- **Mejora propuesta:** Aplicar `EncryptedSharedPreferences` o Android Keystore para proteger tokens, credenciales o preferencias privadas.
- **Justificación:** Evita que datos sensibles queden expuestos en archivos internos o en memoria.
- **Referencia:** MASVS-STORAGE — "Los datos sensibles deben almacenarse de forma cifrada."

---

## 4. Validación de entradas del usuario

- **Mejora propuesta:** Implementar validaciones en formularios o campos de texto si se agregan en futuras versiones.
- **Justificación:** Previene inyecciones, errores de formato y comportamientos inesperados.
- **Referencia:** MASVS-CODE — "Las entradas del usuario deben ser validadas y sanitizadas."

---

## 5. Ciclo de análisis continuo

- **Mejora propuesta:** Repetir análisis con MobSF ante cada cambio significativo en el código.
- **Justificación:** Permite detectar nuevas vulnerabilidades y mantener un ciclo de mejora continua.
- **Referencia:** MASVS-TEST — "La aplicación debe ser evaluada regularmente mediante análisis estático y dinámico."

---

## 6. Educación y documentación

- **Mejora propuesta:** Documentar cada mejora aplicada y su impacto en la seguridad dentro del repositorio.
- **Justificación:** Facilita la revisión académica, la colaboración y el aprendizaje continuo.
- **Referencia:** MASVS-ARCH — "La arquitectura y decisiones de seguridad deben estar documentadas."

---

## Conclusión

Estas mejoras complementan las buenas prácticas ya implementadas y permiten proyectar el desarrollo de la aplicación hacia un entorno más robusto y profesional. Integrarlas no solo fortalece la seguridad técnica, sino también la calidad del proceso académico y el compromiso con el desarrollo responsable.
