package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.Message;
import org.big.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface MessageService {
    Message findbyID(String ID);
    void sendOne(Message thisMessage);
    void removeOne(String ID);
    JSON findbyInfo(HttpServletRequest request);
    JSON findInfoByAddressee(HttpServletRequest request);
    JSON findInfoBySender(HttpServletRequest request);
}
