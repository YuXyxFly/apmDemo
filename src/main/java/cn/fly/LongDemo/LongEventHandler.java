package cn.fly.LongDemo;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;

/**
 * @author fly
 * @date 2022/12/29
 * @description
 */

public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * Called when a publisher has published an event to the {@link RingBuffer}
     *
     * @param event      published to the {@link RingBuffer}
     * @param sequence   of the event being processed
     * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
     * @throws Exception if the EventHandler would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event: " + event);
    }
}
