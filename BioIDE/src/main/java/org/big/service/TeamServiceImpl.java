package org.big.service;

import org.big.entity.Team;
import org.big.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tianshan on 17/2/6.
 */
@Service
public class TeamServiceImpl implements TeamService  {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> selectTeamByUserId(String user_id) {
        return this.teamRepository.selectTeamByUserId(user_id);
    }


}
