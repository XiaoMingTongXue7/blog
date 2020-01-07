package cn.xudam.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 鸣
 * 2020/1/3 20:48
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* cn.xudam.blog.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        RequestLog requestLog = new RequestLog(
            request.getRequestURI().toString(),
            request.getRemoteAddr(),
            joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()",
            joinPoint.getArgs()
        );

        logger.info("Request : {}", requestLog);
    }

    @After("log()")
    public void doAfter(){
//        logger.info("---------doAfter---------");
    }

    @AfterReturning(pointcut = "log()", returning = "result")
    public void doAfterReturn(Object result){
        logger.info("Result : {}", result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog() {
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    }

}
