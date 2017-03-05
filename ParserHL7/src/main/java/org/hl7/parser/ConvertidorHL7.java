package org.hl7.parser;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.message.ADT_A03;

public class ConvertidorHL7 {
	
	public ADT_A03 convertirADTA01enADTA03(ADT_A01 mensajeADT_A01){
		
		ADT_A03 mensajeADT_A03 = new ADT_A03();
		
		String [] segmentos  = mensajeADT_A01.getNames();
		for (int i = 0; i < segmentos.length; i++) {
			try {
				Structure estructuraSegmento = mensajeADT_A01.get(segmentos[i]);
				//mensajeADT_A03.
				//estructuraSegmento.
			
			} catch (HL7Exception e) {
				e.printStackTrace();
			}
		}
		
		return mensajeADT_A03;
		
	}

}
