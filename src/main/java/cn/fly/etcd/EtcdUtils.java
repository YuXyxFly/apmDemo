package cn.fly.etcd;

import io.etcd.jetcd.ByteSequence;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author fly
 * @date 2023/3/11
 * @description
 */

public class EtcdUtils {

    /**
     * 将字符串转为客户端所需的ByteSequence实例
     * @param val
     * @return
     */
    public static ByteSequence bytesOf(String val) {
        return ByteSequence.from(val, UTF_8);
    }

}
