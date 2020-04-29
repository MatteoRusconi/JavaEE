package it.enet;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MessageSender {

	private String subject;
	public Session session;

	public MessageSender(Session session, String subject) throws JMSException {
		this.subject = subject;
		this.session = session;
	}

	public void sender(String mittente, String textMessage) throws JMSException {
		Destination destination = session.createQueue(subject);

		MessageProducer producer = session.createProducer(destination);

		TextMessage message = session.createTextMessage(textMessage);
		message.setStringProperty("mittente", mittente);

		producer.send(message);

	}

}