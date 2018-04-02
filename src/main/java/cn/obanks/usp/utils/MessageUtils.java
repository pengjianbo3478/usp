package cn.obanks.usp.utils;
import java.io.Serializable;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageUtils {
	public static void send(final ConnectionFactory connectionFactory, final String queueName, final Serializable msg) {
		new JmsTemplate(connectionFactory).send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(msg);
			}
		});
	}
}
