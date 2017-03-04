package org.hl7.parser;

import org.hl7.entidad.InfoMensajeHL7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;

public class ParserHL7 {
	
	public InfoMensajeHL7 parsearMensaje(String mensaje){
		
		InfoMensajeHL7 respuesta = new InfoMensajeHL7();
		
		PipeParser parseadorPipe = new PipeParser();
		
		Message mensajeHL7;
		try {
			mensajeHL7 = parseadorPipe.parse(mensaje);
			Terser terser = new Terser(mensajeHL7);
			String version = terser.get("/MSH-12");
			String tipoMensaje = terser.get("/MSH-9-3");
			
			respuesta.setMensaje(mensajeHL7);
			respuesta.setTipoMensajeHL7(tipoMensaje);
			respuesta.setVersion(version);
			
		} catch (HL7Exception e) {
			respuesta.setTipoMensajeHL7("error");
			e.printStackTrace();
		}finally{
			return respuesta;
		}
	}

}
