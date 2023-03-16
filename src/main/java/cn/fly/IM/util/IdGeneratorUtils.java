package cn.fly.IM.util;

import cn.fly.config.id.WebSocketId;

import java.lang.reflect.InvocationTargetException;

/**
 * @author fly
 * @date 2023/3/14
 * @description
 */

public class IdGeneratorUtils {

    // 获取WebSocketId
    public static WebSocketId idGetter(String userName) {
        return WebSocketId.WebSocketIdBuilder.builder(userName);
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
