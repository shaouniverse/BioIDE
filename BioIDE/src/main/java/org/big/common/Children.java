package org.big.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by WangTianshan on 2017/9/28.
 */
@Component
@Qualifier("children")
public class Children{

    private String classname = "children";

    public void hello(String name) {
        System.out.println("hello: "+name);
    }

}