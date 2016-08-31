package com.eric.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		QueueConsumer consumer = new QueueConsumer("queue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
	}
}
