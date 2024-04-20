# Aplicación de fichaje

## Índice

1. Arquitectura y tecnologías
2. Descripción y modelado del problema
3. Seguridad
4. Otros aspectos
5. Funcionamiento de la aplicación


## 4. Otros aspectos

### Máquina de estados

A la hora de tratar la lógica del flujo de los fichajes quedó patente que no era viable hacerlo mediante sentencias condicionales y se requería una **máquina de estados**. La implementación de la máquina de estados encapsuló y estructuró el código, pero no solucionó el problema más importante, que era de naturaleza humana: el código era largo y verboso. La máquina funcionaba correctamente, pero era yo como humano quien cometía errores y despistes a la hora de trasladar un diagrama de una máquina a código, ya que resultaba muy fácil despistarse y no trasladar (o hacerlo mal) un determinado estado o transición.

Se necesitaba una solución que eliminase la mayor cantidad de ruido, reflejando el diagrama en código de la forma más directa posible. Así se creo una pequeña *librería* o *utilidad* (considero que llamarla cualquiera de estas dos cosas es tomar demasiadas licencias) para crear máquinas de estados de manera genérica y sencilla.

#### Ejemplo

Supóngase la siguiente máquina de estados, la cual acepta cadenas sobre el alfabeto (A,B,C) tales que contienen la subcadena *ABC*. Nótese que no se trata de un DFA ya que no existen transiciones para todos los símbolos en todos los estados, quedando la máquina simplemente en dicho estado si no hay una transición definida para el símbolo entrante.

![](/doc/maquina-simple.jpg)

En primer lugar, la utilidad nos exige crear un enumerado que implemente State. Este enumerado representará los estados de la máquina y obliga a implementar un método que devuelva los estados finales:

```
    public enum Estado implements State {

        ESPERANDO_A,
        ESPERANDO_B,
        ESPERANDO_C,
        OK

        @Override
        public boolean isFinalState() {
            return this.equals(OK);
        }
    }
```

Hecho esto, se puede proceder a la creación de la máquina simplemente añadiendo transiciones como tuplas con forma (Q1, *s*, Q2), donde para un estado Q1, una entrada *s* produce una transición al estado *Q2*. El método `addTransitions()` no devuelve el builder que crea la máquina sino que esto lo hace el método `setInitialState()`, siendo así imposible crear una máquina sin un estado inicial. Honestamente, nunca he sido fan del patrón *Builder* y en este caso ni siquiera es necesario, pero he decidido utilizarlo a modo de aprendizaje y práctica con el lenguaje:

```
StateMachine stateMachine = new StateMachineByValueBuilder()
    .addTransitions(
        new ValueTransition(Estado.ESPERANDO_A, "A", Estado.ESPERANDO_B),
        new ValueTransition(Estado.ESPERANDO_B, "B", Estado.ESPERANDO_C),
        new ValueTransition(Estado.ESPERANDO_C, "C", Estado.ESPERANDO_OK),
        new ValueTransition(Estado.ESPERANDO_C, "A", Estado.ESPERANDO_A),
        new ValueTransition(Estado.ESPERANDO_C, "B", Estado.ESPERANDO_A)
    )
    .setInitialState(Estado.ESPERANDO_A)
    .build();
```

Ahora, tan solo hay que utilizar la máquina creada:
```
		List<String> entradas = List.of("C", "A", "B", "C", "A");
		for (String entrada : entradas) {
			boolean result = stateMachine.transitar(entrada);
		}
		
		boolean esFinal = stateMachine.getEstadoActual().isFinalState(); // true

```

#### Ejemplo real

Recordemos el diagrama real de la máquina de estados de la aplicación:

![maquina-real](/doc/maquina-real.jpg)

La diferencia fundamental en este caso es que las entradas son clases, es decir, en lugar de comparar valores necesitamos discernir de qué tipo de clase es la entrada. A nivel técnico esto no es un problema y puede salvarse de múltiples formas como por ejemplo introduciendo un enumerado *TipoFichaje* en cada clase y usándolo a la hora de crear las transiciones y transitar. Sin embargo, se ha creado una forma de contemplar este caso. Personalmente encuentro más coherente y segura la primera forma de utilización, pero se ha implementado esta segunda forma simplemente a modo de experimentación y práctica con el lenguaje.

Nótese el uso ahora de `StateMachineByClassBuilder` el cual obliga a utilizar una `ClassTransition` que a su vez ya no acepta un valor como parámetro sino un tipo de clase:

```
    StateMachine stateMachine = new StateMachineByClassBuilder()
            .addTransitions(
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_JORNADA, EntradaJornada.class, Estado.ESPERANDO_SALIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaDesayuno.class, Estado.ESPERANDO_ENTRADA_DESAYUNO),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaJornada.class, Estado.FIN_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_DESAYUNO, EntradaDesayuno.class, Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA, SalidaJornada.class, Estado.FIN_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA_O_SALIDA_COMIDA, SalidaComida.class, Estado.ESPERANDO_ENTRADA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA, SalidaComida.class, Estado.ESPERANDO_ENTRADA_COMIDA),
                    new ClassTransition(Estado.ESPERANDO_ENTRADA_COMIDA, EntradaComida.class, Estado.ESPERANDO_SALIDA_JORNADA),
                    new ClassTransition(Estado.ESPERANDO_SALIDA_JORNADA, SalidaJornada.class, Estado.FIN_JORNADA),

                    new ClassTransition(Estado.FIN_JORNADA, EntradaJornada.class, Estado.ERROR),
                    new ClassTransition(Estado.FIN_JORNADA, SalidaJornada.class, Estado.ERROR),
                    new ClassTransition(Estado.FIN_JORNADA, EntradaDesayuno.class, Estado.ERROR),
                    new ClassTransition(Estado.FIN_JORNADA, SalidaDesayuno.class, Estado.ERROR),
                    new ClassTransition(Estado.FIN_JORNADA, EntradaComida.class, Estado.ERROR),
                    new ClassTransition(Estado.FIN_JORNADA, SalidaComida.class, Estado.ERROR)
            )
            .setInitialState(Estado.ESPERANDO_ENTRADA_JORNADA)
            .build();
```

Creada la máquina, puede utilizarse con normalidad como en el primer ejemplo.