# Lista enlazada simple: inserción al final (`insertEnd`)

## 1. Propósito del ejercicio

El objetivo es extender una lista enlazada simple (simplemente enlazada) que ya permite insertar elementos **al principio** para que también permita insertar elementos **al final** mediante un método `insertEnd(E info)`.

La exigencia clave es razonar correctamente sobre **dos escenarios**:

- Lista vacía: el nuevo nodo se convierte en el primer nodo.
- Lista no vacía: hay que recorrer la estructura hasta localizar el último nodo y enlazar ahí el nuevo nodo.

---

## 2. Modelo mental de la estructura

Una lista enlazada simple se entiende mejor como dos ideas simultáneas:

- Cada elemento vive en un **nodo**.
- Cada nodo conoce a su **sucesor** (o no conoce a nadie si es el último).

En el código, el nodo guarda:

- `info`: el dato de tipo genérico `E`.
- `next`: referencia al siguiente nodo (o `null` si no hay más).

La lista guarda un único punto de entrada:

- `head`: referencia al primer nodo.

Si `head` es `null`, la lista está vacía.

---

## 3. Invariantes (reglas que deben mantenerse siempre)

Al programar estructuras dinámicas conviene fijar invariantes; son el “contrato interno” que te permite confiar en el estado del programa.

En esta implementación se mantienen, de forma deliberada, estas reglas:

- **Vacía** si y solo si `head == null`.
- Si la lista **no** está vacía, `head` apunta a un nodo válido.
- El **último nodo** es aquel cuyo `next == null`.
- `size` cuenta nodos: se incrementa exactamente una vez por inserción exitosa.

Estas reglas hacen que el método `toString()` (impresión) sea casi trivial: empieza en `head` y avanza siguiendo `next` hasta `null`.

---

## 4. Inserción al final: diseño de `insertEnd(E info)`

Insertar al final no es conceptualmente difícil; lo importante es ser metódico en la gestión de referencias.

### 4.1. Paso 1: crear el nodo

Siempre empezamos construyendo el nodo con el `info` recibido. Esto separa claramente:

- La creación del objeto nodo.
- La decisión de dónde enlazarlo.

### 4.2. Caso A: lista vacía

Si `head == null`, no hay “último nodo” que buscar. El único movimiento correcto es:

- `head = node`.

Con esto, el nodo nuevo es simultáneamente primero y último.

### 4.3. Caso B: lista no vacía

Si la lista tiene elementos, hay que encontrar el último nodo. En una lista simple, el último nodo no se conoce directamente (salvo que exista también un puntero `tail`, que aquí **no** se usa).

La búsqueda sigue el criterio lógico del último nodo:

- Mientras `current.next != null`, todavía no estás en el último.

Cuando el bucle termina, se cumple:

- `current.next == null`  →  `current` es el último.

Entonces, el enlace final es:

- `current.next = node`.

Y por último, se actualiza el tamaño (`size++`).

---

## 5. Coste computacional

La inserción al final en esta versión tiene complejidad:

- **Tiempo**: `O(n)` en el caso general (hay que recorrer hasta el final).
- **Espacio extra**: `O(1)` (solo se usan referencias auxiliares).

Si la lista mantuviera un puntero `tail`, `insertEnd` podría ser `O(1)`. Pero este ejercicio, precisamente, entrena el recorrido y el criterio de parada.
