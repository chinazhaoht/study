package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:22
 */

public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {

        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,bufferSize,executor);
        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l = 0; true; l++){
            bb.putLong(0,l);
            producer.onData(bb);
            System.out.println(ringBuffer.getBufferSize() - ringBuffer.remainingCapacity()+":"+ringBuffer.remainingCapacity());
           // Thread.sleep(1000);
        }
    }
}
