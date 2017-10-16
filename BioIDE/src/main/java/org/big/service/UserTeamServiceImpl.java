package org.big.service;

import org.big.entity.UserTeam;
import org.big.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *<p><b>UserTeam的Service类</b></p>
 *<p> UserTeam的Service类，与UserTeam有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Service
public class UserTeamServiceImpl implements UserTeamService  {

    @Autowired
    private UserTeamRepository userTeamRepository;


    @Override
    public void saveOne(String userId,String teamId) {
        UserTeam thisUserTeam=new UserTeam();
        thisUserTeam.setUserId(userId);
        thisUserTeam.setTeamId(teamId);
        this.userTeamRepository.save(thisUserTeam);
    }

}
