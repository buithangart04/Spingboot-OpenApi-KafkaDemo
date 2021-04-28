package com.fpt.projectthuviec.Kafka;

import com.fpt.projectthuviec.Model.JwtRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "my_topic1",groupId = "group_json",containerFactory = "authenticateListener")
    public void consumerAuthenticate(JwtRequest jwtRequest){
        System.out.println("Consumer Authenticate :" +jwtRequest.toString());
    }


}
