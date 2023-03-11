package cn.fly.logDemo.intercepter;

import cn.fly.logDemo.record.LogRecord;
import cn.fly.logDemo.record.constants.HttpResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fly
 * @date 2023/3/2
 * @description
 */

public class RecordInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        // record the HttpInfo
        HttpResult.HttpResultBuilder.getByCode(response.getStatus());
        String requestURI = request.getRequestURI();
        int status = response.getStatus();
    }
}
