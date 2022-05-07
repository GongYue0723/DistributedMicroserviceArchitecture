package com.example.distributedmicroservicearchitecture.EmbededSerialization;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.io.*;

@Service
public class JavaSerializer implements ISerializer{
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try{
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public <T> void serializeToFile(T obj, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileName);
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> clazz) {
        FileInputStream fileOutputStream = null;
        ObjectInputStream inputStream = null;
        try{
            fileOutputStream = new FileInputStream(fileName);
            inputStream = new ObjectInputStream(fileOutputStream);
            return (T) inputStream.readObject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
