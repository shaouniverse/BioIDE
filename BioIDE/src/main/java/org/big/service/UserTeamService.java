package org.big.service;


import com.alibaba.fastjson.JSON;
import org.big.entity.Team;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface UserTeamService {
    void saveOne(String userId,String teamId);
}
