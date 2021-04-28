package com.fpt.projectthuviec.Kafka;

import com.fpt.projectthuviec.Model.JwtRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, JwtRequest> authenticateConsumerFactory(){
        Map<String, Object> config= new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return  new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<>(JwtRequest.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,JwtRequest> authenticateListener(){
        ConcurrentKafkaListenerContainerFactory<String,JwtRequest> factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(authenticateConsumerFactory());
        return factory;

    }
}
