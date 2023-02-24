package cn.fly.LongDemo;

import com.lmax.disruptor.EventFactory;

/**
 * @author fly
 * @date 2022/12/29
 * @description
 */

public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
