# Variables de entorno
```
DB_USERNAME = username
DB_PASSWORD = Password
DB_URL = jdbc:postgresql://localhost:5432/bellido_map
JWT_SECRET = claveSecretaSuperSeguraParaJwtBellidoMap2026Secret
JWT_EXPIRATION = 86400000
```

En mi caso estoy usando postgres , cambia según gustes la base de datos.

`JWT_SECRET` y `JWT_EXPIRATION` son opcionales (tienen valores por defecto). El secret debe tener al menos 32 caracteres.
