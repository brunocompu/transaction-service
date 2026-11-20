# Decisiones técnicas

## 1. Validaciones de entrada

No agregué reglas de validación para campos como `amount` y `type`. El
enunciado no especifica restricciones concretas, por ejemplo, si se
permiten importes negativos o tipos vacíos.

## 2. Jerarquía de transacciones y detección de ciclos

El cálculo de la suma se realiza de forma recursiva y asume que las
transacciones forman una jerarquía válida, sin ciclos. No implementé una
detección de ciclos porque el enunciado no contempla ese caso.

## 3. Identificadores inexistentes

No agregué un tratamiento específico para cuando se solicita la suma de
una transacción que no existe, ya que el enunciado no define qué
respuesta debería devolver la API en ese caso. Actualmente, si se 
solicita una transacción inexistente, se produce una excepción que 
devuelve un `error 500`. Podría mejorarse manejando ese caso 
específicamente y devolviendo un `404 Not Found`.

## 4. Identificadores duplicados

El enunciado tampoco define qué debería ocurrir si se guarda una
transacción con un identificador que ya existe. Por ese motivo, no
establecí ese comportamiento como un requisito ni agregué una prueba
para una alternativa en particular. Actualmente, `HashMap.put()`
reemplaza el valor asociado a ese identificador.

## 5. Concurrencia

No incorporé mecanismos adicionales para coordinar accesos concurrentes. 
Prioricé una implementación sencilla, dado que no se especifican requisitos
de concurrencia, pero sería un aspecto a revisar para evitar `race conditions`. 

Por ejemplo, operaciones de consulta como el cálculo de sumas o la búsqueda 
por tipo podrían obtener resultados inconsistentes si se ejecutan mientras 
otras solicitudes modifican las transacciones. Esto podría abordarse mediante
mecanismos de sincronización u otras estrategias que garanticen la 
consistencia de las operaciones.

## 6. Complejidad del cálculo de sumas

Para encontrar los hijos de una transacción, el repositorio recorre
todas las transacciones almacenadas. Esta búsqueda se repite para cada
transacción de la rama que se está sumando.

Se podría mejorar el rendimiento manteniendo un índice por `parent_id`,
pero eso también agregaría complejidad al repositorio. Para este
ejercicio decidí mantener la implementación más directa y dejar esa
optimización como una posible mejora.

## 7. Commits y desarrollo iterativo con TDD

Durante el desarrollo intenté avanzar de manera incremental y utilizar
los commits para dejar registro del proceso. La idea fue trabajar
test por test: primero plantear un caso de test que falle, después 
implementar lo mínimo necesario para que pase y por último refactorizar
cuando fuera conveniente antes de continuar con el siguiente caso.

## 8. Claridad del código

Busqué que el código fuera fácil de entender por sí mismo, utilizando 
nombres descriptivos para clases, métodos y tests, evitando usar 
comentarios.

## 9. Tests de integración

Los tests de integración se encuentran en `TransactionControllerTest`. 
Utilizan `@SpringBootTest` y `MockMvc` para probar los endpoints HTTP 
con los componentes reales de la aplicación, verificando su interacción
entre las distintas capas.
