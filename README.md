# CASHOUT
## Requisitos
- Tener instalado docker o podman con un contenedor de postgresql 16
- Tener instalado mockoon (El archivo mockoon-payments.json se encuentra en la raíz del proyecto)
- Para probar la API se adjunta una colección de Bruno (El archivo cashout-bruno.json se encuentra en la raíz del proyecto)
- Este proyecto está desarrollado en java 17

## Instrucciones
1. Se debe tener creada una base de datos en postgresql con el nombre cashout.
2. El schema de la base de datos se llama system.
3. Este proyecto posee migraciones de flyway por lo tanto no es necesario poblar la base de datos.


