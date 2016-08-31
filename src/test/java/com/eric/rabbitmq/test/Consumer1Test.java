package com.eric.rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.eric.rabbitmq.Consumer1;

public class Consumer1Test {

	public static void main(String[] args) throws IOException, TimeoutException {
		Consumer1 consumer1 = new Consumer1("workqueue_p");
		Thread thread = new Thread(consumer1);
		thread.start();
	}
	
}
