package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.QueryTool;
import org.big.entity.Team;
import org.big.repository.TeamRepository;
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
public class TeamServiceImpl implements TeamService  {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> selectTeamByUserId(String team_id) {
        System.out.println("team_id="+team_id);
        List<Team> teams = this.teamRepository.selectTeamByUserId(team_id);
        for(Team thisTeam:teams){
            System.out.println(thisTeam.getName());
        }
        return this.teamRepository.selectTeamByUserId(team_id);
    }

    @Override
    @Transactional
    public JSON findbyInfo(HttpServletRequest request) {
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
    public void removeOne(String ID) {
        this.teamRepository.deleteById(ID);
    }

    @Override
    public Team findOneByName(String name) {
        return this.teamRepository.findOneByName(name);
    }
}
