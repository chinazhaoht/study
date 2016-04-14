package cn.xiaoneng.study.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:15
 */

public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Event: "+longEvent);

    }
}
