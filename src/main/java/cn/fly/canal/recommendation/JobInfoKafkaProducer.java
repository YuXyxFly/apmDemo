package cn.fly.canal.recommendation;

import cn.fly.canal.baseInfo.Job;
import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

@Component
public class JobInfoKafkaProducer {

    public void jobProducer(CanalEntry.RowChange rowChange) {
        Job job = new Job();
        job.setJobId(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(0).getValue()))
                .setSalary(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(1).getValue()))
                .setWorkExperience(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(2).getValue()))
                .setDegree(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(3).getValue()));
        System.out.println(job);
    }
}
