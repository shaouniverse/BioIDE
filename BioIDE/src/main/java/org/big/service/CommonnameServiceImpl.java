package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.QueryTool;
import org.big.entity.Commonname;
import org.big.repository.CommonnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Service
public class CommonnameServiceImpl implements CommonnameService {

    @Autowired
    private CommonnameRepository commonnameRepository;

    @Override
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
            sort="inputtime";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        List<Commonname> thisList=new ArrayList<>();
        Page<Commonname> thisPage=this.commonnameRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
            String thisEdit=
                    "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('"+thisList.get(i).getId()+"','commonname')\" >" +
                            "<span class=\"glyphicon glyphicon-edit\"></span>" +
                            "</a>" +
                            "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','commonname')\" >" +
                            "<span class=\"glyphicon glyphicon-remove\"></span>" +
                            "</a>";
            row.put("select",thisSelect);
            row.put("taxonid",thisList.get(i).getTaxonid());
            row.put("commonname",thisList.get(i).getCommonname());
            row.put("language",thisList.get(i).getLanguage());
            row.put("refjson",thisList.get(i).getRefjson());
            row.put("sourcesid",thisList.get(i).getSourcesid());
            row.put("status",thisList.get(i).getStatus());
            row.put("inputer",thisList.get(i).getInputer());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String inpuTtime="";
            try {
                inpuTtime=formatter.format(thisList.get(i).getInputtime());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("inputtime",inpuTtime);
            row.put("synchstatus",thisList.get(i).getSynchstatus());
            String synchDate="";
            try {
                synchDate=formatter.format(thisList.get(i).getSynchdate());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("synchdate",synchDate);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    public Commonname findbyID(String ID) {
        return this.commonnameRepository.getOne(ID);
    }


    @Override
    public void saveOne(Commonname thisCommoname) {
        if(thisCommoname.getId()==null||thisCommoname.getId().equals("")||thisCommoname.getId().length()<=0){
            thisCommoname.setId(UUID.randomUUID().toString());
            thisCommoname.setInputtime(new Timestamp(System.currentTimeMillis()));
            thisCommoname.setSynchdate(null);
        }
        this.commonnameRepository.save(thisCommoname);
    }

    @Override
    public void removeOne(String ID) {
        this.commonnameRepository.deleteById(ID);
    }

}
