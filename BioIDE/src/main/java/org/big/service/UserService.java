package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface UserService {
    User findbyID(String ID);
    void saveOne(User thisUser);
    void removeOne(String ID);
    JSON findbyInfo(HttpServletRequest request);
}
