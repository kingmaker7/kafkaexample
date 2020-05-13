package com.kafka.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer
{
    public static void produce(String brokers, String topicName) throws IOException
    {

        // Set properties used to configure the producer
        Properties properties = new Properties();
        // Set the brokers (bootstrap servers)
       // properties.setProperty("bootstrap.servers", brokers);
        // Set how to serialize key/value pairs
        //properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        //properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        String msg="";
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
         try {
        	 ArrayList<String> list=DataDump.getData();
        	 for(int i=0;i<list.size();i++) {
        		 msg=list.get(i);
        		 System.out.println(msg);
        		 producer.send(new ProducerRecord<String, String>(topicName, msg)).get();
        	 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	
}
