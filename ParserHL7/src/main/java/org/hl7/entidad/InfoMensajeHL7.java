package org.hl7.entidad;

import ca.uhn.hl7v2.model.Message;

public class InfoMensajeHL7 {
	
	private String tipoMensajeHL7;
	
	private String version;
	
	private Message mensaje;

	public String getTipoMensajeHL7() {
		return tipoMensajeHL7;
	}

	public void setTipoMensajeHL7(String tipoMensajeHL7) {
		this.tipoMensajeHL7 = tipoMensajeHL7;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Message getMensaje() {
		return mensaje;
	}

	public void setMensaje(Message mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
