package com.example.distributedmicroservicearchitecture.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 该UID的作用是在序列化到磁盘时是向后兼容的
     * 即假如之前User类曾有过序列化的操作
     * 更改版本号在重新反序列化对导致java.io.InvalidClassException
     */
    private static final long serialVersionUID = 1L;

    String name;

    Long id;
}
