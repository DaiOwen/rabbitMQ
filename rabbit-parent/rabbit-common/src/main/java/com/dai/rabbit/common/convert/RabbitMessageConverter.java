package com.dai.rabbit.common.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * $RabbitMessageConverter
 *
 * @author daihuhu
 */
public class RabbitMessageConverter implements MessageConverter {

    private GenericMessageConverter delegate;

    public RabbitMessageConverter(GenericMessageConverter delegate) {
        Preconditions.checkNotNull(delegate);
        this.delegate = delegate;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        com.dai.api.Message msg = (com.dai.api.Message)object;
        messageProperties.setDelay(msg.getDelayMills());
        return this.delegate.toMessage(object,messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.dai.api.Message msg = (com.dai.api.Message) this.delegate.fromMessage(message);
        return msg;
    }
}
