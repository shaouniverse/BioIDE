package org.big.common;

import org.big.config.Action;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



/**
 * Created by WangTianshan on 2017/9/28.
 */
//@Component
//@Qualifier("children")
@Service
public class Children{

    public String thisName = "children";


    @Action(age=2)
    public void run(String name) {
        System.out.println("runnnnnnnnnnnnnnnnnnn: "+name);
    }
}