## Parcial de Tomas Maldonado


#### Para ejecutar:
No pude juntar todo en docker compose, tuve muchos inconvenientes con el gateway asi que no será incluido de esa forma.

El orden de ejecucion es Discovery -> Gateway y el resto no tienen prioridad

### Usuarios:
- ROLE_ADMIN:
    - Usuario: admin
    - Contraseña: admin
- ROLE_PROVIDER:
    - Usuario: provider
    - Contraseña: provider
- ROLE_CLIENT:
    - Usuario: client
    - Contraseña: client


##### Comentarios:
- El punto 6 con el Feign, pude realizar la configuracion correctamente, hubo transferencia de el JWT, pero, no de mostraban los grupos dentro del token. Asi que la autenticacion mediante grupods no pudo ser realizada en esa parte.
- Todos los puntos se hicieron siguiendo mas a profundidad los enunciado y clases de playground que comentarios realizados en clase, diferencias en consignas son debidos a inconsistencias entre ambas.