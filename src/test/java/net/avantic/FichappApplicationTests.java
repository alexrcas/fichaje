package net.avantic;

import net.avantic.domain.model.*;
import net.avantic.utils.ValidadorStateMachine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FichappApplicationTests {

	@Test
	void contextLoads() {
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
