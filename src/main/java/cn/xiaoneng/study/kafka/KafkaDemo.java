package cn.xiaoneng.study.kafka;

/**
 * @author zhaoht
 * @date 2016/3/31 19:06
 */
public class KafkaDemo {

    public static void main(String[] args) {
        KafkaProducer producer = new KafkaProducer("test");
        producer.start();
        KafkaConsumer consumer = new KafkaConsumer("test");
        consumer.start();

    }
}
