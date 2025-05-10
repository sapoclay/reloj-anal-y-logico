# Reloj Anal y Lógico en Java

![Reloj_Anal_Y_Logico](https://github.com/user-attachments/assets/639d7443-296d-4e7d-933e-736b75f66032)

Este pequeño proyecto implementa un reloj que combina una visualización analógica y digital, desarrollado en Java utilizando Swing para la interfaz gráfica.

## Características

### Reloj Analógico
- Diseño moderno con efectos 3D y sombras
- Manecillas para horas (gris), minutos (gris) y segundos (rojo)
- Marcadores de minutos y números de hora
- Punto central con efecto tridimensional
- Actualización en tiempo real
- Efectos de antialiasing para una visualización suave

### Reloj Digital
- Muestra la hora en formato 24 horas (HH:mm:ss)
- Fuente Segoe UI moderna y clara
- Color azul distintivo
- Separador elegante entre la fecha y la hora

### Fecha
- Muestra la fecha completa en español
- Incluye día de la semana, día del mes, mes y año
- Formato: "Día de la semana, DD de Mes de YYYY"

## Detalles Técnicos

### Componentes Principales
- **Clase Principal**: `Reloj.java` (extiende JPanel)
- **Interfaz Gráfica**: Java Swing
- **Actualización**: Timer que refresca cada segundo
- **Resolución**: 400x500 píxeles

### Características de Diseño
- **Paleta de Colores**
  - Fondo: Gris claro moderno (RGB: 245, 245, 245)
  - Manecillas: Gris oscuro y rojo
  - Reloj Digital: Azul moderno
  - Separador: Gris claro
  - Fecha: Gris oscuro

- **Efectos Visuales**
  - Antialiasing en todos los elementos
  - Sombras en las manecillas
  - Efectos 3D en el punto central
  - Separador elegante entre elementos

## Requisitos
- Java Runtime Environment (JRE) 8 o superior
- Sistema operativo compatible con Java
- Resolución de pantalla mínima: 400x500 píxeles

