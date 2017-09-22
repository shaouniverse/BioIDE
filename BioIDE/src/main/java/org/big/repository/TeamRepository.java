package org.big.repository;

import org.big.entity.Team;
import org.big.entity.User;
import org.big.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface TeamRepository extends BaseRepository<Team, String> {

    @Query(value ="SELECT t.* FROM team AS t " +
            "LEFT JOIN user_team AS ut " +
            "ON ut.team_id=t.id " +
            "WHERE ut.user_id=?1"
            , nativeQuery = true)
    List<Team> selectTeamByUserId(String user_id);

}
