package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.BuildEntity;
import org.big.common.QueryTool;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.entity.UserTeam;
import org.big.repository.TeamRepository;
import org.big.repository.UserRepository;
import org.big.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
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
 *<p><b>Team的Service类</b></p>
 *<p> Team的Service类，与Team有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Service
public class TeamServiceImpl implements TeamService  {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserTeamRepository userTeamRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Team> selectTeamByUserId(String userId) {
        List<String> ids = this.teamRepository.selectTeamByUserId(userId);
        return this.teamRepository.findAllById(ids);
    }

    @Override
    @Transactional
    public JSON findbyInfo(HttpServletRequest request) {
        String this_language="zh";
        Locale this_locale= LocaleContextHolder.getLocale();
        if(this_locale.getLanguage().equals("zh")){
            this_language="zh";
        }
        if(this_locale.getLanguage().equals("en")){
            this_language="en";
        }
        JSON json= null;
        String searchText=request.getParameter("search");
        if(searchText==null || searchText.length()<=0){
            searchText="";
        }
        int limit_serch=Integer.parseInt(request.getParameter("limit"));
        int offset_serch=Integer.parseInt(request.getParameter("offset"));
        String sort="desc";
        String order="date";
        sort=request.getParameter("sort");
        order=request.getParameter("order");
        if(sort==null || sort.length()<=0){
            sort="adddate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        List<Team> thisList=new ArrayList<>();
        Page<Team> thisPage=this.teamRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
            String thisEdit=
                    		"<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('"+thisList.get(i).getId()+"','team')\" >" +
                            "<span class=\"glyphicon glyphicon-edit\"></span>" +
                            "</a>" +
                            "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','team')\" >" +
                            "<span class=\"glyphicon glyphicon-remove\"></span>" +
                            "</a>";
            row.put("select",thisSelect);
            row.put("name",thisList.get(i).getName());
            row.put("leader",thisList.get(i).getLeader());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";
            try {
                addTime=formatter.format(thisList.get(i).getAdddate());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("adddate",addTime);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    @Transactional
    public JSON findbyUser(HttpServletRequest request) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String this_language="en";
        Locale this_locale= LocaleContextHolder.getLocale();
        if(this_locale.getLanguage().equals("zh")){
            this_language="zh";
        }
        if(this_locale.getLanguage().equals("en")){
            this_language="en";
        }
        JSON json= null;
        String searchText=request.getParameter("search");
        if(searchText==null || searchText.length()<=0){
            searchText="";
        }
        int limit_serch=Integer.parseInt(request.getParameter("limit"));
        int offset_serch=Integer.parseInt(request.getParameter("offset"));
        String sort="desc";
        String order="date";
        sort=request.getParameter("sort");
        order=request.getParameter("order");
        if(sort==null || sort.length()<=0){
            sort="adddate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        
        Page<Object> thisPage=this.teamRepository.searchInfoByUser(searchText,thisUser.getId(),QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        List<Object> thisList=new ArrayList<>();
        
        thisList=thisPage.getContent();
        thisTable.put("total",thisPage.getTotalElements());
        for(int i=0;i<thisList.size();i++){
            JSONObject row = new JSONObject();
            Team thisTeam= BuildEntity.buildTeam(thisList.get(i));
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisTeam.getId()+"' />";
            String thisEdit=
                    "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('"+thisTeam.getId()+"','team')\" >" +
                     	"<span class=\"glyphicon glyphicon-edit\"></span>" +
                    "</a>" +	
                    "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisTeam.getId()+"','team')\" >" +
                     	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                    "</a>";
            row.put("select",thisSelect);
            row.put("name","<a target='_blank' href='console/team/details/"+thisTeam.getId()+"'>"+thisTeam.getName()+"</a>");
            row.put("leader",thisTeam.getLeader());
            row.put("members","共"+this.teamRepository.countMembersByTeamId(thisTeam.getId())+"人");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";
            try {
                addTime=formatter.format(thisTeam.getAdddate());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("adddate",addTime);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    public Team findbyID(String ID) {
        return this.teamRepository.getOne(ID);
    }

    @Override
    public void saveOne(Team thisTeam) {
        if(thisTeam.getId()==null||thisTeam.getId().equals("")||thisTeam.getId().length()<=0){
            thisTeam.setId(UUID.randomUUID().toString());
            thisTeam.setAdddate(new Timestamp(System.currentTimeMillis()));
        }
        this.teamRepository.save(thisTeam);
    }

    @Override
    public void saveOneByUser(Team thisTeam) {
        if(thisTeam.getId()==null||thisTeam.getId().equals("")||thisTeam.getId().length()<=0){
            thisTeam.setId(UUID.randomUUID().toString());
            thisTeam.setAdddate(new Timestamp(System.currentTimeMillis()));
        }
        this.teamRepository.save(thisTeam);
        UserTeam thisUserTeam=new UserTeam();
        thisUserTeam.setUserId(thisTeam.getLeader());
        thisUserTeam.setTeamId(thisTeam.getId());
        this.userTeamRepository.save(thisUserTeam);
    }

    @Override
    public void removeOne(String ID) {
    	this.userTeamRepository.deleteByTeamId(ID);	// 先操作中间表，删除外键关系
        this.teamRepository.deleteById(ID);			// 再操作Team表
    }

    @Override
    public void removeOneByUser(String ID) {
    	this.userTeamRepository.deleteByTeamId(ID);
        this.teamRepository.deleteById(ID);
    }

    @Override
    public Team findOneByName(String name) {
        return this.teamRepository.findOneByName(name);
    }

    @Override
    public int countMembersByTeamId(String ID){
        return this.teamRepository.countMembersByTeamId(ID);
    }

    @Override
    public void removeMembersByTeamIdAndUserId(String teamId,String userId){
        this.userTeamRepository.deleteMembersByTeamIdAndUserId(teamId,userId);
    }

	@Override
	public void updateTeamInfoByLeader(String teamId, String userId) {
		String name = this.userRepository.findOneById(userId).getUserName();
		if (null != name && !"".equals(name)) {
			// 修改Team表的name & leader字段
			this.userTeamRepository.updateTeamInfoByLeader(name, userId, teamId);
		}
	}
}