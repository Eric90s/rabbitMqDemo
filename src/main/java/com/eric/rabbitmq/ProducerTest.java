package com.eric.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class ProducerTest {

	public static void main(String[] args) throws IOException, TimeoutException {
		Producer producer = new Producer("queue");
        
        for (int i = 101; i < 200; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
        
        producer.close();
	}
}
