package cn.fly.logDemo;


import cn.fly.logDemo.infoResolver.TraceIdHandler;
import cn.fly.logDemo.utils.DateUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.MDC;

import java.util.Date;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@Data
@Accessors(chain = true)
public class LogEvent {

    // traceID
    private String traceId;

    // target table name
    private String tableName;

    // info Class
    private Class clazz;

    // info with Json
    private String info;

    // timestamp
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    private Date timestamp;

    private String effectFlag;

    public void generateInfo(Object req){
        try {
            this.traceId = MDC.get(TraceIdHandler.TRACE_ID);
            this.timestamp = new Date();
            this.clazz = req.getClass();
            this.info = new ObjectMapper().writeValueAsString(req);
            if (req.getClass().getAnnotation(TableName.class) != null && req.getClass().getAnnotation(TableName.class).value() != null) {
                this.tableName = req.getClass().getAnnotation(TableName.class).value();
            }else this.tableName = req.getClass().getName().substring(req.getClass().getName().lastIndexOf(".") + 1);
            throw new Exception("tested throws");
        } catch (Exception e) {
            this.effectFlag = "0";
        }
    }
}
