# spring-microservice-Kafka
Download the Zookeeper and ApacheKafka 
=======================================
Download and extract Zookeeper using 7-zip from http://zookeeper.apache.org/releases.html
Download and extract Kafka using 7-zip from http://kafka.apache.org/downloads.html

step1:
=========
Zookeeper Installation
=========================
1. Goto your Zookeeper config directory. For me its C:\softwares\zookeeper-3.4.9\conf

2. Rename file “zoo_sample.cfg” to “zoo.cfg”

3. Open zoo.cfg in any text editor like notepad but I prefer notepad++.

4. Find & edit dataDir=/tmp/zookeeper to :\zookeeper-3.4.9\data

5. Add entry in System Environment Variables as we did for Java

    a. Add in System Variables ZOOKEEPER_HOME = C:\softwares\zookeeper-3.4.9

    b. Edit System Variable named “Path” add ;%ZOOKEEPER_HOME%\bin;

6. You can change the default Zookeeper port in zoo.cfg file (Default port 2181).

7. Run Zookeeper by opening a new cmd and type zkserver.

8. You will see the command prompt with some details like the image .

step2:
=======
Downloading and Instal the Apache Kafka
=======================================
1. Go to your Kafka config directory. For me its C:\softwares\kafka_2.11-2.0.0\config

2. Edit file “server.properties”

3. Find & edit line “log.dirs=/tmp/kafka-logs” to “log.dir= C:\kafka_2.11-0.9.0.0\kafka-logs”.

4. If your Zookeeper is running on some other machine or cluster you can edit “zookeeper.connect:2181” to your custom IP and port. For this demo we are using same machine so no need to change. Also Kafka port & broker.id are configurable in this file. Leave other settings as it is.

5. Your Kafka will run on default port 9092 & connect to zookeeper’s default port which is 2181.

Step3:
=======
Running Kafka Server
====================
1. Go to your Kafka installation directory C:\softwares\kafka_2.11-2.0.0\

2. Open a command prompt here by pressing Shift + right click and choose“Open command window here” option)

3. Now type .\bin\windows\kafka-server-start.bat .\config\server.properties and press Enter.

example:
============
C:\softwares\kafka_2.11-2.0.0>.\bin\windows\kafka-server-start.bat .\config\server.properties

Step4:
=====
Creating topics
===============
1. Now create a topic with name “userRequestKafka” and replication factor 1, as we have only one Kafka server running. If you have a cluster with more than 1 Kafka server running, you can increase the replication-factor accordingly which will increase the data availability and act like a fault-tolerant system.

2.Open a new command prompt in the location C:\softwares\kafka_2.11-2.0.0\bin\windows
3. Type following command and hit Enter:
 example:
 ========
 C:\softwares\kafka_2.11-2.0.0\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic userRequestKafka
 
 Step5:
 ======
 Creating a Producer and Consumer in Kafka
 ==========================================
 1. Open a new command prompt in the location  C:\softwares\kafka_2.11-2.0.0\bin\windows

2. To start a producer type the following command:
   Example:
   =======
    C:\softwares\kafka_2.11-2.0.0\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic userRequestKafka
    
    3. Again open a new command prompt in the same location as  C:\softwares\kafka_2.11-2.0.0\bin\windows
    
    4. Now start a consumer by typing the following command:
    
    Example:
    =======
    C:\softwares\kafka_2.11-2.0.0\bin\windows>kafka-console-consumer.bat -bootstrap-server localhost:2181 -topic userRequestKafka
    
Step6:
===========
After hiting micro service,http://localhost:5002/user-api/post-kafka-user

Check the messages in Apache Kafka.

Example:
===========

C:\softwares\kafka_2.11-2.0.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic userRequestKafka --from-beginning

example:
========
{"id":0,"email":"ankamma.java@gmail.com","name":"pallapu","password":"pallapu","username":"apallapuRaju","roleRequest":[{"id":0,"name":"admin"}]}
{"id":0,"email":"ankamma.java@gmail.com","name":"pallapu","password":"pallapu","username":"apallapuRaju","roleRequest":[{"id":0,"name":"admin"}]}
{"id":0,"email":"ankamma.java@gmail.com","name":"pallapu","password":"pallapu","username":"apallapuRaju","roleRequest":[{"id":0,"name":"admin"}]}
{"id":0,"email":"ankamma.java@gmail.com","name":"pallapu","password":"pallapu","username":"apallapuRaju","roleRequest":[{"id":0,"name":"admin"}]}





