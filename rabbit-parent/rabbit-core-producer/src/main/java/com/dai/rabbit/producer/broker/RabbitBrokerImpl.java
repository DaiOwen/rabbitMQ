package com.dai.rabbit.producer.broker;

import com.dai.api.Message;
import com.dai.api.MessageType;
import com.dai.rabbit.producer.constant.BrokerMessageConst;
import com.dai.rabbit.producer.constant.BrokerMessageStatus;
import com.dai.rabbit.producer.entity.BrokerMessage;
import com.dai.rabbit.producer.service.MessageStoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * $RabbitBrokerImpl 真正的发送不同类型的消息实现类
 *
 * @author Administrator
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    private RabbitTemplateContainer rabbitTemplateContainer;

    @Autowired
    private MessageStoreService messageStoreService;
    /**
     * 可靠新消息的发送
     * @param message
     */
    @Override
    public void reliantSend(Message message) {
        message.setMessageType(MessageType.RELIANT);
        BrokerMessage bm = messageStoreService.selectByMessageId(message.getMessageId());
        if(bm == null) {
            //1. 把数据库的消息发送日志先记录好
            Date now = new Date();
            BrokerMessage brokerMessage = new BrokerMessage();
            brokerMessage.setMessageId(message.getMessageId());
            brokerMessage.setStatus(BrokerMessageStatus.SENDING.getCode());
            //tryCount 在最开始发送的时候不需要进行设置
            brokerMessage.setNextRetry(DateUtils.addMinutes(now, BrokerMessageConst.TIMEOUT));
            brokerMessage.setCreateTime(now);
            brokerMessage.setUpdateTime(now);
            brokerMessage.setMessage(message);
            messageStoreService.insert(brokerMessage);
        }
        //2. 执行真正的发送消息逻辑
        sendKernel(message);
    }

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
        AsyncBaseQueue.submit( () -> {
            CorrelationData correlationData =
                    new CorrelationData(String.format("%s#%s#%s",
                    message.getMessageId(),
                    System.currentTimeMillis(),
                    message.getMessageType()));
            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();
            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
            rabbitTemplate.convertAndSend(topic,routingKey,message,correlationData);
            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}",message.getMessageId());
        });

    }

    @Override
    public void confirmSend(Message message) {
        //增加message.setMessageType()操作，是为了再次确认。
        message.setMessageType(MessageType.CONFIRM);
        sendKernel(message);
    }

    @Override
    public void sendMesssages() {

    }
}
