package org.big.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.big.common.QueryTool;
import org.big.entity.Dataset;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.big.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class DatasetServiceImpl implements DatasetService {
	@Autowired
	private DatasetRepository datasetRepository;
	
	@Override
	public JSON findbyInfo(HttpServletRequest request) {
        JSON json = null;
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
            sort="createdDate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        List<Dataset> thisList=new ArrayList<>();
        Page<Dataset> thisPage=this.datasetRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
            String thisEdit=
            	"<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','dataset')\" >" +
                	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                "</a>"+ "&nbsp;"+
                "<a class=\"table-edit-icon\" onclick=\"deleteThisObject('"+thisList.get(i).getId()+"','dataset')\" >" +
                	"<span class=\"fa fa-trash\"></span>" +
            	"</a>";
            row.put("select",thisSelect);
            row.put("user.id",thisList.get(i).getUser().getNickname());
//            String coverimgPath=
//                    "<img class=\"maxWidth400\" src=\""+thisList.get(i).getCoverimg()+"\" alt=\""+thisList.get(i).getCoverimg()+"\" />";
            row.put("dsname",thisList.get(i).getDsname());
            row.put("dsabstract",thisList.get(i).getDsabstract());
//            row.put("num",thisList.get(i).getNum());
//            row.put("coverimg",coverimgPath);
            String thisStatus="";
            switch(thisList.get(i).getStatus())
            {
                case 0:
                    thisStatus="私有";
                    break;
                case 1:
                    thisStatus="公开";
                    break;
                case -1:
                    thisStatus="弃用";
                    break;
                default:
                    thisStatus="未知";
                    break;
            }
            row.put("status",thisStatus);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";  	// 添加时间
            // String updateTime="";	// 修改时间
            try {
                addTime=formatter.format(thisPage.getContent().get(i).getCreatedDate());
            } catch (Exception e) {
            }
            
            /*try {
                updateTime=formatter.format(thisList.get(i).getDtimeupdate());
            } catch (Exception e) {
            }*/
            row.put("createdDate",addTime);
            // row.put("dtimeupdate",updateTime);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
	}

	@Override
    @Transactional
	public JSON findMybyInfo(HttpServletRequest request) {
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
            sort="createdDate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();

        //获取当前登录用户
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Dataset> thisPage=this.datasetRepository.searchMyInfo(searchText,thisUser.getId(),QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());

        for(int i=0;i<thisPage.getContent().size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="";
            String thisEdit="";
            //判断是否未默认数据集
            if(!thisPage.getContent().get(i).getDsabstract().equals("Default")){
                thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisPage.getContent().get(i).getId()+"' />";
                thisEdit=
                        "<a class=\"table-edit-icon\" onclick=\"editThisObject('"+thisPage.getContent().get(i).getId()+"','dataset')\" >" +
                        	"<span class=\"glyphicon glyphicon-edit\"></span>" +
                        "</a>" + "&nbsp;"+
                        "<a class=\"table-edit-icon\" onclick=\"removeThisObject('"+thisPage.getContent().get(i).getId()+"','dataset')\" >" +
                        	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                        "</a>";
            }
            row.put("select",thisSelect);
           // String coverimgPath= "<img class=\"maxWidth400\" src=\""+thisPage.getContent().get(i).getCoverimg()+"\" alt=\""+thisPage.getContent().get(i).getCoverimg()+"\" />";
            row.put("dsname","<a href=\"console/dataset/show/"+thisPage.getContent().get(i).getId()+"\">"+thisPage.getContent().get(i).getDsname()+"</a>");
            row.put("dsabstract",thisPage.getContent().get(i).getDsabstract());
           // row.put("num",thisPage.getContent().get(i).getNum());
           // row.put("coverimg",coverimgPath);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";
            // String updateTime="";
            try {
                addTime=formatter.format(thisPage.getContent().get(i).getCreatedDate());
            } catch (Exception e) {
            }
           /* try {
                updateTime=formatter.format(thisPage.getContent().get(i).getDtimeupdate());
            } catch (Exception e) {
            }*/
            row.put("createdDate",addTime);
            // row.put("dtimeupdate",updateTime);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
	}

	@Override
	public JSON findMybySelect(HttpServletRequest request) {
		return null;
	}

	@Override
	public void saveOne(Dataset thisDataset) {

	}

	@Override
	public void addOne(Dataset thisDataset) {

	}

	@Override
	public Boolean removeOne(int ID) {
		return null;
	}

	@Override
	public Boolean deleteOne(HttpServletRequest request, int ID) {
		return null;
	}

	@Override
	public Dataset findbyID(int ID) {
		return null;
	}

	@Override
	public Dataset findOneByDsabstractAndUser(String dsabstract, User thisUser) {
		return null;
	}

	@Override
	public Dataset findDefaultByUser() {
		return null;
	}

	@Override
	public JSON newOne(Dataset thisDataset) {
		return null;
	}

}
