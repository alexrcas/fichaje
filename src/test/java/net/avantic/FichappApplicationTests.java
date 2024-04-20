package net.avantic;

import net.avantic.utils.ValidadorStateMachine;
import net.avantic.utils.statemachine.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FichappApplicationTests {


	@Test
	void contextLoads() {

		net.avantic.utils.statemachine.StateMachine stateMachine = new StateMachineByValueBuilder()
				.addTransitions(
						new ValueTransition(ValidadorStateMachine.Estado.ESPERANDO_ENTRADA_JORNADA, 1, ValidadorStateMachine.Estado.ESPERANDO_SALIDA),
						new ValueTransition(ValidadorStateMachine.Estado.ESPERANDO_SALIDA, 1, ValidadorStateMachine.Estado.FIN_JORNADA),
						new ValueTransition(ValidadorStateMachine.Estado.FIN_JORNADA, 2, ValidadorStateMachine.Estado.ERROR)
				)
				.setInitialState(ValidadorStateMachine.Estado.ESPERANDO_ENTRADA_JORNADA)
				.build();

		stateMachine.transitar(1);
		stateMachine.transitar(1);
		stateMachine.transitar(1);


/*
		ValidadorStateMachine stateMachine = new ValidadorStateMachine();

		EntradaJornada entradaJornada = new EntradaJornada(null, null);
		SalidaDesayuno salidaDesayuno = new SalidaDesayuno(null, null);
		EntradaDesayuno entradaDesayuno = new EntradaDesayuno(null, null);
		SalidaComida salidaComida = new SalidaComida(null, null);
		EntradaComida entradaComida = new EntradaComida(null, null);
		SalidaJornada salidaJornada = new SalidaJornada(null, null);

		List<Fichaje> fichajes = List.of(entradaJornada, salidaComida, entradaComida, salidaJornada, entradaComida);

		for (Fichaje fichaje : fichajes) {
			boolean success = stateMachine.transitar(fichaje);
			if (!success) {
				break;
			}
		}

		if (!stateMachine.getEstadoActual().isAceptacion()) {
			System.out.println("Se esperaba: " + stateMachine.getEstadoActual().transicionesEsperadas() + " - Se encontró: " + stateMachine.getErrorCause());
		}
*/



	}


}
