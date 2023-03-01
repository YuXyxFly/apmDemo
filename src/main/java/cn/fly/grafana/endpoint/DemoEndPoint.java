package cn.fly.grafana.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fly
 * @date 2023/3/1
 * @description
 */

@Component
@Endpoint(id = "demo")
public class DemoEndPoint {

    @ReadOperation
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        result.put("作者", "yudaoyuanma");
        result.put("秃头", "true");
        return result;
    }

}
