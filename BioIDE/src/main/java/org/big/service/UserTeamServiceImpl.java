package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.QueryTool;
import org.big.entity.Team;
import org.big.entity.UserTeam;
import org.big.repository.TeamRepository;
import org.big.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Tianshan on 17/2/6.
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

//    @Override
//    public void removeOne(String ID) {
//        this.userTeamRepository.deleteById(ID);
//    }

}
