package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface UserService {
    User findbyID(String ID);
    //List<User> findbyInfo(String find_text, int limit_serch, int offset_serch, String order, String sort);
    //List<User> findbyInfoPage(String find_text, int limit_serch, int offset_serch, String order, String sort);
    int countfindbyInfo(String find_text);
    void addNew(User thisUser);
    void removeOne(String ID);
    void updateOne(User thisPerson);
    JSON findbyInfo(HttpServletRequest request);
}
