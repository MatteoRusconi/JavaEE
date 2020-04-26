package it.enet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MessageReceiver {

	private static List<Message> msgLetti = new ArrayList();

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is

	@Autowired
	private static Connection connection;
	@Autowired
	private static Session session;
	// default broker URL is : tcp://localhost:61616"
	@Value("${subject}")
	private static String subject;

	public static void receiver(String username) throws JMSException {
		// Getting JMS connection from the server
		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();

		connection.start();
		// Creating session for seding messages
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

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

		connection.close();
	}
}