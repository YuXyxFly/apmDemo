package cn.fly.canal.recommendation;

import cn.fly.canal.baseInfo.User;
import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

@Component
public class UserInfoKafkaProducer {

    public void userProducer(CanalEntry.RowChange rowChange) {
        User user = new User();
        user.setUserId(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(0).getValue()))
                .setExpectedSalary(Long.valueOf(rowChange.getRowDatas(0).getAfterColumns(1).getValue()))
                .setWorkExperience(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(2).getValue()))
                .setDegree(Integer.parseInt(rowChange.getRowDatas(0).getAfterColumns(3).getValue()));
        System.out.println(user);
    }

}
