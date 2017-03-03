package org.hl7.prueba;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.Escaping;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.ParserConfiguration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jorge
 *
 */
public class GeneradorMensaje {

	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(GeneradorMensaje.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		         /*
		          * In a real situation, of course, many more segments and fields would be populated
		          */
		         
		         
		          
		         /*
		          * Prints:
		          * 
		          * MSH|^~\&|TestSendingSystem||||200701011539||ADT^A01^ADT A01||||123
		          * PID|||123456||Doe^John
		          */
		 
		         try {
					// TODO Auto-generated method stub
					ADT_A01 adt = new ADT_A01();
					adt.initQuickstart("ADT", "A01", "P");

					
					// Populate the MSH Segment
					MSH mshSegment = adt.getMSH();
					
					mshSegment.getMsh3_SendingApplication().getHd1_NamespaceID().setValue("INFO33");
					mshSegment.getMsh7_DateTimeOfMessage().getTs1_Time().setValue("20160627122000");
					//mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
					//mshSegment.getSequenceNumber().setValue("123");
					
					
					
					// Populate the PID Segment
					PID pid = adt.getPID();
					pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
					pid.getPatientName(0).getGivenName().setValue("John");
					//pid.getPatientIdentifierList(0).getID().setValue("123456");
					// Now, let's encode the message and look at the output
					HapiContext context = new DefaultHapiContext();
					//ParserConfiguration config = new ParserConfiguration();
					ParserConfiguration config = context.getParserConfiguration();
					Escaping escape = config.getEscaping();
					Parser parser = context.getPipeParser(); 
					String encodedMessage = parser.encode(adt);
					System.out.println("Printing ER7 Encoded Message:");
					System.out.println(encodedMessage.toCharArray());
					System.out.println(encodedMessage.toString());
					System.out.println("/////////////");
					slf4jLogger.info(encodedMessage);
					slf4jLogger.info(replaceNewlines(encodedMessage));
					System.out.println("/////////////");
					// Next, let's use the XML parser to encode as XML
					parser = context.getXMLParser();
					encodedMessage = parser.encode(adt);
					System.out.println("Printing XML Encoded Message:");
					System.out.println(encodedMessage);
				} catch (Exception e) {
					// TODO: handle exception
				}
	}
	
	
	private static String replaceNewlines(String input) {
	    return input.replaceAll("\\r", "\n");
	}

}