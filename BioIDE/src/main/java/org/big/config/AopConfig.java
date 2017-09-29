package org.big.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by WangTianshan on 2017/9/28.
 */
@Configuration
@ComponentScan("org.big.common")
@EnableAspectJAutoProxy
public class AopConfig {


}