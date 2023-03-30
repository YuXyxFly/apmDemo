package cn.fly.logDemo.infoResolver;

import cn.fly.logDemo.LogEvent;
import cn.fly.logDemo.LogEventThread;
import cn.fly.logDemo.infoResolver.model.logTable.TranslatorCollect;
import cn.fly.logDemo.utils.BasicClassesChecker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * @date 2023/2/22
 * @description
 */

@Component
public class LogEventProducer {

    private RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer() {
        //this.ringBuffer = new LogEventThread().getDisruptor().getRingBuffer();\
    }

    private static final EventTranslatorOneArg<LogEvent, TranslatorCollect> TRANSLATOR = (event, sequence, collect) -> event.generateInfo(collect);

    public boolean producer(ProceedingJoinPoint proceedingJoinPoint) {
        // 参数值
        Object[] args = proceedingJoinPoint.getArgs();
        Object req = null;
        Object id = 0L;
        //Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!BasicClassesChecker.check(args[k].getClass())){
                req = args[k];
            }else id = args[k];
            //classes[k] = args[k].getClass();
        }
        //ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        //// 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        //Method method = Class.forName(classType).getMethod(methodName, classes);
        //// 参数名
        //String[] parameterNames = pnd.getParameterNames(method);
        //// 通过map封装参数和参数值
        //HashMap<Object, String> paramMap = new HashMap();
        //for (int i = 0; i < parameterNames.length; i++) {
        //    paramMap.put(args[i], parameterNames[i]);
        //}
        this.ringBuffer.publishEvent(TRANSLATOR, new TranslatorCollect(req, (Long)id));
        return true;
    }


}
