package cn.xiaoneng.study.kafka;

import kafka.consumer.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.*;

/**
 * @author zhaoht
 * @date 2016/3/31 19:12
 */
public class KafkaConsumer extends Thread {

    private final ConsumerConnector consumer;
    private final String topic;

    private org.apache.kafka.clients.consumer.KafkaConsumer kafkaConsumer;

    private Properties properties;

    public KafkaConsumer(String topic){
        consumer = (ConsumerConnector) Consumer.createJavaConsumerConnector(
                createConsumerConfig()
        );
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","");
        properties.put("group.id","");
        properties.put("zookeeper.session.timeout.ms","40000");
        properties.put("auto.commit.interval.ms","1000") ;
        return new ConsumerConfig(properties);
    }

    public void run(){
        Map<String,Integer> topicCountMap = new HashMap<String,Integer>();
        topicCountMap.put(topic,new Integer(1));
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap =
                (Map<String, List<KafkaStream<byte[], byte[]>>>) consumer.createMessageStreams((scala.collection.Map<String, Object>) topicCountMap);

        KafkaStream<byte[],byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[],byte[]> it = stream.iterator();
        while(it.hasNext()){
            System.out.println("receive: " + new String(it.next().message()));
            try{
                sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void poll(){
        kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords record = kafkaConsumer.poll(10000);
        kafkaConsumer.close();

    }


}
