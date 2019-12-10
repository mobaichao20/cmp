package io.cmp.modules.specialList.aspect;

import com.google.gson.Gson;
import io.cmp.modules.specialList.entity.SpecialListLogEntity;
import io.cmp.modules.specialList.service.SpecialListLogService;
import io.cmp.modules.specialList.utils.CreateGuid;
import io.cmp.modules.sys.controller.AbstractController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面类
 */
@Aspect
@Component
public class Log  extends AbstractController {
    @Autowired
    private SpecialListLogService specialListLogService;



    @Pointcut("@annotation(io.cmp.modules.specialList.config.Log)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        //保存日志
        saveSpecialListLog(point);
        return result;
    }

    private void saveSpecialListLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SpecialListLogEntity specialListLog = new SpecialListLogEntity();
        io.cmp.modules.specialList.config.Log log = method.getAnnotation(io.cmp.modules.specialList.config.Log.class);
        if(specialListLog != null){
            //注解上的描述
            specialListLog.setOperation(log.value());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        specialListLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = new Gson().toJson(args);
            specialListLog.setRequestParam(params);
        }catch (Exception e){
        }

        specialListLog.setRequestTime(new Date());//请求时间
        specialListLog.setCreateTime(new Date());//创建时间
        specialListLog.setUsername(getUser().getUsername());
        specialListLog.setLogId(CreateGuid.create("log"));
        //保存系统日志
        specialListLogService.save(specialListLog);
    }
}
