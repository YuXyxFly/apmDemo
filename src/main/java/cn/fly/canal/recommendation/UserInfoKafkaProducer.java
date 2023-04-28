package cn.fly.canal.recommendation;

import cn.fly.canal.baseInfo.User;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

//@Component
public class UserInfoKafkaProducer {

    private static final String USER_TOPIC = "userInfo";

    //@Resource
    //KafkaTemplate<String, String> kafkaTemplate;

    public void userProducer(CanalEntry.RowChange rowChange) throws JsonProcessingException {
        User user = new User();
        user.setUserId(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(0).getValue()))
                .setExpectedSalary(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(1).getValue()))
                .setWorkExperience(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(2).getValue()))
                .setDegree(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(3).getValue()));
        System.out.println(user);
        //kafkaTemplate.send(USER_TOPIC, new ObjectMapper().writeValueAsString(user));

    }

}
