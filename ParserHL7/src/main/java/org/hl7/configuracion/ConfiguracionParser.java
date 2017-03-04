package org.hl7.configuracion;

import java.util.List;

public class ConfiguracionParser {
	
	public List<DatosMensajeSinParsear> datosRecuperar;

	public ConfiguracionParser(List<DatosMensajeSinParsear> datosRecuperar) {
		super();
		this.datosRecuperar = datosRecuperar;
	}

	public List<DatosMensajeSinParsear> getDatosRecuperar() {
		return datosRecuperar;
	}

	public void setDatosRecuperar(List<DatosMensajeSinParsear> datosRecuperar) {
		this.datosRecuperar = datosRecuperar;
	}	
	

}
