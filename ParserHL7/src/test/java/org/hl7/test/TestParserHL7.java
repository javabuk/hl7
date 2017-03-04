package org.hl7.test;

import static org.junit.Assert.*;

import org.hl7.entidad.InfoMensajeHL7;
import org.hl7.parser.ParserHL7;
import org.junit.Test;

public class TestParserHL7 {

	@Test
	public void testParserVersion() {
		
		String mensajePrueba = "MSH|^~\\&|INFO33||||20160627122000||ADT^A01^ADT_A01|101|P|2.3\r" +
				"PID|||||Doe^John";
		ParserHL7 parser = new ParserHL7();
		InfoMensajeHL7 mensajeInfo = parser.parsearMensaje(mensajePrueba);
		
		assertNotNull(mensajeInfo);
	}

}
