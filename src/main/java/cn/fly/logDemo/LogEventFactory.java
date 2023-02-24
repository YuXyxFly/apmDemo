package cn.fly.logDemo;

import com.lmax.disruptor.EventFactory;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

public class LogEventFactory implements EventFactory<LogEvent> {

    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
