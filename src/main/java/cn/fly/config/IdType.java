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
        public Object getter(String userName) {
            return IdGeneratorUtils.idGetter(userName);
        }
    };

    IdType(Class<WebSocketId> webSocketIdClass, String msg) {
    }

    public abstract Object getter(String userName);
}
