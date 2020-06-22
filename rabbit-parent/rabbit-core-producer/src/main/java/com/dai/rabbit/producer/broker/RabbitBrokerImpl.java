package com.dai.rabbit.producer.broker;

import com.dai.api.Message;
import com.dai.api.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * $RabbitBrokerImpl 真正的发送不同类型的消息实现类
 *
 * @author Administrator
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 迅速发送消息
     *
     * @param message 消息
     */
    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    /**
     * $sendKernel 发送消息的核心方法，使用异步线程池进行发送消息
     *
     * @param message 消息
     */
    private void sendKernel(Message message) {
        AsyncBaseQueue.submit((Runnable) () -> {
            CorrelationData correlationData =
                    new CorrelationData(String.format("%s#%s#%s",
                    message.getMessageId(),
                    System.currentTimeMillis(),
                    message.getMessageType()));
            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();
            rabbitTemplate.convertAndSend(topic,routingKey,message,correlationData);
            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}",message.getMessageId());
        });

    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSend(Message message) {

    }

    @Override
    public void sendMesssages() {

    }
}
