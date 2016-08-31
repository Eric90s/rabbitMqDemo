package com.eric.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {

	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	public EndPoint(String endPointName) throws IOException, TimeoutException {
//		super();
		this.endPointName = endPointName;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		boolean durable = true;	//声明是否持久化消息
		channel.queueDeclare(endPointName, durable, false, false, null);
	}
	
	
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}
	
	
}
