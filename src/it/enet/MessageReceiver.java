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
	// default broker URL is : tcp://localhost:61616"

	public void receiver(String username) throws JMSException {

		// Getting the queue 'JCG_QUEUE'
		Destination destination = session.createQueue(subject);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);

		Queue queue = session.createQueue(subject);
		QueueBrowser browser = session.createBrowser(queue);
		Enumeration<?> messagesInQueue = browser.getEnumeration();

		while (messagesInQueue.hasMoreElements()) {
			// Here we receive the message.
			Message message = (Message) messagesInQueue.nextElement();

			// We will be using TestMessage in our example. MessageProducer sent us a
			// TextMessage
			// so we must cast to it to get access to its .getText() method.

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