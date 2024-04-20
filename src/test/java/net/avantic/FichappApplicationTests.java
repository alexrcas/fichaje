package net.avantic;

import net.avantic.domain.model.*;
import net.avantic.utils.ValidadorStateMachine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FichappApplicationTests {


	class Transition {
		private final State inicio;
		private final Class<?> entrada;
		private final State fin;

		Transition(State inicio, Class<?> entrada, State fin) {
			this.inicio = inicio;
			this.entrada = entrada;
			this.fin = fin;
		}

		public State getFin() {
			return fin;
		}
	}

	interface State<T extends Enum<T>> {
		boolean isFinalState();
	}

	class StateMachine {
		List<Transition> transiciones;
		State estadoActual;

		public StateMachine(StateMachineBuilder builder) {
			this.transiciones = builder.getTransitions();
			this.estadoActual = builder.getInitialState();
		}

		boolean transitar(Object entrada) {
			Optional<Transition> transicion = transiciones.stream()
					.filter(t -> t.inicio.equals(this.estadoActual))
					.filter(t -> t.entrada.equals(entrada.getClass()))
					.findAny();

			if (transicion.isEmpty()) {
				return false;
			}

			estadoActual = transicion.get().getFin();
			return true;
		}
	}

	class StateMachineBuilder {
		List<Transition> transitions = new ArrayList<>();
		State initialState;

		public List<Transition> getTransitions() {
			return transitions;
		}

		public State getInitialState() {
			return initialState;
		}

		public StateMachineBuilder setInitialState(State initialState) {
			this.initialState = initialState;
			return this;
		}

		public StateMachineBuilder addTransitions(Transition... t) {
			transitions.addAll(Arrays.asList(t));
			return this;
		}

		public StateMachine build() {
			return new StateMachine(this);
		}
	}


	@Test
	void contextLoads() {

		EntradaJornada entradaJornada = new EntradaJornada(null, null);
		Fichaje salidaDesayuno = new SalidaDesayuno(null, null);
		Fichaje entradaDesayuno = new EntradaDesayuno(null, null);
		SalidaJornada salidaJornada = new SalidaJornada(null, null);

		List<Fichaje> fichajes = List.of(entradaJornada, salidaDesayuno, entradaDesayuno, salidaJornada);

		enum MisEstados implements State<MisEstados>{
			ESPERANDO_ENTRADA_JORNADA,
			ESPERANDO_SALIDA_DESAYUNO,
			ESPERANDO_ENTRADA_DESAYUNO,
			ESPERANDO_SALIDA_JORNADA,
			FIN;

			@Override
			public boolean isFinalState() {
				return this.equals(MisEstados.FIN);
			}
		}


		StateMachine stateMachine = new StateMachineBuilder()
				.setInitialState(MisEstados.ESPERANDO_ENTRADA_JORNADA)
				.addTransitions(
						new Transition(MisEstados.ESPERANDO_ENTRADA_JORNADA, EntradaJornada.class, MisEstados.ESPERANDO_SALIDA_DESAYUNO),
						new Transition(MisEstados.ESPERANDO_SALIDA_DESAYUNO, SalidaDesayuno.class, MisEstados.ESPERANDO_SALIDA_DESAYUNO),
						new Transition(MisEstados.ESPERANDO_ENTRADA_DESAYUNO, EntradaDesayuno.class, MisEstados.ESPERANDO_SALIDA_JORNADA),
						new Transition(MisEstados.ESPERANDO_SALIDA_JORNADA, SalidaJornada.class, MisEstados.FIN))
				.build();

		for (Fichaje fichaje : fichajes) {
			stateMachine.transitar(fichaje);
		}

		System.out.println("fin");


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
