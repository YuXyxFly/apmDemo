package cn.fly.logDemo;


import ch.qos.logback.classic.db.names.TableName;
import cn.fly.logDemo.infoResolver.TraceIdHandler;
import cn.fly.logDemo.infoResolver.model.logTable.TranslatorCollect;
import cn.fly.logDemo.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.MDC;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@Data
@Accessors(chain = true)
public class LogEvent {


    // 主键ID
    private Long infoId;

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

    public void generateInfo(TranslatorCollect collect) {
        try {
            Object req = collect.getReq();
            this.infoId = collect.getInfoId();
            this.traceId = MDC.get(TraceIdHandler.TRACE_ID);
            this.timestamp = new Date();
            this.clazz = req.getClass();
            this.info = new ObjectMapper().writeValueAsString(req);
            if (req.getClass().getAnnotation(Table.class) != null && req.getClass().getAnnotation(Table.class).name() != null) {
                this.tableName = req.getClass().getAnnotation(Table.class).name();
            } else this.tableName = req.getClass().getName().substring(req.getClass().getName().lastIndexOf(".") + 1);
            //throw new Exception("tested throws"); 测试使用
        } catch (Exception e) {
            this.effectFlag = "0";
        }
    }
}
