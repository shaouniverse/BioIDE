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
        System.out.println("user_id="+user_id);
        List<Team> teams = this.teamRepository.selectTeamByUserId(user_id);
        for(Team thisTeam:teams){
            System.out.println(thisTeam.getName());
        }
        return this.teamRepository.selectTeamByUserId(user_id);
    }


}
