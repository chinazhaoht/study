package cn.xiaoneng.study.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;
import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.springframework.security.authentication.jaas.event.JaasAuthenticationSuccessEvent;

/**
 * @author zhaoht
 * @date 2016/4/1 10:28
 */
public class JsaonPartitioner implements Partitioner {

    public JsaonPartitioner(VerifiableProperties verifiableProperties){

    }
    @Override
    public int partition(Object key, int numPartitions) {
        try{
            int partitionNum = Integer.parseInt((String)key);
            return Math.abs(Integer.parseInt((String)key) % numPartitions);
        }catch (Exception e){
           return Math.abs(key.hashCode() % numPartitions);
        }
    }

}
