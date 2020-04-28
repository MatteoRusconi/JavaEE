package it.enet;

import java.util.Scanner;

import javax.jms.JMSException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws JMSException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		MessageReceiver msgr = (MessageReceiver) ctx.getBean("receiver");
		MessageSender msgs = (MessageSender) ctx.getBean("sender");
		Scanner scan = new Scanner(System.in);
		Boolean cond = true;
		int input = 0;
		String textMessage = null;

		String username = null;
		System.out.println("Inserire username:");
		username = scan.nextLine();

		while (cond) {
			System.out.println("********************************");
			System.out.println("1. Invia un messaggio");
			System.out.println("2. Ricevi Messaggi");
			System.out.println("3. Esci");
			System.out.println("********************************");
			System.out.println("Scegli:");

			input = scan.nextInt();

			scan.nextLine();
			switch (input) {

			case 1:
				System.out.println("********************************");

				System.out.println("\nMessaggio da inviare:");
				textMessage = scan.nextLine();
				msgs.sender(username, textMessage);
				System.out.println("*** MESSAGGIO INVIATO ***");
				System.out.println("");
				break;

			case 2:
				System.out.println("********************************");

				System.out.println("\nMessaggi ricevuti:");
				System.out.println("");
				msgr.receiver(username);
				System.out.println("");
				break;

			case 3:
				cond = false;
				break;
			default:
				cond = false;
				break;
			}
		}
	}

}
