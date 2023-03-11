package cn.fly.logDemo;

import cn.fly.logDemo.config.SpringUtils;
import cn.fly.logDemo.infoResolver.dao.MysqlColumnsDao;
import cn.fly.logDemo.infoResolver.model.logTable.LogCollectedInfo;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import cn.fly.logDemo.utils.ReflectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     *                   1. validate the effect
     *                   2. try locate into the database
     */
    @Override
    public void onEvent(LogEvent event) throws Exception {
        try {

            if (Strings.isNotEmpty(event.getEffectFlag())) {
                logger.error("TraceId: " + event.getTraceId() + " generate dateBase info failed at " + event.getTimestamp());
                return;
            }
            Object logRawInfo = new ObjectMapper().readValue(event.getInfo(), event.getClazz());
            //Todo generate LocatedInfo -- 校验非空 -- 组装业务数据表 -- 落库
            // ----------- canal -- 日志处理 -- flume -- 外部平台展示
            Map<String, Object> allFieldNotNull = ReflectionUtils.getAllFieldNotNull(logRawInfo);
            List<String> list = new ArrayList<>();
            allFieldNotNull.keySet().forEach( item -> {
                list.add(item);
            });
            Map<String, List<MysqlColumns>> fieldNotNUllTableInfoCollected = SpringUtils.getBean(MysqlColumnsDao.class).showTableInfo(event.getTableName(), list)
                    .parallelStream().collect(Collectors.groupingBy(MysqlColumns::getField));
            LogCollectedInfo logCollectedInfo = new LogCollectedInfo().generateInfo(fieldNotNUllTableInfoCollected, event, allFieldNotNull);
            try {
                SpringUtils.getBean(TransactionTemplate.class).execute(status -> {
                    //collectionInfos.forEach(item -> {
                    //    SpringUtils.getBean(TableConfigMapper.class);
                    //    SpringUtils.getBean(TableFieldConfigMapper.class);
                    //    SpringUtils.getBean(TableFieldValueMapper.class);
                    //});
                    try {
                        logger.info("Already located into the database --- " + new ObjectMapper().writeValueAsString(logCollectedInfo));
                    } catch (JsonProcessingException ignored) {
                    }
                    return true;
                });
            } catch (TransactionException e) {
                logger.error(new ObjectMapper().writeValueAsString(event) + "落库失败!");
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

}
