package com.kafka.example;


import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
		/*
		 * if(args.length < 3) { usage(); }
		 */
        // Get the brokers
        String brokers = "localhost:9092";
        String topicName = "mysqldata";
        String run="create";
        switch(run.toLowerCase()) {
            case "producer":
                Producer.produce(brokers, topicName);
                break;
            case "consumer":
                String  groupId = "SampleProducer";
                Consumer.consume(brokers, groupId, topicName);
                break;
            case "describe":
                AdminClientWrapper.describeTopics(brokers, topicName);
                break;
            case "create":
                AdminClientWrapper.createTopics(brokers, topicName);
                break;
            case "delete":
                AdminClientWrapper.deleteTopics(brokers, topicName);
                break;
            default:
                usage();
        }
        System.exit(0);
    }
    // Display usage
    public static void usage() {
        System.out.println("Usage:");
        System.out.println("kafka-example.jar <producer|consumer|describe|create|delete> <topicName> brokerhosts [groupid]");
        System.exit(1);
    }
}
