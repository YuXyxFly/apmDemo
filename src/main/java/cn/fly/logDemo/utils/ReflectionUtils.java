package cn.fly.logDemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

public class ReflectionUtils {

    static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    public static Map<String, Object> getAllFieldNotNull(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    map.put(field.getName(), value);
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("getAllFieldNotNull err {}",e);
        }
        return map;
    }


}
