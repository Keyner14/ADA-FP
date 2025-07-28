# 📚 Gestor de Tareas con JavaFX (Heap + AVL Tree)

Este proyecto es una aplicación de escritorio desarrollada en **Java** usando **JavaFX** que implementa un sistema de gestión de tareas con estructura **MVC (Modelo-Vista-Controlador)**. Utiliza una **estructura de datos tipo Heap** para manejar prioridades y un **árbol AVL** para búsquedas eficientes por ID.

## 🚀 Características principales

- Inserción, búsqueda y eliminación de tareas.
- Manejo de prioridades mediante un **Max-Heap**.
- Búsqueda eficiente **O(log n)** con un **Árbol AVL**.
- Interfaz gráfica interactiva creada con **JavaFX**.

## 📁 Estructura del Proyecto

- `HelloApplication.java`: Clase principal que lanza la aplicación.
- `heapController.java`: Controlador vinculado a la vista que gestiona el heap.
- `HelloController.java`: Controlador vinculado a la vista principal con heap y AVL.
- `task.java`: Modelo que representa una tarea.
- `heap.java`: Estructura de datos Max-Heap.
- `AVLTree.java`: Implementación del árbol AVL para búsquedas por ID.

## 🛠️ Requisitos

- Java 17 o superior
- JavaFX (compatible con la versión de Java instalada)
- IDE recomendado: IntelliJ IDEA
- JDK configurado correctamente
- JavaFX configurado como librería externa

## ⚙️ Cómo ejecutar el proyecto

### 1. Descargar o clonar el repositorio

```bash
git clone https://github.com/Keyner14/ADA-FP.git
cd ADA-FP
```

### 2. Abir el proyecto en IntelliJ IDEA

- Seleccionar Open y elegir carpeta del proyecto
- Asegurarse de que el JDK está configurado (File > Project Structure > Project SDK)

### 3. Ejecutar la aplicación

- Buscar la clase **`HelloApplication.java`** 
- Click derecho → **Run 'HelloApplication.main()'.**
- La interfaz gráfica se abrirá.

## 🧪 Cómo correr los tests

Este proyecto incluye una serie de pruebas utilizando **JUnit 5** para validar el funcionamiento del Heap y el Árbol AVL.

### ▶️ Ejecutar pruebas

Se pueden correr las pruebas de dos formas:

1. **Desde IntelliJ IDEA**
    - Navega a `src/test/java/com/example/ada1fp/tests/TaskManagerTest.java`
    - Haz clic derecho en la clase o método → `Run 'TaskManagerTest'`

Las pruebas verifican:
- Inserción y orden de prioridad en el Heap
- Eliminación y mantenimiento de la estructura
- Búsqueda eficiente en el AVL
- Equilibrio automático del AVL tras inserciones
