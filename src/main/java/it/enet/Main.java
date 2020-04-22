package it.enet;

import java.util.Scanner;

import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;

public class Main {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
	// on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL is : tcp://localhost:61616"
	private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your

	public static void main(String[] args) throws JMSException {

		Scanner scan = new Scanner(System.in);
		Boolean cond = true;

		while (cond) {
			System.out.println("1. Invia un messaggio");
			System.out.println("2. Ricevi Messaggi");
			System.out.println("3. Esci");
			int input = scan.nextInt();
			String textMessage = null;
			scan.nextLine();
			switch (input) {
			case 1:
				System.out.println("Messaggio da inviare:");
				textMessage = scan.nextLine();
				MessageSender.sender(textMessage);
				break;
			case 2:
				System.out.println("Messaggi ricevuti:");
				MessageReceiver.receiver();
				break;
			case 3:
				cond = false;
				break;
			}
		}

	}

}
