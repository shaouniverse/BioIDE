package org.big.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by WangTianshan on 2017/9/28.
 */
@Component
@Aspect //声明这是一个切面。必须的！
public class AspectConfig {

    //通知和切入点的混合写法；
    //第一个 * 号表示任意返回类型，第二个 * 号表示Person的所有方法
//    @Before("execution(* org.big.common.IdentityVote.*(..))")
//    public void showTime1(){
//        System.out.println("BeforeCurrentTime = " + System.currentTimeMillis());
//    }
//    @Before("execution(* org.big.common.Children.*(..))")
//    public void beforeTest() {
//        System.out.println("----------");
//        //System.out.println("userId:" + userId);
//    }

    @Before("execution(* org.big.common.Children.*(..)) && args(userId,..)")
        public void beforeTest(int userId) {
          System.out.println("----------");
            //System.out.println("userId:" + userId);
        }
//    @Before("execution(* org.big.common.Children.*(..))")
//    public void adminPrivilegeCheck()
//            throws Throwable {
//                throw new Exception("no privilege");
//    }
//    @After("execution(* org.big.common.IdentityVote.*(..))")
//    public void showTime2(){
//        System.out.println("AfterCurrentTime = " + System.currentTimeMillis());
//    }
}