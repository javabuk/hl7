package org.hl7.test;

import static org.junit.Assert.*;

import org.hl7.prueba.PruebaMensajes;
import org.junit.Test;

public class TestPrueba {

	@Test
	public void test() {
		
		PruebaMensajes mensajes = new PruebaMensajes();
		String prueba = mensajes.mensajePrueba();
		assertNotNull(prueba);
		
	}

}
