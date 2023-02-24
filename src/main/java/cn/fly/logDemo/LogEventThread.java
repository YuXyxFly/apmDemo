package cn.fly.logDemo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

public class LogEventThread {

    private Disruptor<LogEvent> disruptor;

    Logger logger = LoggerFactory.getLogger(LogEventThread.class);

    // 初始化并启动disruptor
    public LogEventThread() {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        this.disruptor = new Disruptor<>(LogEvent::new, bufferSize, executor);

        // 创建10个消费者来处理同一个生产者发的消息(这10个消费者不重复消费消息)
        LogEventHandler[] consumers = new LogEventHandler[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new LogEventHandler();
        }
        disruptor.handleEventsWithWorkerPool(consumers);

        // Connect the handler
        //disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));
        //disruptor.handleEventsWith(new LogEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        logger.info("Disruptor: initialization completed");

        // Get the ring buffer from the Disruptor to be used for publishing.
        //RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        //ByteBuffer bb = ByteBuffer.allocate(8);
        //for (long l = 0; true; l++)
        //{
        //    bb.putLong(0, l);
        //    ringBuffer.publishEvent((event, sequence, buffer) -> event.setTraceId(String.valueOf(buffer.getLong(0))), bb);
        //}
    }

    public Disruptor<LogEvent> getDisruptor() {
        return disruptor;
    }
}
