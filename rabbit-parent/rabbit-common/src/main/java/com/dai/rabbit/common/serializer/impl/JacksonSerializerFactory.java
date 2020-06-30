package com.dai.rabbit.common.serializer.impl;

import com.dai.api.Message;
import com.dai.rabbit.common.serializer.Serializer;
import com.dai.rabbit.common.serializer.SerializerFactory;

/**
 * $JacksonSerializerFactory 序列化工厂
 * @author daihuhu
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParameterSerial(Message.class);
    }
}
