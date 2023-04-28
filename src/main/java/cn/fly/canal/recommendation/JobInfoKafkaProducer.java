package cn.fly.canal.recommendation;

import cn.fly.canal.baseInfo.Job;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import kafka.Kafka;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

//@Component
public class JobInfoKafkaProducer {

    private static final String JOB_TOPIC = "jobInfo";
    //
    //@Resource
    //KafkaTemplate<String, String> kafkaTemplate;


    public void jobProducer(CanalEntry.RowChange rowChange) throws JsonProcessingException {
        Job job = new Job();
        job.setJobId(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(0).getValue()))
                .setSalary(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(1).getValue()))
                .setWorkExperience(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(2).getValue()))
                .setDegree(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(3).getValue()));
        System.out.println(job);
        //kafkaTemplate.send(JOB_TOPIC, new ObjectMapper().writeValueAsString(job));
    }
}
