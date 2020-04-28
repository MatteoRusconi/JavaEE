package it.enet;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Data;

@Data
public class MessageSender {

	private static List<Message> msgLetti = new ArrayList();
	private String subject;
	public Session session;

	public MessageSender() throws JMSException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		this.session = (Session) ctx.getBean("session");
		this.subject = "JCG_QUEUE";
	}

	public void sender(String mittente, String textMessage) throws JMSException {

		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session.createTextMessage(textMessage);
		message.setStringProperty("mittente", mittente);
		// Here we are sending our message!
		producer.send(message);

	}

}