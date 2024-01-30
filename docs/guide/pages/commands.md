## Obtener Medios de Cobro Disponibles

## Obtener Esquemas de Cuotas Disponibles
### Descripción del flujo
Este comando permite que los integradores tengan control de las cuotas en el momento de hacer transacciones con tarjeta de crédito, dando la posibilidad 
al cliente de escoger la cuota que desea financiar. Este comando esta habilitado solo para sellers de MLB.

### Diagrama de secuencia
En este diagrama mostraremos la forma de comunicarnos con smart para recuperar la lista de cuotas para el integrador.

```plantuml
[[!include assets/pumls/get_installment_amount_content_provider.puml]]
```

### Manejo de errores
En la siguiente tabla mostraremos el comportamiento en caso de errores en el momento de recuperar la lista de cuotas y al momento de lanzar
el flujo de pago con tarjeta de crédito y las cuotas.

| Caso                                                    | Comportamiento                                                                          |
|---------------------------------------------------------|-----------------------------------------------------------------------------------------|
| Smart no instalada                                      | Muestra mensaje de excepción que debe instalar la app                                   |
| Usuario no logueado                                     | Lanza el login QR de smart para que el usuario se autentique                            |
| No puede recuperar cuotas                               | Lanza el flujo de pago con el metodo que seleccionó                                     |
| Si el user es diferente a MLB lanza el flujo con cuotas | retorna una excepcion con un mensaje para informar que no esta habilitado para ese site | 

## Iniciar Flujo de Cobro

## Imprimir Imágenes

## Lanzar Pantalla de Bluetooth
### Descripción del flujo
Este comando permite facilitar el proceso de emparejamiento y conexión por medio de una UI que tiene todas las funcionalidades agrupadas.

### Diagrama de secuencia
La UI de Bluetooth se encuentra en la App de SmartPOS y se utiliza una estrategia de deeplink para iniciar el flujo desde el SDK de integraciones
```plantuml
[[!include assets/pumls/bluetooth_settings_ui_seq.puml]]
```

### Información adicional
- 

### Manejo de errores

| Caso                | Comportamiento |
|---------------------|----------------|
| Smart no instalada  | ???            |
| Usuario no logueado | ???            |

### Monitores
:warning: WIP :warning:



