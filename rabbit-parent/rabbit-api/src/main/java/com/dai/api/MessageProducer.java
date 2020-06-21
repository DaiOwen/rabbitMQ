package com.dai.api;


import com.dai.api.exception.MessageRunTimeException;

import java.util.List;

/**
 * 	$MessageProducer
 * @author Alienware
 *
 */
public interface MessageProducer {

	/**
	 * 	$send消息的发送 附带SendCallback回调执行响应的业务逻辑处理
	 * @param message 发送的消息
	 * @param sendCallback 回调函数
	 * @throws MessageRunTimeException 消息运行异常
	 */
	void send(Message message, SendCallback sendCallback) throws MessageRunTimeException;
	
	/**
	 * 	
	 * @param message 消息的发送
	 * @throws MessageRunTimeException 消息运行异常
	 */
	void send(Message message) throws MessageRunTimeException;
	
	/**
	 * 	$send 消息的批量发送
	 * @param messages 发送的消息
	 * @throws MessageRunTimeException 消息运行异常
	 */
	void send(List<Message> messages) throws MessageRunTimeException;
	
}
