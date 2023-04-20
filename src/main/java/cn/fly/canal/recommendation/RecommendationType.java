package cn.fly.canal.recommendation;

import cn.fly.logDemo.config.SpringUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

public enum RecommendationType {

    User("UserInfo"){
        @Override
        public void recommendationKafkaProducer(CanalEntry.RowChange rowChange) {
            try {
                SpringUtils.getBean(UserInfoKafkaProducer.class).userProducer(rowChange);
            } catch (JsonProcessingException ignore) {
            }
        }
    },

    Job("JobInfo"){
        @Override
        public void recommendationKafkaProducer(CanalEntry.RowChange rowChange) {
            try {
                SpringUtils.getBean(JobInfoKafkaProducer.class).jobProducer(rowChange);
            } catch (JsonProcessingException ignore) {
            }
        }
    },

    Another(null){
        @Override
        public void recommendationKafkaProducer(CanalEntry.RowChange rowChange) {
            return;
        }
    };

    private String tableName;

    RecommendationType(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public static class RecommendationTypeBuilder{

        public static RecommendationType builder(String tableSchema){
            switch(tableSchema){
                case "UserInfo":
                    return RecommendationType.User;
                case "JobInfo":
                    return RecommendationType.Job;
                default:
                    return RecommendationType.Another;

            }
        }
    }

    public abstract void recommendationKafkaProducer(CanalEntry.RowChange rowChange);

}
