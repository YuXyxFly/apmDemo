package cn.fly.canal.disruptor;

import cn.fly.canal.model.CanalEventData;
import cn.fly.canal.model.CanalTransformData;
import cn.fly.logDemo.LogEventThread;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * @date 2023/3/29
 * @description
 */

@Component
public class CanalEventProducer {

    private final RingBuffer<CanalEventData> ringBuffer;

    public CanalEventProducer() {
        this.ringBuffer = new LogEventThread().getDisruptor().getRingBuffer();
    }

    private static final EventTranslatorOneArg<CanalEventData, CanalTransformData> TRANSLATOR = (fieldChange, sequence, data) -> fieldChange.generateFieldInfo(data);

    public void producer(CanalTransformData rowChange) {
        this.ringBuffer.publishEvent(TRANSLATOR, rowChange);
    }

}
