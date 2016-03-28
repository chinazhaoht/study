package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhaoht
 * @date 2016/3/25 14:26
 */

public class TestMain {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,bufferSize,executor);
        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();


        for(long l = 0;true;l++){
            long sequence = ringBuffer.next();
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.set(l);
            ringBuffer.publish(sequence);
        }
    }
}
