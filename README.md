# Uala Mobile Challenge

## Overview

Este proyecto es una aplicación Android desarrollada en Kotlin, utilizando herramientas y bibliotecas modernas como **Jetpack Compose** para la interfaz de usuario, **Room** para la gestión de bases de datos locales, **Hilt** para la inyección de dependencias y **Retrofit** para las operaciones de red.

La aplicación sigue una arquitectura basada en capas:
- **Capa de interfaz de usuario (UI):** Se encarga de mostrar los datos y está diseñada siguiendo el patrón MVVM.
- **Capa de lógica de negocio (Domain).**
- **Capa de acceso a datos (Data).**

## Project Structure

El proyecto implementa la modularización siguiendo los patrones recomendados por Android. Se divide en cuatro módulos principales, diseñados para aprovechar las ventajas que esta estructura aporta:

1. **Organización (Separación de responsabilidades):** Cada módulo tiene una responsabilidad específica, facilitando la comprensión y navegación del código.
2. **Reutilización (Independencia):** Los módulos son independientes, lo que permite su reutilización en otros proyectos y reduce la duplicación de código.
3. **Escalabilidad (Desarrollo en paralelo):** Los desarrolladores pueden trabajar simultáneamente en diferentes módulos, acelerando el desarrollo e integración de nuevas características.
4. **Pruebas unitarias (Automatización):** Los módulos aislados son más fáciles de probar y automatizar, garantizando la calidad del código.
5. **Mantenibilidad (Aislamiento):** Los cambios en un módulo no afectan a otros, siempre que las interfaces públicas se mantengan constantes.
6. **Desacoplamiento:** Se minimizan las dependencias entre módulos, facilitando actualizaciones y mantenimiento.
7. **Optimización del tiempo de compilación:** La compilación incremental recompila solo los módulos afectados por los cambios, mejorando la eficiencia.

### Módulos

1. **app:** Contiene todo lo relacionado con la interfaz de usuario, incluyendo recursos locales, localización, vistas y navegación.
2. **domain:** Define la lógica de negocio y los casos de uso.
3. **data:** Gestiona el acceso a datos, como API y bases de datos locales.
4. **common:** Incluye utilidades y clases compartidas entre módulos.

## Dependencies

El proyecto utiliza las siguientes bibliotecas:

- **Jetpack Compose:** Para la interfaz de usuario moderna y declarativa.
- **Room:** Para el almacenamiento local mediante SQLite.
- **Hilt:** Para la inyección de dependencias.
- **Retrofit:** Para la comunicación con APIs REST.
- **Mockito:** Para pruebas unitarias.

## Build Configuration

- **compileSdk:** 35
- **minSdk:** 24
- **targetSdk:** 35
- **Java version:** 11

## Key Features

### Flujo de datos

El flujo de datos sigue estos pasos:

1. **Carga inicial:**
    - Al iniciar la aplicación, se muestra una pantalla de carga mientras se consulta la API.
    - Los datos obtenidos se guardan en una base de datos local.

2. **Gestión de datos locales:**
    - Los datos almacenados en la base de datos se pueden paginar y buscar eficientemente mediante consultas SQL.

3. **Interacción del usuario:**
    - Las ciudades se muestran en una lista.
    - Al seleccionar una ciudad, se abre **Google Maps** con su ubicación específica.
    - Los usuarios pueden marcar ciudades como favoritas; esta acción actualiza el estado en la base de datos local y en la interfaz de usuario.

### Casos de uso

Los principales casos de uso implementados son:

- **FetchCitiesUseCase:** Obtiene datos de la API.
- **SaveCitiesUseCase:** Guarda los datos obtenidos en la base de datos local.
- **LoadCitiesUseCase:** Carga datos desde la base de datos, con soporte para paginación y búsqueda.
- **GetCitiesUseCase:** Recupera ciudades desde la base de datos para mostrarlas en la interfaz de usuario.
- **UpdateFavoritesUseCase:** Actualiza el estado de favorito de las ciudades.

### Gestión de favoritos

La funcionalidad para marcar ciudades como favoritas se realiza en el `ViewModel`. Los cambios se reflejan en la base de datos local y en la lista mostrada al usuario.

### Navegación

La navegación principal es gestionada por `AppNavigation`, con un único punto de entrada en `MainActivity`. La app está diseñada para manejar estados de carga inicial, mostrar los datos obtenidos, y gestionar transiciones hacia nuevas pantallas como la vista de mapas.

