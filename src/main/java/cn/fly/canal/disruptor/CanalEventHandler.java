package cn.fly.canal.disruptor;

import cn.fly.canal.model.CanalEventData;
import cn.fly.canal.recommendation.RecommendationType;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fly
 * @date 2023/3/29
 * @description
 */

public class CanalEventHandler implements WorkHandler<CanalEventData> {

    Logger logger = LoggerFactory.getLogger(CanalEventHandler.class);

    /**
     * Callback to indicate a unit of work needs to be processed.
     *
     * @param event published to the {@link RingBuffer}
     * @throws Exception if the {@link WorkHandler} would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(CanalEventData event) throws Exception {
        // canal -- disruptor.consumer -- kafka -- flink -- ES
        RecommendationType.RecommendationTypeBuilder.builder(event.getTableSchema()).recommendationKafkaProducer(event.getRowInfo());
        logger.info("以消费" + event.getTableSchema());
    }
}
