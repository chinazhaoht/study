package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:14
 */

public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
