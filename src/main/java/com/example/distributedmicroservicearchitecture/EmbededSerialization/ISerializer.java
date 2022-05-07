package com.example.distributedmicroservicearchitecture.EmbededSerialization;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author gongyue
 * @date 2022-05-01
 * @description 自定义Java默认序列化接口
 */
public interface ISerializer {

    /**
     * 对象的序列化
     * @param obj
     * @return
     * @param <T> 代表任意泛型，动态取决于你的需要的传参和返回参数
     */
    <T> byte[] serialize (T obj);

    /**
     * 对象的反序列化
     * @param data
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T deserialize (byte[] data, Class<T> clazz);

    /**
     * 将对象序列化写入到文件中
     * @param obj
     * @param fileName
     * @param <T>
     */
    <T> void serializeToFile (T obj, String fileName);

    /**
     * 从文件中反序列化成对象
     * @param fileName
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T deserializeFromFile (String fileName, Class<T> clazz);

}
