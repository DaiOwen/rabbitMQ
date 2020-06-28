package com.dai.rabbit.common.serializer;

/**
 * $Serializer 序列化何反序列化接口
 * @author Administrator
 */
public interface Serializer {

    byte[] serializeRaw(Object data);

    String serialize(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);
}
