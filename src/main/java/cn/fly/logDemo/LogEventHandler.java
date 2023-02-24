package cn.fly.logDemo;

import cn.fly.logDemo.infoResolver.model.logTable.TableConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import org.apache.logging.log4j.util.Strings;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

public class LogEventHandler implements WorkHandler<LogEvent> {

    Logger logger = LoggerFactory.getLogger(LogEventHandler.class);

    ///**
    // * Called when a publisher has published an event to the {@link RingBuffer}
    // *
    // * @param event      published to the {@link RingBuffer}
    // * @param sequence   of the event being processed
    // * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
    // * @throws Exception if the EventHandler would like the exception handled further up the chain.
    // */
    //@Override
    //public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
    //    System.out.println("已落业务日志库" + event.getTraceId());
    //}


    /**
     * Callback to indicate a unit of work needs to be processed.
     *
     * @param event published to the {@link RingBuffer}
     * @throws Exception if the {@link WorkHandler} would like the exception handled further up the chain.
     * 1. validate the effect
     * 2. try locate into the database
     */
    @Override
    public void onEvent(LogEvent event) throws Exception {
        if (Strings.isNotEmpty(event.getEffectFlag())) {
            logger.error("TraceId: " + event.getTraceId() + " generate dateBase info failed at " + event.getTimestamp());
            return;
        }
        Object log = new ObjectMapper().readValue(event.getInfo(), event.getClazz());
        //Todo generate LocatedInfo -- 校验非空 -- 组装业务数据表 -- 落库
        // ----------- canal -- 日志处理 -- flume -- 外部平台展示


        logger.info("Already located into the database --- " + new ObjectMapper().writeValueAsString(event));
    }

}
