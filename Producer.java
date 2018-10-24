package com.example.ZRabbit.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class Producer {


    private static final String Queue_Name = "queue";

    @GetMapping("/abc")
    public void Rabbit() throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        factory.setHost("159.69.41.213");
        factory.setUsername("oneshell");
        factory.setPassword("RaBbIt123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queue_Name, false, false, false, null);

        for (int i = 0; i <= 1000; i++) {
            String message = "something missing" + i;
            Thread.sleep(2000);
            channel.basicPublish("", Queue_Name, null, message.getBytes());

            System.out.println("publish the message:" + message);
        }
        channel.close();
        connection.close();
    }
}



