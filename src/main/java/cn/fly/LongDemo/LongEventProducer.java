package cn.fly.LongDemo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author fly
 * @date 2022/12/29
 * @description
 */

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);

            event.set(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
