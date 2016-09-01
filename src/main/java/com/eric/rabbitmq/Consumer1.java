package com.eric.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer1 implements Runnable,Consumer {
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	private int hashCode;
	
	public Consumer1(String endPointName) throws IOException, TimeoutException {
		this.endPointName = endPointName;
		hashCode = Consumer1.class.hashCode();
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		channel = connection.createChannel();
		
		boolean durable = true;	//声明为持久化
		channel.queueDeclare(endPointName, durable, false, false, null);
		
		System.out.println(hashCode + " [*] Waiting for messages. To exit press CTRL+C");
		//设置最大服务转发消息数量  
        int prefetchCount = 1;  
        channel.basicQos(prefetchCount);
	}

	@Override
	public void handleConsumeOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope env,
			BasicProperties props, byte[] body) throws IOException {
		String message = (String)SerializationUtils.deserialize(body);
        System.out.println(hashCode + " [x] Received '" + message + "'");  
        doWork(message);  
        System.out.println(hashCode + " [x] Done");
        channel.basicAck(env.getDeliveryTag(), false);
		
	}

	private void doWork(String message) {
		for (char ch : message.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void handleShutdownSignal(String consumerTag,
			ShutdownSignalException sig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		try {
			boolean ack = false ; //打开应答机制
			channel.basicConsume(endPointName, ack , this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
