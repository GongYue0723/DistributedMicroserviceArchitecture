package com.example.distributedmicroservicearchitecture;

import com.example.distributedmicroservicearchitecture.EmbededSerialization.ISerializer;
import com.example.distributedmicroservicearchitecture.EmbededSerialization.JavaSerializer;
import com.example.distributedmicroservicearchitecture.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class DistributedMicroserviceArchitectureApplicationTests {
    @Autowired
    JavaSerializer javaSerializer;

    @Test
    void contextLoads() {
    }

    @Test
    void EmbededSerialization (){
        User user = new User();
        user.setId(1l);
        user.setName("龚越");
        byte[] userBytes = javaSerializer.serialize(user);
        System.out.println(Arrays.toString(userBytes));
        User deserializedUser = javaSerializer.deserialize(userBytes, User.class);
        System.out.println("deserialized data is : " + deserializedUser);
        javaSerializer.serializeToFile(user, "temp.out");
        User deserializedUserFromFile = javaSerializer.deserializeFromFile("temp.out", User.class);
        System.out.println("deserialized data from file is : " + deserializedUserFromFile);
    }

}
