package com.dai.rabbit.producer.broker;


import com.dai.api.Message;

/**
 * $RabbitBroker 具体发送不同种类型消息的接口
 * @author Administrator
 */
public interface RabbitBroker {

    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);

    void sendMesssages();
}
