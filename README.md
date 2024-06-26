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

El stack tecnológico que se ha utilizado es el siguiente:

![](/doc/stack-java.jpg)

Sin embargo, otro objetivo del proyecto es el aprendizaje, la experimentación y la comparativa con otras tecnologías. Por ello, se ha propuesto también un segundo stack tecnológico. Una vez finalizado el proyecto original, se rehará de nuevo desde cero utilizando este segundo stack y este repositorio será actualizado incluyendo ambas versiones del proyecto.

![](/doc/stack-node.jpg)

## 2. Descripción y requisitos funcionales

Se requiere una aplicación que permita registrar y gestionar el fichaje de los empleados. La aplicación no restringirá la acción del usuario, es decir, el empleado podrá fichar lo que quiera, cuando quiera y cuantas veces quiera. Será posteriormente un motor de validación el que evaluará la coherencia o no del fichaje de una jornada. No obstante, la aplicación precargará por defecto el fichaje sugerido ya que la mayoría de las veces coincidirá con la intención del usuario. Por ejemplo, cabe esperar que el primer fichaje del día sea la entrada de jornada o que tras haber fichado salida a desayuno lo siguiente que se fiche sea la entrada de este.

Por defecto, el fichaje constará a la hora en que se realiza la acción de fichar, pero también podrá realizarse un fichaje extemporáneo, es decir, un fichaje que se refiere a un momento del pasado. Un ejemplo sería olvidar fichar la salida del viernes y realizar su fichaje el lunes siguiente. 

La aplicación conservará el registro de todo sin efectuar nunca un borrado definitivo. Por ello, para eliminar fichajes erróneos existirá el concepto de *anulación*. Un fichaje anulado sigue existiendo como registro pero es ignorado para el cómputo. Solo el administrador podrá anular fichajes, pero los usuarios podrán solicitar la anulación de un fichaje desde la propia aplicación. El administrador recibirá una notificación informando de esta solicitud y realizará la anulación si procede.

Se contará también con un calendario para marcar los días que proceda como festivos y que no sean tenidos en cuenta para el cómputo. También existirá el concepto de **vacaciones**, que a diferencia de los festivos se aplican sobre un solo empleado. De igual forma, existirá la **ausencia justificada**, que aplicada sobre un empleado y una jornada descuentan un cierto número de horas cuando se efectúa el cómputo de la semana. Si bien la jornada reducida de verano puede solventarse con esta funcionalidad, es posible que se le dote de su propio concepto. La administración del calendario es, obviamente, tarea exclusiva del administrador.

La aplicación mostrará de manera rápida y sencilla una cuadrícula semanal con el cómputo de horas de cada jornada. Cada empleado solo verá su fichaje pero el administrador podrá ver el de todos los empleados. Al pinchar sobre una cuadrícula se mostrará el detalle del cómputo y los fichajes realizados. Si el fichaje de una jornada es incorrecto se reflejará en la cuadrícula para identificarlo rápidamente y también en la vista de detalle. 

Posiblemente se cree una vista de incidencias que muestre un listado solo con las jornadas que tienen fichajes incorrectos. Esta vista podría tener valor sobre todo para el administrador, evitándole tener que revisar la cuadrícula de cada empleado. No obstante, el objetivo de la aplicación es que el fichaje no necesite supervisión, mostrando de forma muy concisa y directa el estado del fichaje a cada empleado. Es posible que se implante algún sistema de notificaciones push en la app móvil o de envío de correos electrónicos una vez a la semana recordando a los empleados los fichajes erróneos o el olvido de estos.

### 2.1. Aspectos legales

"La Inspección de Trabajo insiste en que el registro *debe hacerse mediante un sistema de registro objetivo, que garantice la fiabilidad, veracidad y la **no alteración a posteriori de los datos**. Los datos del registro de jornada deben estar a disposición de la Inspección de Trabajo y Seguridad Social, pero también de los trabajadores y sus representantes legales.*"

Para cumplir con lo anterior, se propone el diseño descrito a continuación:

1. Se creará en base de datos una tabla de informe. Esta tabla no tiene un diseño habitual o correcto desde el punto de vista del software pero su objetivo no es ser utilizada por la aplicación sino ser legible para el humano. La tabla resume la jornada de un empleado en un solo registro, y hace esto para cada día y cada empleado. Un campo *ID Informe* agrupa todos los registros. En este ejemplo se propone agrupar mensualmente.

2. A principios de cada mes, una tarea automatizada genera el informe del mes anterior. En la imagen de ejemplo, a principios de junio se generaría el informe con los fichajes de mayo, ampliando así la tabla con la información en verde.

3. Una vez generada esta información, se obtienen todos los registros bajo el mismo *ID Informe* y todos sus campos, generando un hash como resultado. Este hash es a continuación firmado con sello digital en el servidor. El hash garantiza la no alteración de los datos y la firma del mismo da fe de su no alteración desde que este fue calculado.


![](/doc/img/firma-fichaje.png)

## 3. Modelado del problema

A continuación se muestra el modelo propuesto para respaldar la solución. Es un diseño pequeño y simple que se vale del polimorfismo para representar los diferentes fichajes y que persigue la ausencia de valores nulos en las entidades. La entidad *Vacaciones* si bien no es estrictamente necesaria, permite agrupar los días libres identificando un periodo vacacional. Esto podría ser interesante de cara a posibles evoluciones futuras.

![](/doc/diagrama-robustez.jpg)

La validación de una jornada se realizará con una máquina de estados representada por el siguiente diagrama:

![](/doc/maquina-real.jpg)

Para aliviar la carga de la tarea de validación, la jornada contará con un flag que indicará si ha sido correctamente validada o no. Las jornadas validadas no volverán a ser tenidas en cuenta y no pasarán por el validador. Si se realiza un fichaje sobre una jornada validada, esta volverá a considerarse pendiente de validar. Dado que las jornadas cuentan con su propia representación y se obtienen directamente sin iterar sobre los días, a efectos prácticos la validación se producirá prácticamente solo en el día en curso para cada empleado o los pocos con un fichaje erróneo.


## 4. Seguridad

### 4.1. Seguridad a nivel de usuario y contraseña (oAuth)
La aplicación está protegida con acceso mediante usuario y contraseña. Para mayor comodidad la autenticación será relegada a la cuenta de Google, por lo que los usuarios simplemente accederán con dicha cuenta. Únicamente los usuarios autenticados pueden acceder o consumir los servicios de la aplicación. Esto conforma la primera barrera de seguridad.

### 4.2. Seguridad a nivel de rol y securización de rutas
La aplicación contempla dos roles: usuario y administrador. Las rutas están protegidas y filtradas teniendo en cuenta estos roles, por lo que no se trata únicamente de no mostrar al usuario aquello que no puede hacer: aunque conociese la ruta e intentase hacerlo obtendría un error de autorización.

### 4.3. Seguridad a nivel de entidad (entidad securizada)
Los usuarios, obviamente, deben tener permiso para consultar las jornadas, pero esto significa que si un usuario trampea el código del cliente cambiando el id de la jornada a consultar podría obtener información de la jornada de otro empleado. Por ello, se contempla una capa de seguridad a nivel de entidad o repositorio. Al obtener una jornada en base de datos, se comprueba que el usuario que la consulta sea el usuario de dicha jornada, devolviendo un error en caso contrario. Esto no se aplica si se posee el rol de administrador, que podrá consultar la jornada de cualquier usuario.

### 4.4. Comprobación de comando en el back-end (y en el front pero solo como azúcar de usabilidad)
Todas las comprobaciones de formularios o cualquier tipo de acción se realizan siempre en el back-end, aunque es posible que para mejorar la usabilidad existan algunas comprobaciones en el front-end que en la mayoría de los casos serán suficientes. No obstante, estas son solo un añadido para mejorar la usabilidad, existiendo siempre la validación en el back-end.


## 5. Otros aspectos

### Máquina de estados

La creación de una máquina de estados era verbosa. Se necesitaba una solución que eliminase la mayor cantidad de ruido, reflejando el diagrama en código de la forma más directa posible. Así se creo una pequeña utilidad para crear máquinas de estados de manera genérica y sencilla.

#### Ejemplo

Supóngase la siguiente máquina de estados:

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

Hecho esto, se puede proceder a la creación de la máquina añadiendo N transiciones como tuplas con forma (Q1, *s*, Q2), donde para un estado Q1, una entrada *s* produce una transición al estado *Q2*. El método `addTransitions()` no devuelve el builder que crea la máquina sino que esto lo hace el método `setInitialState()`, siendo así imposible crear una máquina sin un estado inicial. Honestamente, nunca he sido fan del patrón *Builder* y en este caso ni siquiera es necesario, pero he decidido utilizarlo a modo de aprendizaje y práctica con el lenguaje:

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

La diferencia fundamental en este caso es que lo que necesitamos discernir al recibir una entrada no es un valor sino un tipo de clase. A nivel técnico esto no es un problema y puede salvarse de múltiples formas, pero se ha creado una forma de contemplar este caso. Personalmente encuentro más coherente y segura la primera forma de utilización, pero se ha implementado esta segunda forma simplemente a modo de experimentación y práctica con el lenguaje.

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

*Nota*: las siguientes capturas se han tomado durante un desarrollo en constante evolución y generando diferentes escenarios de prueba. La consistencia entre las mismas así como el realismo de los datos mostrados puede ser inexacto. El aspecto final de la aplicación real podría llegar a diferir ligeramente de las mismas.

La pantalla de fichajes es el punto de entrada de la aplicación. Muestra los fichajes y la posibilidad de fichar de una manera concisa y directa. Señala el día y la semana en curso. Cuando carga, la tabla hace scroll automáticamente hasta la semana en curso.

Las dos últimas columnas de la tabla muestran el total de horas trabajadas y el objetivo de horas semanal. La primera columna aparecerá en rojo o verde en función de si se cumple con las horas objetivo de la jornada, tanto con un límite superior como inferior.

Al visualizar su tabla, el empleado podrá encontrar algunas etiquetas sobre determinados días:
* **VAC**: indica vacaciones.
* **FEST**: indica día festivo.
* **JUST**: indica que se han justificado horas de ausencia ese día. Junto con el día festivo o las vacaciones, afecta al objetivo de horas semanales.
* **ERR**: indica un error en el fichaje. Esta etiqueta no es exclusiva con la anterior, pudiendo aparecer ambas a la vez.

En el panel derecho se encuentra el formulario de fichaje. Este formulario se precarga con la opción sugerida, es decir, si no hay fichaje para el día en curso mostrará preseleccionado *Entrada Jornada*. Si se ficha, mostrará *Salida desayuno*, a continuación *Entrada desayuno* y así sucesivamente hasta completar el flujo de un día normal, por lo que la mayoría de veces bastará con entrar a la aplicación y pulsar el botón para fichar.

![](/doc/img/fichajes.png)

La imagen anterior ha sido obtenida con la cuenta de administrador de la aplicación y es ligeramente diferente a lo que verá el resto de usuarios. Los usuarios normales no verán la columna izquierda, con diversas herramientas de administración y tampoco verán el selector situado sobre la tabla, que permite visualizar el fichaje de cualquier empleado. En este caso, se selecciona un usuario de pruebas que no tiene fichajes.

![](/doc/img/fichaje-ajeno.png)

En cualquier caso, haciendo click sobre un fichaje se abrirá la vista de detalle. La vista de detalle muestra el flujo de fichajes y una tabla que refleja el detalle del cómputo. Haciendo click sobre un fichaje un empleado puede solicitar su anulación. Los fichajes anulados seguirán en su lugar original pero se mostrarán tachados o en gris.

![](/doc/img/detalle-fichaje.png)

![](/doc/img/detalle-ausencia.png)

Si se hace click sobre un fichaje erróneo la aplicación mostrará el fichaje que ha hecho fallar al motor de validación. En el caso de este ejemplo, una entrada de comida no es posible si no existe una salida de comida previa.

![](/doc/img/error-fichaje.png)

La aplicación dispone de modo claro y oscuro intercambiables en cualquier momento

![](/doc/img/switch-theme.gif)

Las vistas de vacaciones, días festivos y ausencias justificadas son muy similares e intuitivas y siempre funcionan de la misma manera, por lo que no se mostarán todas. Listan entradas existentes y al mismo tiempo permiten la creación de nuevas. Nótese que en el caso de las vacaciones la aplicación tiene en cuenta fines de semana y festivos y únicamente bastará con señalar la fecha de inicio y la de regreso, contando esta los días laborables en los que se gastan las vacaciones. Esto podría ser útil de cara a una posible línea futura (ver sección *7. Líneas futuras*)

![](/doc/img/vacaciones.png)

![](/doc/img/ausencia-justificada.png)

Dado que la aplicación es muy pequeña en cuanto a casos de uso y los formularios apenas tienen uno o dos campos, por ahora no se utilizan modales sino que dichos formularios están embebidos en las propias vistas. Los errores devueltos por el servidor siempre se muestran de la misma forma: en la parte superior del contenedor.

![](/doc/img/vacaciones-error.png)


## 7. Líneas futuras

Dado que la aplicación tiene consciencia de las vacaciones de los empleados y puede llevar la cuenta de las mismas, debería ser sencillo crear una pequeña funcionalidad para generar la hoja de solicitud de vacaciones. Actualmente, para solicitar las vacaciones el empleado debe editar una plantilla en la que especifica de cuántos días de vacaciones dispone, cuántos desea utilizar y la fecha de inicio y fin de las misma para posteriormente exportarla como PDF. Sugerir que este proceso es tedioso sería exagerar, pero es cierto que a veces el empleado debe recordar mediante la administración o buscando su última hoja de vacaciones cuántos días le quedan pendientes de disfrutar, o es habitual que cometa un error al no coincidir el número de días empleados con el rango de fechas que ha especificado (sobre todo cuando hay un festivo de por medio). Al tener consciencia de las vacaciones del empleado, la aplicación podría generar fácilmente la hoja de vacaciones solicitando únicamente un rango de fechas.
