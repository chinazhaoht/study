package cn.xiaoneng.study.disruptor;

/**
 * @author:zhaoht
 * @date:2016/3/23 10:13
 */

public class LongEvent {

    private long value;

    public void set(long value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
