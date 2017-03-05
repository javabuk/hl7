package org.hl7.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hl7.configuracion.ConfiguracionParser;
import org.hl7.configuracion.DatosMensajeSinParsear;
import org.hl7.entidad.InfoMensajeHL7;
import org.hl7.parser.ParserHL7;
import org.hl7.validaciones.Validaciones2_5;
import org.junit.Test;

import ca.uhn.hl7v2.model.v25.message.ADT_A01;

public class TestParserHL7 {

	@Test
	public void testParserVersion() {
		
		String mensajePrueba = "MSH|^~\\&|INFO33||||20160627122000||ADT^A01^ADT_A01|101|P|2.3\r" +
				"PID|||||Doe^John";
		ParserHL7 parser = new ParserHL7();
		InfoMensajeHL7 mensajeInfo = parser.parsearMensaje(mensajePrueba);
		
		assertNotNull(mensajeInfo);
	}
	
	@Test
	public void testParserVersionDatosAdicionales() {
		
		String mensajePrueba = "MSH|^~\\&|INFO33||MEDIKOSTA||20160627122000||ADT^A01^ADT_A01|101|P|2.5\r" +
				"PID|||||Doe^John";
		List<DatosMensajeSinParsear> listaDatosAdicionales = new ArrayList<DatosMensajeSinParsear>();
		DatosMensajeSinParsear datoAdicional = new DatosMensajeSinParsear();
		datoAdicional.setConcepto("Emisor");
		datoAdicional.setRutaParser("/MSH-3-1");
		listaDatosAdicionales.add(datoAdicional);
		
		DatosMensajeSinParsear datoAdicionalReceptor = new DatosMensajeSinParsear();
		datoAdicionalReceptor.setConcepto("Receptor");
		datoAdicionalReceptor.setRutaParser("/MSH-5-1");
		listaDatosAdicionales.add(datoAdicionalReceptor);
		
		ConfiguracionParser configuracion = new ConfiguracionParser(listaDatosAdicionales);
		
		ParserHL7 parser = new ParserHL7(configuracion);
		InfoMensajeHL7 mensajeInfo = parser.parsearMensaje(mensajePrueba);
		
		ADT_A01 mensajeADT = (ADT_A01)mensajeInfo.getMensaje();
		
		/*try {
			Class<?> cls = Class.forName(mensajeInfo.getTipoMensaje());
			Object instance = cls.newInstance();
			//T value = cls.cast(mensajeInfo.getMensajeParseado());
			System.out.println("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		assertNotNull(mensajeInfo);
	}
	
	@Test
	public void testParserVersionDatosAdicionalesValidacion() {
		
		String mensajePrueba = "MSH|^~\\&|INFO33||MEDIKOSTA||20160627122000||ADT^A01^ADT_A01|101|P|2.5\r" +
				"PID|||||Doe^John";
		List<DatosMensajeSinParsear> listaDatosAdicionales = new ArrayList<DatosMensajeSinParsear>();
		DatosMensajeSinParsear datoAdicional = new DatosMensajeSinParsear();
		datoAdicional.setConcepto("Emisor");
		datoAdicional.setRutaParser("/MSH-3-1");
		listaDatosAdicionales.add(datoAdicional);
		
		DatosMensajeSinParsear datoAdicionalReceptor = new DatosMensajeSinParsear();
		datoAdicionalReceptor.setConcepto("Receptor");
		datoAdicionalReceptor.setRutaParser("/MSH-5-1");
		listaDatosAdicionales.add(datoAdicionalReceptor);
		
		ConfiguracionParser configuracion = new ConfiguracionParser(listaDatosAdicionales);
		
		ParserHL7 parser = new ParserHL7(configuracion,new Validaciones2_5());
		InfoMensajeHL7 mensajeInfo = parser.parsearMensaje(mensajePrueba);
		
		ADT_A01 mensajeADT = (ADT_A01)mensajeInfo.getMensaje();
		
		/*try {
			Class<?> cls = Class.forName(mensajeInfo.getTipoMensaje());
			Object instance = cls.newInstance();
			//T value = cls.cast(mensajeInfo.getMensajeParseado());
			System.out.println("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		assertNotNull(mensajeInfo);
	}

}
