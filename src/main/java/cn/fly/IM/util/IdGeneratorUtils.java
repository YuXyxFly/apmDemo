package cn.fly.IM.util;

import cn.fly.config.WebSocketId;

import java.lang.reflect.InvocationTargetException;

/**
 * @author fly
 * @date 2023/3/14
 * @description
 */

public class IdGeneratorUtils {

    // 获取WebSocketId
    public static String idGetter(String userName) {
        return WebSocketId.WebSocketIdBuilder.builder(userName).getEtcdId();
    }

    // 解析WebSocketId
    @SuppressWarnings("unchecked")
    public static <T> T resolve(String value, Class<T> clazz) {
        try {
            return (T) clazz.getMethod("resolve").invoke(value);
        } catch (IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException ignored) {
            return null;
        }
    }

}
