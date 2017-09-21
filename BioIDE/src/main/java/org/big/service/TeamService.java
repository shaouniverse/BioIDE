package org.big.service;


import org.big.entity.Team;

import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
public interface TeamService {
    List<Team> selectTeamByUserId(String user_id);
}
