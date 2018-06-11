package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.common.QueryTool;
import org.big.entity.Citation;
import org.big.entity.UserDetail;
import org.big.repository.CitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class CitationServiceImpl implements CitationService {
	@Autowired
	private CitationRepository citationRepository;
	
	@Override
	public JSON findCitationList(HttpServletRequest request) {
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
			sort = "synchdate";
		}
		if (order == null || order.length() <= 0) {
			order = "desc";
		}
		JSONObject thisTable = new JSONObject();
		JSONArray rows = new JSONArray();
		List<Citation> thisList = new ArrayList<>();
		Page<Citation> thisPage = this.citationRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','citation')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','citation')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("sciname", "<a href=\"console/taxaset/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getSciname() + "</a>");
			row.put("authorship", thisList.get(i).getAuthorship());
			row.put("nametype", thisList.get(i).getNametype());
			row.put("inputer", thisList.get(i).getInputer());
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
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }

	@Override
	public JSON addCitation(@Valid Citation thisCitation, HttpServletRequest request) {
		JSONObject thisResult = new JSONObject();
		try {
			thisCitation.setId(UUID.randomUUID().toString());
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisCitation.setInputer(thisUser.getId());
			thisCitation.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisCitation.setStatus(1);
			thisCitation.setSynchstatus(0);
			thisCitation.setRefjson(handleReferenceToJson(request).toJSONString());
			this.citationRepository.save(thisCitation);
			
			thisResult.put("result", true);
		} catch (Exception e) {
			thisResult.put("result", false);
		}
		return thisResult;
	}
	
	
	@Override
	public JSON handleReferenceToJson(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("countCitationReferences_") == 0) {
				System.out.println(paraName + " : " + request.getParameter(paraName));
			}
			if (paraName.indexOf("citationReferencesPageE_") == 0) {
				System.out.println(paraName + " : " + request.getParameter(paraName));
			}
			if (paraName.indexOf("citationReferencesPageS_") == 0) {
				System.out.println(paraName + " : " + request.getParameter(paraName));
			}
			if (paraName.indexOf("citationReferences_") == 0) {
				System.out.println(paraName + " : " + request.getParameter(paraName));
			}
		}
		
		
		return null;
	}
	
	
}
