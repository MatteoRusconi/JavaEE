package it.enet;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:application.properties")
public class MessageSender {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
	// on localhost
	@Autowired
	private static Connection connection;
	@Autowired
	private static Session session;
	// default broker URL is : tcp://localhost:61616"
	@Value("${subject}")
	private static String subject;

	public static void sender(String mittente, String textMessage) throws JMSException {

		// Creating session for seding messages
		connection.start();

		// Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		// The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session.createTextMessage(textMessage);
		message.setStringProperty("mittente", mittente);
		// Here we are sending our message!
		producer.send(message);

		connection.close();
	}
}