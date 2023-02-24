package cn.fly.logDemo.infoResolver;

import cn.hutool.core.util.IdUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;

/**
 * @author fly
 * @date 2023/2/22
 * @description
 */

public class TraceIdHandler {

    public static final String TRACE_ID = "traceId";

    public static void resolveTranceId() {
        if (Strings.isBlank(MDC.get(TRACE_ID))) {
            String traceId = IdUtil.getSnowflakeNextIdStr();
            MDC.put(TRACE_ID, traceId);
        }
    }
}
