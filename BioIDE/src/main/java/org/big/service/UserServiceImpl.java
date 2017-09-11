package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.entity.User;
import org.big.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

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
            sort="adddate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        thisTable.put("total",this.userRepository.countSearchInfo(searchText));
        JSONArray rows = new JSONArray();
        List<User> thisList=new ArrayList<>();
        if(order.equals("asc")){
            //PageRequest pageAsc=new PageRequest(offset_serch-1,limit_serch,new Sort(Sort.Direction.ASC, sort));
            //Page<User> thisPages= this.userRepository.searchInfo(searchText,pageAsc);
            //thisList = this.userRepository.searchInfo(searchText,new Sort(Sort.Direction.ASC, sort));
            thisList = this.userRepository.searchInfo(searchText,sort,order,offset_serch,limit_serch);
        }
        else{
            //PageRequest pageDesc=new PageRequest(offset_serch-1,limit_serch,new Sort(Sort.Direction.DESC, sort));
            //thisList = this.userRepository.searchInfo(searchText,pageDesc);
            //Page<User> thisPages= this.userRepository.searchInfo(searchText,pageDesc);
            //thisList = this.userRepository.searchInfo(searchText,new Sort(Sort.Direction.DESC, sort));
            thisList = this.userRepository.searchInfo(searchText,sort,order,offset_serch,limit_serch);
        }
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
            String thisEdit=
                    "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('"+thisList.get(i).getId()+"','user')\" >" +
                            "<span class=\"glyphicon glyphicon-edit\"></span>" +
                            "</a>" +
                            "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','user')\" >" +
                            "<span class=\"glyphicon glyphicon-remove\"></span>" +
                            "</a>";
            row.put("select",thisSelect);
            row.put("userName",thisList.get(i).getUserName());
            row.put("email",thisList.get(i).getEmail());
            row.put("phone",thisList.get(i).getPhone());
            row.put("role",thisList.get(i).getRole());
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
    public User findbyID(String ID) {
        return this.userRepository.getOne(ID);
    }

//    @Override
//    public List<User> findbyInfoPage(String findText, int limit_serch, int offset_serch, String order, String sort) {
//        System.out.println("==========");
//        System.out.println("findText:"+findText);
//        System.out.println("limit_serch:"+limit_serch);
//        System.out.println("offset_serch:"+offset_serch);
//        System.out.println("order:"+order);
//        System.out.println("sort:"+sort);
//        System.out.println("==========");
//        if(order.equals("asc"))
//            return this.userRepository.searchInfoPage(findText,new Sort(Sort.Direction.ASC, sort),new PageRequest(offset_serch+1,limit_serch));
//        else
//            return this.userRepository.searchInfoPage(findText,new Sort(Sort.Direction.DESC, sort),new PageRequest(offset_serch+1,limit_serch));
//    }

//    @Override
//    public List<User> findbyInfo(String findText, int limit_serch, int offset_serch,String order, String sort) {
//        //return this.personRepository.searchInfoQuery(findText,limit_serch,offset_serch,order,sort);
//        System.out.println("==========");
//        System.out.println("findText:"+findText);
//        System.out.println("limit_serch:"+limit_serch);
//        System.out.println("offset_serch:"+offset_serch);
//        System.out.println("order:"+order);
//        System.out.println("sort:"+sort);
//        System.out.println("==========");
//        if(order.equals("asc"))
//            return this.userRepository.searchInfo(findText,new Sort(Sort.Direction.ASC, sort));
//        else
//            return this.userRepository.searchInfo(findText,new Sort(Sort.Direction.DESC, sort));
//    }

    @Override
    public int countfindbyInfo(String findText) {
        return this.userRepository.countSearchInfo("%"+findText+"%");
    }

    @Override
    public void addNew(User thisUser) {
        if(thisUser.getId()==null||thisUser.getId().equals("")||thisUser.getId().length()<=0){
            thisUser.setId(UUID.randomUUID().toString());
            thisUser.setAdddate(new Timestamp(System.currentTimeMillis()));
        }
        this.userRepository.save(thisUser);
    }

    @Override
    public void removeOne(String ID) {
        this.userRepository.deleteById(ID);
    }

    @Override
    public void updateOne(User thisUser) {
        this.userRepository.save(thisUser);
    }
}
