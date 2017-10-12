package org.big.repository;

import org.big.entity.UserTeam;
import org.big.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *<p><b>UserTeam的DAO类接口</b></p>
 *<p> UserTeam的DAO类接口，与UserTeam有关的持久化操作方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface UserTeamRepository extends BaseRepository<UserTeam, String> {

    /**
     *<b>根据Team id删除关系记录</b>
     *<p> 将所有TeamId为传入参数的记录全部删除</p>
     * @author WangTianshan (王天山)
     * @param teamId Team的id
     * @return void
     */
    @Modifying
    @Transactional
    @Query("delete from UserTeam ut where ut.teamId = ?1")
    void deleteByTeamId(String teamId);

}
