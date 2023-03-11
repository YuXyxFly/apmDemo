package cn.fly.logDemo.aspect;

import cn.fly.logDemo.config.SpringUtils;
import cn.fly.logDemo.infoResolver.LogEventProducer;
import cn.fly.logDemo.infoResolver.RequestInfoHandler;
import cn.fly.logDemo.infoResolver.TraceIdHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author fly
 * @date 2023/2/22
 * @description
 *
 *
 *
 * == doAround.before ==
 * == doBefore ==
 *  proceedingJoinPoint.proceed()
 * == doAfter
 * == doAround.after ==
 */

@Aspect
@Component
public class LogAspect {

    @Resource
    HttpServletRequest request;

    @Pointcut( "execution( public * cn.fly.testController.*Controller.*(..))")
    public void doPointcut() {}


    //@Before("doPointcut()")
    //public void doBefore() {
    //    System.out.println("== doBefore ==");
    //}

    //@After("doPointcut()")
    //public void doAfter() {
    //    System.out.println("== doAfter");
    //}

    /**
     * 对应所有被拦截方法的返回值类型, 不能为void
     * 处理request
     * @return
     */
    @Around("doPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        TraceIdHandler.resolveTranceId();
        if (HttpMethod.PUT.matches(request.getMethod()) || HttpMethod.POST.matches(request.getMethod())) {
            SpringUtils.getBean(LogEventProducer.class).producer(proceedingJoinPoint);
        }
        //RequestInfoHandler.resolve(request);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
