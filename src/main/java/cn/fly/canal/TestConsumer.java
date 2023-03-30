package cn.fly.canal;

import cn.fly.canal.disruptor.CanalEventProducer;
import cn.fly.canal.model.CanalTransformData;
import cn.fly.config.CanalMysqlConfig;
import cn.fly.logDemo.LogEvent;
import cn.fly.logDemo.LogEventThread;
import cn.fly.logDemo.config.SpringUtils;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author fly
 * @date 2023/3/27
 * @description
 */

@Component
public class TestConsumer {
    @Resource
    CanalMysqlConfig canalMysqlConfig;

    private final static int BATCH_SIZE = 10000;

    Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    @Async("MysqlConsumer")
    @Bean
    public CanalConnector startConsumer() {
        while(true) {
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalMysqlConfig.getHost(), canalMysqlConfig.getPort()), "example", "", "");
            try {
                connector.connect();
                logger.info("init canal connection successfully, " + canalMysqlConfig.getHost() + ":" + canalMysqlConfig.getPort());
                //订阅数据库表,全部表q
                connector.subscribe( ".*\\..*");
                //回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
                connector.rollback();
                while(true) {
                    // 获取指定数量的数据
                    Message message = connector.getWithoutAck(BATCH_SIZE);
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                    } else {
                        handleDATAChange(message.getEntries());
                    }
                    // 提交确认
                    connector.ack(batchId);
                }
            } catch (CanalClientException | InvalidProtocolBufferException e ) {
                e.printStackTrace();
                logger.error("成功断开监测连接!尝试重连");
            } finally {
                connector.disconnect();
                //防止频繁访问数据库链接: 线程睡眠 10秒
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打印canal server解析binlog获得的实体类信息
     */
    private void handleDATAChange(List<CanalEntry.Entry> entrys) throws InvalidProtocolBufferException {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }
            //RowChange对象，包含了一行数据变化的所有特征
            CanalEntry.RowChange rowChage;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),e);
            }
            CanalEntry.EventType eventType = rowChage.getEventType();
            logger.info("Canal监测到更新:【{}】", entry.getHeader().getTableName());
            switch (eventType) {
                /**
                 * 删除操作
                 */
                case DELETE:
                    break;
                /**
                 * 添加操作
                 */
                case INSERT:
                case UPDATE:
                    SpringUtils.getBean(CanalEventProducer.class).producer(new CanalTransformData(rowChage, entry.getHeader().getTableName()));
                    break;
                default:
                    break;
            }

        }
    }



}
