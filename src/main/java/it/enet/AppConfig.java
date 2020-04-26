package it.enet;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("file:application.properties")
public class AppConfig {
	@Bean("Connection")
	public Connection getConnection(@Value("${urlConnection}") String url) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		return connectionFactory.createConnection();
	}

	@Bean("Session")
	public Session getSession(Connection connection, @Value("${sessionValue}") int value) throws JMSException {
		return connection.createSession(false, value);
	}

}