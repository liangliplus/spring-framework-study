package indi.liangli.springframework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {


    /**
     * 所有返回类型，indi.liangli.springframework.aop 下面包及子包下面所有类的方法及任意参数
     * execution(* indi.liangli.springframework.aop..*.*(..))
     *
     */
    @Pointcut("execution(* indi.liangli.springframework.aop.UserService.save(indi.liangli.springframework.entity.User))")
    public void pointCut() {}


    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        //目标对象
        Object target = proceedingJoinPoint.getTarget();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object currentObject = proceedingJoinPoint.getThis();
        Object[] args = proceedingJoinPoint.getArgs();

        System.out.printf("@around 开始打印日志 ..... 实际对象：%s 目标对象：%s 方法名:%s 参数列表:%d %n",currentObject.getClass(), target.getClass(),methodName,args.length);
        try {
            return proceedingJoinPoint.proceed(args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("@around 执行完成 ");
        }
    }

    /**
     * 演示方法二
     * AfterReturning 必须方法正常返回才会执行
     * 注意annotation 括号中的值必须与形参中的命名一致，例如都是小写log
     */
    @AfterReturning(value = "@annotation(log)",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result, Log log) {
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        /**
         * spring 是如何获取到方法上的形参名称的？？
         * 通过asm 获取的LocalVariableTableParameterNameDiscoverer
         * 本质就是读取字节码文件（.class）
         */
        System.out.println(Arrays.toString(parameterNames));
        System.out.printf("@AfterReturning %s %s方法返回后执行: %s %n",joinPoint.getTarget(),joinPoint.getSignature().getName(),result);
    }




}
