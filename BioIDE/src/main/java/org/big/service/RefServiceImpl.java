package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.common.QueryTool;
import org.big.entity.Ref;
import org.big.entity.UserDetail;
import org.big.repository.RefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class RefServiceImpl implements RefService {
	@Autowired
	private RefRepository refRepository;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public JSON findRefList(HttpServletRequest request) {
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
		List<Ref> thisList = new ArrayList<>();
		Page<Ref> thisPage = this.refRepository.searchInfo(searchText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','ref')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','ref')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("author", thisList.get(i).getAuthor());
			row.put("title", "<a href=\"console/ref/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getTitle() + "</a>");
			row.put("languages", thisList.get(i).getLanguages());
			row.put("ptype", thisList.get(i).getPtype());
			row.put("journal", thisList.get(i).getJournal());
			row.put("press", thisList.get(i).getPress());
			row.put("pyear", thisList.get(i).getPyear());
			row.put("version", thisList.get(i).getVersion());
			row.put("inputer", this.userService.findbyID(thisList.get(i).getInputer()).getNickname());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addTime = "";
			try {
				addTime = formatter.format(thisList.get(i).getInputtime());
			} catch (Exception e) {
			}
			row.put("inputtime", addTime);
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }

	@Override
	public Ref findOneById(String id) {
		return this.refRepository.findOneById(id);
	}

	@Override
	public void saveOne(@Valid Ref thisRef) {
		thisRef.setId(UUID.randomUUID().toString());
		UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		thisRef.setInputer(thisUser.getId());
		thisRef.setInputtime(new Timestamp(System.currentTimeMillis()));
		thisRef.setSynchdate(new Timestamp(System.currentTimeMillis()));
		thisRef.setSynchstatus(0);
		thisRef.setStatus(1);
		
		this.refRepository.save(thisRef);
	}

	@Override
	public void removeOne(String Id) {
		this.refRepository.deleteOneById(Id);
	}

	@Override
	public boolean logicRemove(String id) {
		Ref thisRef = this.refRepository.findOneById(id);
		if (null != thisRef && 1 == thisRef.getStatus()) {
			thisRef.setStatus(0);
			this.refRepository.save(thisRef);
			return true;
		}
		return false;
	}

	@Override
	public void updateOneById(@Valid Ref thisRef) {
		thisRef.setInputtime(new Timestamp(System.currentTimeMillis()));
		thisRef.setSynchdate(new Timestamp(System.currentTimeMillis()));
		this.refRepository.save(thisRef);
	}
	
	
}
