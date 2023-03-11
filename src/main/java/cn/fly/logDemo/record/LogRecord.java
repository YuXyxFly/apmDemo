package cn.fly.logDemo.record;

import cn.fly.logDemo.record.constants.HttpResult;
import cn.fly.logDemo.record.model.HttpRecord;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fly
 * @date 2023/3/1
 * @description
 */

@Component
public class LogRecord {

    Map<String, HttpRecord> HttpRecords = new ConcurrentHashMap<>();

    public boolean isKeyContains(String key) {
        return HttpRecords.containsKey(key);
    }

    public boolean setKey(String key, HttpResult result) {
        try {
            HttpRecords.get(key).reqCountResolve(result);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, HttpRecord> getHttpRecords() {
        return HttpRecords;
    }
}
