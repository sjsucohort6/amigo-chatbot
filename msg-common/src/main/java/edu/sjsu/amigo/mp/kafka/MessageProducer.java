/*
 * Copyright (c) 2017 San Jose State University.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package edu.sjsu.amigo.mp.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static edu.sjsu.amigo.mp.kafka.MessageQueueConstants.USER_MSG_TOPIC;

/**
 * A kafka message producer.
 *
 * @author rwatsh on 2/26/17.
 */
public class MessageProducer implements AutoCloseable {

    private final Producer producer;

    public MessageProducer(String kafkaHostName) {
        //Configure the Producer
        Properties configProperties = new Properties();
        // Assuming that localhost port 9092 is mapped to kafka container's port 9092
        // TODO externalize the port
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHostName + ":9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(configProperties);
    }

    public void sendUserMessage(String message) {
        sendMessage(USER_MSG_TOPIC, message);
    }

    private void sendMessage(String topicName, String message) {
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, message);
        producer.send(rec);
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public void close() throws Exception {
        if (producer != null) {
            producer.close();
        }
    }
}
