package org.big.config;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.big.common.IdentityVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.lang.reflect.Method;

/**
 * Created by WangTianshan on 2017/9/28.
 */
@Aspect //声明这是一个切面。必须的！
@Component
public class AspectConfig {
    @Autowired
    private HttpServletRequest request;

//    @Pointcut("@annotation(org.big.config.Action)")
//    public void annotationPointCut(){};

//    @Before("annotationPointCut()")
//    public void before(JoinPoint joinPoint){
//        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
//        Method method=signature.getMethod();
//        Action action=method.getAnnotation(Action.class);
//        System.out.println("=========注解是拦截Before=======");
//        System.out.println("名字是："+action.name());
//        System.out.println("年龄是："+action.age());
//    }

//    //通知和切入点的混合写法；
//    //第一个 * 号表示任意返回类型，第二个 * 号表示所有方法
//    @Before("execution(* org.big.common.Children.*(..)) && args(name)")
//    public void before(JoinPoint joinPoint,String name)throws Throwable{
//        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
//        Method method=signature.getMethod();
//        Action action=method.getAnnotation(Action.class);
//        System.out.println("=========注解是拦截Before=======");
//        System.out.println("年龄是："+action.age());
//        System.out.println("name是："+name);
//        System.out.println("method是："+method.getName());
//        if(name.equals("wts")){
//            System.out.println("通过！");
//        }
//        else{
//            System.out.println("不通过！");
//            throw new Exception("不通过");
//        }
//
//    }

//    @Around("execution(* org.big.common.Children.*(..)) && args(name)")
//    public Object before(JoinPoint joinPoint,String name){
//        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
//        Method method=signature.getMethod();
//        System.out.println("name是："+name);
//        System.out.println("method是："+method.getName());
//        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
//        if(name.equals("wts1")){//你的校验成功执行方法,失败方法就不用执行了
//            try {
//                return pjp.proceed();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//            return null;
//        }else{
//            //可以返回你失败的信息也可以直接抛出校验失败的异常
//            return null;
//        }
//    }

    //删除权限判断
    @Around("execution(* org.big.service.*.remove*(..)) && args(objectId)")
    public Object canReove(JoinPoint joinPoint,String objectId){
        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
        IdentityVote thisIdentityVote=new IdentityVote();
        String targetName = pjp.getSignature().getDeclaringTypeName();
        //System.out.println(pjp.getTarget().getClass().getName());
        //targetName=targetName.substring(16);
        targetName=targetName.replace("org.big.service.","");
        if(thisIdentityVote.hasAuthority(targetName,objectId)){
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                request.getSession().setAttribute("operationError","authority");
                throwable.printStackTrace();
            }
            return false;
        }else{
            request.getSession().setAttribute("operationError","authority");
            return false;
        }
    }
}