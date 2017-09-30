package org.big.repository;

import org.big.entity.Team;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select t from Team t" +
            " where (" +
            "t.name like %?1% " +
            "or t.leader like %?1%)"
    )
    Page<Team> searchInfo(
            String findText,
            Pageable pageable
    );

    @Query(value = "SELECT " +
            "t.id AS id," +
            "t.name AS name," +
            "u.userName AS leader," +
            "t.note AS note," +
            "t.adddate AS adddate " +
            "FROM Team t " +
            "LEFT JOIN UserTeam ut " +
            "ON ut.teamId=t.id " +
            "LEFT JOIN User u " +
            "ON u.id=t.leader " +
            "WHERE " +
            "ut.userId=?2 "+
            "AND (" +
            "t.name LIKE %?1% " +
            "OR u.userName LIKE %?1%)"
    )
    Page<Object> searchInfoByUser(
            String findText,
            String user_id,
            Pageable pageable
    );

    Team findOneByName(String name);

}
