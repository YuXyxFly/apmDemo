package cn.fly.canal.baseInfo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/4/7
 * @description
 */

@Data
@Accessors(chain = true)
public class User {

    public Long userId;

    public Long expectedSalary;

    private int workExperience;

    private int degree;

}
