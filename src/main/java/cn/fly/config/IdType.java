package cn.fly.config;

import cn.fly.IM.util.IdGeneratorUtils;

/**
 * @author fly
 * @date 2023/3/14
 * @description
 */

public enum IdType {

    WEBSOCKET(WebSocketId.class, "websocketID"){
        @Override
        public String getter(String userName) {
            return IdGeneratorUtils.idGetter(userName);
        }

        @Override
        public Object resolve(String value) {
            return IdGeneratorUtils.resolve(value, WebSocketId.class);
        }
    };

    IdType(Class<WebSocketId> webSocketIdClass, String msg) {
    }

    public abstract String getter(String userName);

    public abstract Object resolve(String value);
}
