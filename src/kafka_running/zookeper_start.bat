D:\kafka_2.11-2.4.1\bin\windows\zookeeper-server-start.bat D:\kafka_2.11-2.4.1\config\zookeeper.properties

D:\kafka_2.11-2.4.1\bin\windows\kafka-server-start.bat D:\kafka_2.11-2.4.1\config\server.properties

D:\kafka_2.11-2.4.1\bin\windows\zookeeper-shell.bat localhost:2181 ls /brokers/ids 


D:\kafka_2.11-2.4.1\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test  



D:\kafka_2.11-2.4.1\bin\windows\zookeeper-server-stop.bat D:\kafka_2.11-2.4.1\config\zookeeper.properties


D:\kafka_2.11-2.4.1\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181


D:\kafka_2.11-2.4.1\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test

D:\kafka_2.11-2.4.1\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning  
