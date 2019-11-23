# kafka-java-example

Step 1: Download kafka from<br>
https://kafka.apache.org/downloads 

> tar -xzf kafka_{version}.tgz<br>
> cd kafka_{version}<br>

Step 2: Start the server
Kafka uses ZooKeeper you need to first start a ZooKeeper server

> sh ./kafka-server-start.sh ../config/server.properties

Step 3: Create a topic

> sh ./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test<br>
> sh ./zookeeper-server-start.sh ../config/zookeeper.properties

you can see the topic by running the following list topic command:

> sh ./kafka-topics.sh --list --bootstrap-server localhost:9092<br>
-> test

Step 4: Create a producer

> sh ./kafka-console-producer.sh --broker-list localhost:9092 --topic test<br>
-> This is a message<br>
-> This is another message

Step 5: Create a consumer

> sh ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning<br>
-> This is a message<br>
-> This is another message

Step 5: Run the project and test the messaging inside it
