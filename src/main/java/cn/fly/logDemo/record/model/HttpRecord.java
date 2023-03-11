package cn.fly.logDemo.record.model;


import cn.fly.logDemo.record.constants.HttpResult;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author fly
 * @date 2023/3/2
 * @description
 */

@Data
@Accessors(chain = true)
public class HttpRecord {

    private int total;

    private int success;

    public void reqCountResolve(HttpResult status) {
        this.total += 1;
        this.success += status.getCode();
    }

}
