package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.common.QueryTool;
import org.big.entity.Datasource;
import org.big.entity.UserDetail;
import org.big.repository.DatasourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Service
public class DatasourceServiceImpl implements DatasourceService{
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired 
	private UserService userService;
	@Autowired
	private DatasetService datasetService;
	
	@Override
	public JSON findBySelect(HttpServletRequest request) {
		String findText = request.getParameter("find");
		if (findText == null || findText.length() <= 0) {
			findText = "";
		}
		int findPage = 1;
		try {
			findPage = Integer.valueOf(request.getParameter("page"));
		} catch (Exception e) {
		}
		int limit_serch = 30;
		int offset_serch = (findPage - 1) * 30;
		String sort = "title";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<Datasource> thisList = new ArrayList<>();
		// 获得当前选中Dataset下的Datasources
		String datasetID = (String) request.getSession().getAttribute("datasetID");
		Page<Datasource> thisPage = this.datasourceRepository.searchByDsname(findText, datasetID,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisSelect.put("total_count", thisPage.getTotalElements());
		Boolean incompleteResulte = true;
		if ((thisPage.getTotalElements() / 30) > findPage)
			incompleteResulte = false;
		thisSelect.put("incompleteResulte", incompleteResulte);
		thisList = thisPage.getContent();
		if (findPage == 1) {
			JSONObject row = new JSONObject();
			row.put("id", "addNew");
			row.put("full_name", "新建一个数据源");
			items.add(row);
		}
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
			row.put("id", thisList.get(i).getId());
			row.put("full_name", thisList.get(i).getTitle());
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}

	@Override
	public void saveOne(@Valid Datasource thisDatasource) {	
		UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		thisDatasource.setInputer(thisUser.getId());
		thisDatasource.setId(UUID.randomUUID().toString());
		thisDatasource.setStatus(1);
		thisDatasource.setSynchdate(new Timestamp(System.currentTimeMillis()));
		thisDatasource.setSynchstatus(0);
		
		this.datasourceRepository.save(thisDatasource);
	}

	@Override
	public void removeOne(String Id) {
		this.datasourceRepository.deleteOneById(Id);
	}

	@Override
	public boolean logicRemove(String id) {
		Datasource thisDatasource = this.datasourceRepository.findOneById(id);
		if (null != thisDatasource && 1 == thisDatasource.getStatus()) {
			thisDatasource.setStatus(0);
			this.datasourceRepository.save(thisDatasource);
			return true;
		}
		return false;
	}
	
	@Override
	public void updateOneById(@Valid Datasource thisDatasource) {
		thisDatasource.setSynchdate(new Timestamp(System.currentTimeMillis()));
		this.datasourceRepository.save(thisDatasource);
	}

	@Override
	public JSON findDatasourceList(HttpServletRequest request) {
		String dsId = (String) request.getSession().getAttribute("datasetID");
		JSON json = null;
		String searchText = request.getParameter("search");
		if (searchText == null || searchText.length() <= 0) {
			searchText = "";
		}
		int limit_serch = Integer.parseInt(request.getParameter("limit"));
		int offset_serch = Integer.parseInt(request.getParameter("offset"));
		String sort = "desc";
		String order = "date";
		sort = request.getParameter("sort");
		order = request.getParameter("order");
		if (sort == null || sort.length() <= 0) {
			sort = "inputtime";
		}
		if (order == null || order.length() <= 0) {
			order = "desc";
		}
		JSONObject thisTable = new JSONObject();
		JSONArray rows = new JSONArray();
		List<Datasource> thisList = new ArrayList<>();
		Page<Datasource> thisPage = this.datasourceRepository.searchInfo(searchText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order), dsId);
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','datasource')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','datasource')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("title", "<a href=\"console/datasource/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getTitle() + "</a>");
			row.put("inputer", this.userService.findbyID(thisList.get(i).getInputer()).getNickname());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addTime = "";
			String editTime = "";
			try {
				addTime = formatter.format(thisList.get(i).getInputtime());
				editTime = formatter.format(thisList.get(i).getSynchdate());
			} catch (Exception e) {
			}
			row.put("inputtime", addTime);
			row.put("synchdate", editTime);
			row.put("versions", thisList.get(i).getVersions());
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }
	
	@Override
	public Datasource findOneById(String id) {
		return this.datasourceRepository.findOneById(id);
	}

	@Override
	public JSON newOne(Datasource thisDatasource, HttpServletRequest request) {
		JSONObject thisResult = new JSONObject();
		try {
			thisDatasource.setInputtime(new Timestamp(System.currentTimeMillis()));
			thisDatasource.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisDatasource.setStatus(1);
			String id = UUID.randomUUID().toString();
			thisDatasource.setId(id);
			thisDatasource.setSynchstatus(0);
			// 获取当前登录用户
			 UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 thisDatasource.setInputer(thisUser.getId());
			// 获取当前选中Dataset
			String dsid = (String) request.getSession().getAttribute("datasetID");
			thisDatasource.setDataset(datasetService.findbyID(dsid));
			this.datasourceRepository.save(thisDatasource);

			thisResult.put("result", true);
			thisResult.put("newId", this.datasourceRepository.findOneById(id).getId());
			thisResult.put("newTitle", this.datasourceRepository.findOneById(id).getTitle());
		} catch (Exception e) {
			thisResult.put("result", false);
		}
		return thisResult;
	}
}
