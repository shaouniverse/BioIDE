package org.big.repository;

import org.big.entity.Team;
import org.big.entity.UserTeam;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface UserTeamRepository extends BaseRepository<UserTeam, String> {

    @Modifying
    @Transactional
    @Query("delete from UserTeam ut where ut.teamId = ?1")
    void deleteByTeamId(String teamId);

}
