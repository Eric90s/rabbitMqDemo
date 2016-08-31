package com.eric.rabbitmq;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.MessageProperties;

public class Producer extends EndPoint {

	public Producer(String endPointName) throws IOException, TimeoutException {
		super(endPointName);
		// TODO Auto-generated constructor stub
	}
	
	public void sendMessage(Serializable object) throws IOException {
		//MessageProperties.PERSISTENT_TEXT_PLAIN 用来设置将消息持久化
		channel.basicPublish("", endPointName, MessageProperties.PERSISTENT_TEXT_PLAIN, SerializationUtils.serialize(object));
	}

}
