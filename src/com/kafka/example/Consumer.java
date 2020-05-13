package com.kafka.example;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.api.java.JavaInputDStream;

public class Consumer {
    @SuppressWarnings("resource")
    public static int consume(String brokers, String groupId, String topicName) {
        KafkaConsumer<String, String> consumer;
        		
        	   Properties properties = new Properties();
               properties.setProperty("bootstrap.servers", brokers);
               properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
               properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
               properties.setProperty("group.id", groupId);
               properties.setProperty("auto.offset.reset","earliest");


        consumer = new KafkaConsumer<String, String>(properties);

        // Subscribe to the 'actors' topic
        /* consumer.subscribe(Arrays.asList(topicName));

		
		
		  int count = 0; while(true) { ConsumerRecords<String, String> records =
		  consumer.poll(200);
		  
		 if (records.count() == 0) { } else { for(ConsumerRecord<String, String>
		  record: records) { // Display record and count 
			  count += 1;
		  System.out.println( count + ": " + record.value()); } } }
		 */
        List<String> topics = Arrays.asList(topicName);
		  JavaInputDStream<ConsumerRecord<String, String>> messages = 
				  KafkaUtils.createDirectStream(
				    streamingContext, 
				    LocationStrategies.PreferConsistent(), 
				    ConsumerStrategies.<String, String> Subscribe(topics, kafkaParams));
		/*
		 * SparkSession
		 * spark=SparkSession.builder().appName("kafka streaming").getOrCreate();
		 * 
		 * Dataset<Row> df = spark .readStream() .format("kafka")
		 * .option("kafka.bootstrap.servers", brokers) .option("subscribe", topicName)
		 * .load(); df.show();
		 */
    }
}
