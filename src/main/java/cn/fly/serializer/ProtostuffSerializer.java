//package cn.fly.serializer;
//
//import io.protostuff.LinkedBuffer;
//import io.protostuff.ProtostuffIOUtil;
//import io.protostuff.Schema;
//import io.protostuff.runtime.RuntimeSchema;
//import org.apache.kafka.common.serialization.Serializer;
//
//import java.util.Map;
//
///**
// * @author fly
// * @date 2023/3/17
// * @description
// */
//
//public class ProtostuffSerializer <T> implements Serializer<T> {
//
//
//    private static final LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.MIN_BUFFER_SIZE);
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public byte[] serialize(String topic, T data) {
//        Class<T> dataClass = (Class<T>) data.getClass();
//        Schema<T> schema = RuntimeSchema.getSchema(dataClass);
//        byte[] byteData;
//        try {
//            byteData = ProtostuffIOUtil.toByteArray(data, schema, BUFFER);
//        } finally {
//            BUFFER.clear();
//        }
//        return byteData;
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//
//}
