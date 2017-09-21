package org.big.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.Random;

/**
 * Created by Tianshan on 17/5/26.
 */
@Configuration
public class CaptchaConfig {

    @Bean(name="captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
//        Random r = new Random();
//        String randomColor=""+r.nextInt(255)+","+r.nextInt(255)+","+r.nextInt(255);
//        System.out.println("randomColor="+randomColor);
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "210,214,222");
        properties.setProperty("kaptcha.textproducer.font.color", "199,21,133");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "42");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}