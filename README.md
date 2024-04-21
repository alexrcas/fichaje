# Aplicación de fichaje

Aplicación web y móvil para gestionar el fichaje y el cómputo de horas de los empleados de la empresa.

## Índice

1. Arquitectura y tecnologías
2. Descripción y requisitos funcionales
3. Modelado del problema
4. Seguridad
5. Otros aspectos
6. Funcionamiento de la aplicación
7. Líneas futuras

## 1. Arquitectura y tecnologías

Este proyecto se ha desarrollado como una aplicación cliente-servidor, con una interfaz web servida desde el propio servidor a través de un motor de plantillas y una API que permite realizar integraciones de terceras aplicaciones.

El stack tecnológico original que se ha utilizado es el siguiente:

![](/doc/stack-java.jpg)

Sin embargo, otro objetivo de este proyecto es el aprendizaje, la experimentación y la comparativa con otras tecnologías. Por ello, se ha propuesto también un segundo stack tecnológico. Una vez finalizado el proyecto original, se rehará de nuevo desde cero utilizando este segundo stack y este repositorio será actualizado incluyendo ambas versiones del proyecto.

![](/doc/stack-node.jpg)

## 2. Descripción y requisitos funcionales

Se requiere la creación de una aplicación que permita gestionar y registrar los fichajes de los empleados. La aplicación no restringirá la acción del usuario, es decir, el empleado podrá fichar lo que quiera, cuando quiera y cuantas veces quiera. Será posteriormente un motor de validación el que evaluará la coherencia o no del fichaje de una jornada.

Por defecto el fichaje se produce a la hora en que se realiza la acción de fichar, pero también podrá realizarse un fichaje extemporáneo, es decir, un fichaje que no es en tiempo real sino que se refiere a un momento del pasado. Un ejemplo sería olvidar fichar la salida del viernes y ficharla el lunes siguiente. No obstante, pese a que el usuario tiene el control, la aplicación sí cargará por defecto el fichaje sugerido ya que la mayoría de las veces coincidirá con la intención del usuario. Por ejemplo, cabe esperar que el primer fichaje del día sea la entrada de jornada o que tras haber fichado salida a desayuno lo siguiente que se fiche sea la entrada de este.

La aplicación conservará el registro de todo sin efectuar nunca un borrado definitivo. Por ello, para eliminar fichajes erróneos existirá el concepto de *anulación*. Un fichaje anulado sigue existiendo como registro pero es ignorado para el cómputo. 

Será necesario que la aplicación cuente también con un calendario para poder marcar los días que proceda como festivos y que no sean tenidos en cuenta para el cómputo. De igual forma, debe existir el concepto de vacaciones, que a diferencia de los festivos se aplican sobre un empleado y no de forma general. La administración del calendario es, obviamente, tarea exclusiva del administrador.

Solo el administrador podrá anular fichajes, pero los usuarios podrán solicitar la anulación de un fichaje desde la propia aplicación. El administrador recibirá una notificación informando de esta solicitud y realizará la anulación si procede.

La aplicación mostrará de manera rápida y sencilla una cuadrícula semanal con el cómputo de horas de cada jornada. Cada empleado solo verá su fichaje pero el administrador podrá ver el de todos los empleados. Al pinchar sobre una cuadrícula se mostrará el detalle del cómputo y los fichajes realizados. Si el fichaje de una jornada es incorrecto se reflejará en la cuadrícula para identificarlo rápidamente y también en la vista de detalle. 

Posiblemente se cree una vista de incidencias que muestre un listado solo con las jornadas que tienen fichajes incorrectos. Esta vista puede tener valor sobre todo para el administrador, que no tendrá que navegar revisando la cuadrícula de cada empleado. No obstante, la idea es que la aplicación resalte a cada empleado rápidamente el estado de su fichaje, librando al administrador de esta tarea de supervisión. Es posible que se implante algún sistema de notificaciones push en la app móvil o de envío de correos electrónicos una vez a la semana recordando a los empleados los fichajes erróneos o el olvido de estos.

Hashes y blockchain, copias de seguridad

## 3. Modelado del problema

A continuación se muestra el modelo propuesto para respaldar la solución. Se trata de un modelo pequeño y simple que se vale del polimorfismo para representar los diferentes fichajes y que persigue eliminar la necesidad de contar con valores nulos en las entidades.

![](/doc/diagrama-robustez.jpg)

La validación de una jornada se realizará con una máquina de estados representada por el siguiente diagrama:

![](/doc/maquina-real.jpg)

Para aliviar la carga de la tarea de validación, la jornada contará con un flag que indicará si ha sido correctamente validada o no. Las jornadas validadas no volverán a ser tenidas en cuenta y no pasarán por el validador. Si se realiza un fichaje sobre una jornada validada, esta volverá a considerarse pendiente de validar.

Notificaciones server sent event?

## 4. Seguridad

## 5. Otros aspectos

### Máquina de estados

La implementación de la máquina de estados encapsuló y estructuró el código, pero no solucionó el problema más importante, que era de naturaleza humana: el código era largo y verboso. La máquina funcionaba correctamente, pero era yo como humano quien cometía errores y despistes a la hora de trasladar un diagrama de una máquina a código ya que resultaba muy fácil despistarse y no trasladar (o hacerlo mal) un determinado estado o transición.

Se necesitaba una solución que eliminase la mayor cantidad de ruido, reflejando el diagrama en código de la forma más directa posible. Así se creo una pequeña utilidad para crear máquinas de estados de manera genérica y sencilla.

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

## 6. Funcionamiento de la aplicación

## 7. Líneas futuras
vacaciones