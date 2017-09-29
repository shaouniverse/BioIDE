package org.big.service;


import com.alibaba.fastjson.JSON;
import org.big.entity.Team;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface TeamService {
    List<Team> selectTeamByUserId(String user_id);
    Team findbyID(String ID);
    void saveOne(Team thisTeam);
    void saveOneByUser(Team thisTeam);
    void removeOne(String ID);
    void removeOneByUser(String ID);
    JSON findbyInfo(HttpServletRequest request);
    JSON findbyUser(HttpServletRequest request);
    Team findOneByName(String name);
}
