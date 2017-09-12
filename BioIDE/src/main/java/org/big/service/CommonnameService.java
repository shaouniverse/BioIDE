package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.Commonname;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface CommonnameService {
    Commonname findbyID(String ID);
    void removeOne(String ID);
    void saveOne(Commonname thisPerson);
    JSON findbyInfo(HttpServletRequest request);
}
