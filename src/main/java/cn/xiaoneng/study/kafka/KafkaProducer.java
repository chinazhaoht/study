package cn.xiaoneng.study.kafka;




import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author zhaoht
 * @date 2016/3/31 19:07
 */
public class KafkaProducer extends Thread {

    private final Producer<Integer,String> producer;
    private final String topic;

    private final Properties properties = new Properties();

    public KafkaProducer(String topic) {
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list","10.22.10.139:9092");
        this.topic = topic;
        producer = new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public void run(){
        int messageNo = 1;
        while(true){
            String messageStr = new String("Message_"+messageNo);
            System.out.println("send:"+messageStr);
            producer.send(new KeyedMessage<Integer, String>(topic,messageStr));
            messageNo++;
            try{
                sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
