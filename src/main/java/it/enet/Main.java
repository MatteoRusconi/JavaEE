package it.enet;

import java.util.Scanner;

import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;

public class Main {

	public static void main(String[] args) throws JMSException {

		Scanner scan = new Scanner(System.in);
		Boolean cond = true;

		String mittente = null;
		System.out.println("Inserire nome:");
		mittente = scan.nextLine();

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
				MessageSender.sender(mittente, textMessage);
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
