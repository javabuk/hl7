package org.hl7.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hl7.configuracion.ConfiguracionParser;
import org.hl7.configuracion.DatosMensajeSinParsear;
import org.hl7.entidad.DatosAdicionales;
import org.hl7.entidad.InfoMensajeHL7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.validation.builder.support.DefaultValidationBuilder;

public class ParserHL7 {
	
	
	private static final String RUTA_VERSION = "/MSH-12";
	private static final String RUTA_TIPOMENSAJE = "/MSH-9-3";
	
	private ConfiguracionParser configuracion;
	private DefaultValidationBuilder validacion;
	
	
	public ParserHL7() {
		this(null);		
	}
	
	public ParserHL7(ConfiguracionParser configuracion) {		
		this(configuracion, null);	
	}

	public ParserHL7(ConfiguracionParser configuracion, DefaultValidationBuilder validacion ) {
		super();
		this.configuracion = configuracion;
		this.validacion = validacion;
	}

	public InfoMensajeHL7 parsearMensaje(String mensaje){
		
		InfoMensajeHL7 respuesta = new InfoMensajeHL7();
		PipeParser parseadorPipe = null;
		if(validacion!=null){
			HapiContext context = new DefaultHapiContext();
			context.setValidationRuleBuilder(validacion);
			parseadorPipe = context.getPipeParser();
		}else{
			parseadorPipe = new PipeParser();
		}
		
		
		Message mensajeHL7;
		try {
			mensajeHL7 = parseadorPipe.parse(mensaje);
			respuesta.setTipoMensaje(mensajeHL7.getClass().getName());
			respuesta.setMensajeParseado(mensajeHL7.getClass());
			Terser terser = new Terser(mensajeHL7);
			String version = terser.get(RUTA_VERSION);
			String tipoMensaje = terser.get(RUTA_TIPOMENSAJE);
			if(configuracion!=null && configuracion.getDatosRecuperar() != null){
				List<DatosMensajeSinParsear> datosRecuperar = configuracion.getDatosRecuperar();
				List<DatosAdicionales> datosAdicionales = new ArrayList<DatosAdicionales>();
				for (Iterator<DatosMensajeSinParsear> iterator = datosRecuperar.iterator(); iterator.hasNext();) {
					DatosMensajeSinParsear datosMensajeSinParsear = iterator.next();
					DatosAdicionales datoAdicional = new DatosAdicionales();
					datoAdicional.setValor(terser.get(datosMensajeSinParsear.getRutaParser()));
					datoAdicional.setConcepto(datosMensajeSinParsear.getConcepto());
					datosAdicionales.add(datoAdicional);
				}
				respuesta.setDatosAdicionales(datosAdicionales);
			}
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
