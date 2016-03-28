package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:19
 */

public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;


    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer>TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                public void translateTo(LongEvent longEvent, long l, ByteBuffer byteBuffer) {
                    longEvent.set(byteBuffer.getLong(0));
                }
            };
    public void onData(ByteBuffer bb){
        ringBuffer.publishEvent(TRANSLATOR,bb);
    }
}
