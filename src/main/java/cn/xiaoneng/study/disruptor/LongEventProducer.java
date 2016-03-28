package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:16
 */

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){

        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try{
            LongEvent event = ringBuffer.get(sequence);
            event.set(bb.getLong(0));
          //  System.out.println("data"+bb.getLong(0));
        }
        finally {
            ringBuffer.publish(sequence);
        }
    }
}
