package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
	@Autowired
	private TaxonService taxonService;
	@Autowired
	private UserService userService;
	
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
			row.put("sciname", "<a href=\"console/citation/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getSciname() + "</a>");
			row.put("authorship", thisList.get(i).getAuthorship());
			row.put("nametype", thisList.get(i).getNametype());
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
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }

	@Override
	public JSON addCitation(Citation thisCitation, HttpServletRequest request) {
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("citationId_") == 0) {
				System.out.println(request.getParameter(paraName));
				thisCitation.setId(request.getParameter(paraName));
			}
			if (paraName.indexOf("sciname_") == 0) {
				thisCitation.setSciname(request.getParameter(paraName));
			}
			if (paraName.indexOf("authorship_") == 0) {
				thisCitation.setAuthorship(request.getParameter(paraName));
			}
			if (paraName.indexOf("nametype_") == 0) {
				thisCitation.setNametype(Integer.valueOf(request.getParameter(paraName)));
			}
			if (paraName.indexOf("citationSourcesid_") == 0) {
				thisCitation.setSourcesid(request.getParameter(paraName));
			}
			if (paraName.indexOf("citationstr_") == 0) {
				thisCitation.setCitationstr(request.getParameter(paraName));
			}
		}
		
		JSONObject thisResult = new JSONObject();
		try {
			/*thisCitation.setId(UUID.randomUUID().toString());*/
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisCitation.setInputer(thisUser.getId());
			thisCitation.setInputtime(new Timestamp(System.currentTimeMillis()));
			thisCitation.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisCitation.setStatus(1);
			thisCitation.setSynchstatus(0);
			thisCitation.setRefjson(handleReferenceToJson(request).toJSONString());
			String taxonId = (String) request.getSession().getAttribute("taxonId");
			thisCitation.setTaxon(taxonService.findOneById(taxonId));
			System.out.println(thisCitation);
			this.citationRepository.save(thisCitation);
			thisResult.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			thisResult.put("result", false);
		}
		return thisResult;
	}
	
	
	@Override
	public JSON handleReferenceToJson(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		String countCitationReferences = null;
		String citationReferencesPageE = null;
		String citationReferencesPageS = null;
		String citationReferenceId = null;
		String jsonStr = null;
		
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("countCitationReferences_") == 0) {
				countCitationReferences = request.getParameter(paraName);
			}
			if (paraName.indexOf("citationReferences_") == 0) {
				citationReferenceId = request.getParameter(paraName);
			}
			if (paraName.indexOf("citationReferencesPageS_") == 0) {
				citationReferencesPageS = request.getParameter(paraName);
			}
			if (paraName.indexOf("citationReferencesPageE_") == 0) {
				citationReferencesPageE = request.getParameter(paraName);
			}
		}
		for (int i = 1; i <= Integer.parseInt(countCitationReferences); i++) {
			if (StringUtils.isNotBlank(citationReferenceId) && StringUtils.isNotBlank(citationReferencesPageS)
					&& StringUtils.isNotBlank(citationReferencesPageE)) {
				jsonStr = "{"
						+ "\"citationReferenceId\"" + ":\"" + citationReferenceId + "\","
						+ "\"citationReferencesPageS\"" + ":\"" + citationReferencesPageS + "\"," 
						+ "\"citationReferencesPageE\"" + ":\"" + citationReferencesPageE + "\""
						+ "}";
			}
			JSONObject jsonText = JSON.parseObject(jsonStr);
			jsonArray.add(i - 1, jsonText);
		}
		return jsonArray;
	}

	@Override
	public boolean logicRemove(String id) {
		Citation thisCitation = this.citationRepository.findOneById(id);
		if (null != thisCitation && 1 == thisCitation.getStatus()) {
			thisCitation.setStatus(0);
			this.citationRepository.save(thisCitation);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteOne(HttpServletRequest request) {
		String citationId = request.getParameter("citationId");
		if (StringUtils.isNotBlank(citationId)) {
			if (null != this.citationRepository.findOneById(citationId)) {
				this.citationRepository.deleteOneById(citationId);
			}
			return true;
		}
		return false;
	}
	
}
