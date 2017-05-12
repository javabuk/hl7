package org.hl7.validaciones;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.hl7v2.Version;
import ca.uhn.hl7v2.validation.ValidationException;
import ca.uhn.hl7v2.validation.builder.Predicate;
import ca.uhn.hl7v2.validation.builder.support.DefaultValidationBuilder;

/**
 * Clase para añadir validaciones al mensaje. Definidas en
 * http://hl7api.sourceforge.net/base/apidocs/ca/uhn/hl7v2/validation/builder/class-use/Predicate.html
 * 
 * @author Jorge
 *
 */
public class Validaciones2_5 extends DefaultValidationBuilder {

	@Override
	protected void configure() {
		super.configure();
		// Validamos que el campo PID-2 no este vacio
		// forVersion(Version.V25).message("ADT", "*").terser("PID-2",
		// not(empty()));
		// Validamos que el campo emisor sea MEDIKOSTA
		// forVersion(Version.V25).message("ADT", "*").terser("MSH-3-1",
		// isEqualIgnoreCase("MEDIKOSTA"));
		// Validamos que el campo emisor este comprendido en una lista
		List<String> listaEmisoresValidos = new ArrayList<String>();
		listaEmisoresValidos.add("MEDIKOSTA");
		listaEmisoresValidos.add("INFO33");
		forVersion(Version.V25).message("ADT", "*").terser("MSH-3-1", in(listaEmisoresValidos));

		// Realizamos varias validaciones sobre un mismo campo
		List<Predicate> listaValidacionesReceptor = new ArrayList<Predicate>();
		listaValidacionesReceptor.add(isEqualIgnoreCase("MEDIKOSTA"));
		listaValidacionesReceptor.add(not(number()));
		forVersion(Version.V25).message("ADT", "*").terser("MSH-5-1", allOf(listaValidacionesReceptor));

		// forVersion(Version.V25).message("ADT", "*").terser("MSH-5-1",
		// number());

		// Validación personalizada
		Predicate predicado = new Predicate() {

			public String getDescription() {
				return "Validación HL7 personalizada";
			}

			public boolean evaluate(Object data) throws ValidationException {
				if (data != null) {
					System.out.println("Tiene valor");
				} else {
					System.out.println("No tiene valor");
				}
				return true;
			}
		};
		forVersion(Version.V25).message("ADT", "*").terser("MSH-5-1", predicado);
		// Comentario añadido después del tag Tag_pru_0.0.1
	}

}
