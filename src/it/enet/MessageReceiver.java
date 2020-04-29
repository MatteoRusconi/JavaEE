package it.enet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MessageReceiver {
	private static List<Message> msgLetti = new ArrayList();
	private String subject;
	private Session session;

	public MessageReceiver(Session session, String subject) throws JMSException {
		this.session = session;
		this.subject = subject;
	}

	public void receiver(String username) throws JMSException {

		Destination destination = session.createQueue(subject);

		MessageConsumer consumer = session.createConsumer(destination);

		Queue queue = session.createQueue(subject);
		QueueBrowser browser = session.createBrowser(queue);
		Enumeration<?> messagesInQueue = browser.getEnumeration();

		while (messagesInQueue.hasMoreElements()) {

			Message message = (Message) messagesInQueue.nextElement();

			if (!message.getStringProperty("mittente").contentEquals(username)) {

				if (!msgLetti.contains(message)) {
					TextMessage textMessage = (TextMessage) message;
					msgLetti.add(message);
					System.out.println(message.getStringProperty("mittente") + ": " + textMessage.getText());
				}

			}

		}
	}

}