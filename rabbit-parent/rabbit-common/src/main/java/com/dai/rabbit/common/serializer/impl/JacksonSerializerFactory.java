package com.dai.rabbit.common.serializer.impl;

import com.dai.rabbit.common.serializer.Serializer;
import com.dai.rabbit.common.serializer.SerializerFactory;

public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer;
    }
}
