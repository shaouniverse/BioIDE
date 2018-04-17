package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
//            row.put("user.id",thisList.get(i).getUser().getNickname());
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
        List<Dataset> thisList=new ArrayList<>();
        
        thisList=thisPage.getContent();
        thisTable.put("total",thisPage.getTotalElements());

        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="";
            String thisEdit="";
            //判断是否为默认数据集
           // if(!thisPage.getContent().get(i).getDsabstract().equals("Default")){
                thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
                thisEdit=
                        "<a class=\"table-edit-icon\" onclick=\"editThisObject('"+thisList.get(i).getId()+"','dataset')\" >" +
                        	"<span class=\"glyphicon glyphicon-edit\"></span>" +
                        "</a>" + "&nbsp;"+
                        "<a class=\"table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','dataset')\" >" +
                        	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                        "</a>";
         //   }
            row.put("select",thisSelect);
            row.put("dsname","<a href=\"console/dataset/show/"+thisPage.getContent().get(i).getId()+"\">"+thisPage.getContent().get(i).getDsname()+"</a>");
            row.put("dsabstract",thisPage.getContent().get(i).getDsabstract());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";
            // String updateTiDme="";
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
		thisDataset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		thisDataset.setSynchdate(new Timestamp(System.currentTimeMillis()));
		if (thisDataset.getDsabstract().equals("Default")) {
			thisDataset.setDsabstract("default");
		}
		this.datasetRepository.save(thisDataset);
	}

	@Override
	public void addOne(Dataset thisDataset) {
		thisDataset.setId(UUID.randomUUID().toString());						// 数据集ID(数据及名称及描述)
		thisDataset.setCreatedDate(new Timestamp(System.currentTimeMillis()));	// 创建日期
		//获取当前登录用户
		UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		thisDataset.setCreator(thisUser.getId());								// 创建人 -- 当前数据集的负责人
		thisDataset.setStatus(1);
		thisDataset.setSynchstatus(0);
		thisDataset.setSynchdate(new Timestamp(System.currentTimeMillis()));	// 最后同步日期
		thisDataset.setMark(UUID.randomUUID().toString());
        this.datasetRepository.save(thisDataset);
	}

	@Override
	public Boolean removeOne(String ID) {
		System.out.println("ServiceImplID:" + ID);
		Dataset thisDataset = datasetRepository.findOneById(ID);
		System.out.println("UserID:" + thisDataset.getCreator() + "\t" + "DatasetID:" + thisDataset.getId() + "\t" + "TeamID:" + thisDataset.getTeam().getId());
		thisDataset.setStatus(0);
		this.datasetRepository.save(thisDataset);
		return true;
	}

	@Override
	public Boolean deleteOne(HttpServletRequest request, int ID) {
		return null;
	}

	@Override
	public Dataset findbyID(String ID) {
		return this.datasetRepository.getOne(ID);
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
		JSONObject thisResult=new JSONObject();
        try{
            // thisDataset.setDtime(new Timestamp(System.currentTimeMillis()));
            thisDataset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            thisDataset.setStatus((byte) 1);
            String mark=UUID.randomUUID().toString();
            thisDataset.setMark(mark);
            //获取当前登录用户
            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            thisDataset.setCreator(thisUser.getId());
            this.datasetRepository.save(thisDataset);
            thisResult.put("result",true);
            thisResult.put("newId",this.datasetRepository.findOneByMark(mark).getId());
            thisResult.put("newDsname",this.datasetRepository.findOneByMark(mark).getDsname());
        }
        catch (Exception e){
            thisResult.put("result",false);
        }
        return thisResult;
	}

}
