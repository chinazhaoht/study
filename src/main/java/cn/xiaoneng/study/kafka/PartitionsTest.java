package cn.xiaoneng.study.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhaoht
 * @date 2016/4/1 10:31
 */
public class PartitionsTest {
    private final Producer<Integer,String> producer;
    private final String topic;

    private final Properties properties = new Properties();

    public PartitionsTest(String topic) {
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","10.22.10.139:9092");
        this.topic = topic;
        producer = new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static void main(String[] args) {
        new PartitionsTest("tesrt").sendMessage();

    }

    public void sendMessage(){

        for(int i = 1; i <= 5; i++){
            List messageList = new ArrayList<KeyedMessage<String,String>>();
            for(int j = 0; j <4; j++){
                messageList.add(new KeyedMessage<String,String>("topic2",j+"","The " + i + " message for key "+ j ));

            }
            producer.send(messageList);
        }
        producer.close();
    }
}
