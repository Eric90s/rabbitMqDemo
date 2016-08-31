package com.eric.rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.eric.rabbitmq.Producer;

public class Producer1Test {

	public static void main(String[] args) throws IOException, TimeoutException {
		Producer producer = new Producer("workqueue_p");
		//发送10条消息，依次在消息后面附加1-10个点  
        for (int i = 5; i > 0; i--)  
        {  
            String dots = "";  
            for (int j = 0; j <= i; j++)  
            {  
                dots += ".";  
            }  
            String message = "helloworld" + dots+dots.length();  
            producer.sendMessage(message);
            System.out.println(" [x] Sent '" + message + "'");  
        }
        
        producer.close();
	}
}
