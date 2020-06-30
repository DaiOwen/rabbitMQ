package com.dai.rabbit.common.serializer;

/**
 * $Serializer 序列化和反序列化接口
 * @author Administrator
 */
public interface Serializer {

    /**
     * @param data 传入对象参数
     * @return byte[] 返回序列化之后的比特数组
     */
    byte[] serializeRaw(Object data);


    String serialize(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);
}
