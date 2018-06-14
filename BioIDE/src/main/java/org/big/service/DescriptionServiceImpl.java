package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.big.common.QueryTool;
import org.big.entity.Description;
import org.big.entity.UserDetail;
import org.big.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class DescriptionServiceImpl implements DescriptionService {
	@Autowired
	private DescriptionRepository descriptionRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private TaxonService taxonService;
	@Autowired
	private DescriptiontypeService descriptiontypeService;
	
	@Override
	public JSON findDescriptionList(HttpServletRequest request) {
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
		List<Description> thisList = new ArrayList<>();
		Page<Description> thisPage = this.descriptionRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','description')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','description')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("destitle", "<a href=\"console/description/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getDestitle() + "</a>");
			row.put("describer", thisList.get(i).getDescriber());
			row.put("desdate", thisList.get(i).getDesdate());
			row.put("destypeid", thisList.get(i).getDestypeid());
			row.put("rightsholder", thisList.get(i).getRightsholder());
			row.put("language", thisList.get(i).getLanguage());
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
	public JSON addDescription(Description thisDescription, HttpServletRequest request) {
		// 关系(关系类型)
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("descriptionId_") == 0) {
				thisDescription.setId(request.getParameter(paraName));
			}
			if (paraName.indexOf("destitle_") == 0) {
				thisDescription.setDestitle(request.getParameter(paraName));
			}
			if (paraName.indexOf("describer_") == 0) {
				thisDescription.setDescriber(request.getParameter(paraName));
			}
			if (paraName.indexOf("desdate_") == 0) {
				thisDescription.setDesdate(request.getParameter(paraName));
			}
			if (paraName.indexOf("descontent_") == 0) {
				thisDescription.setDescontent(request.getParameter(paraName));
			}
			if (paraName.indexOf("descriptionremark_") == 0) {
				thisDescription.setRemark(request.getParameter(paraName));
			}
			if (paraName.indexOf("descriptionsourcesid_") == 0) {
				thisDescription.setSourcesid(request.getParameter(paraName));
			}
			if (paraName.indexOf("relation_") == 0) {
				thisDescription.setRelation(request.getParameter(paraName));
			}
			if (paraName.indexOf("rightsholder_") == 0) {
				thisDescription.setRightsholder(request.getParameter(paraName));
			}
			if (paraName.indexOf("destypeid_") == 0) {
				thisDescription.setDestypeid(request.getParameter(paraName));
				thisDescription.setDescriptiontype(this.descriptiontypeService.findOneById(request.getParameter(paraName)));
			}
			if (paraName.indexOf("licenseid_") == 0) {
				thisDescription.setLicenseid(request.getParameter(paraName));
			}
			if (paraName.indexOf("language_") == 0) {
				thisDescription.setLanguage(request.getParameter(paraName));
			}
		}
		thisDescription.setInputtime(new Timestamp(System.currentTimeMillis()));
		
		JSONObject thisResult = new JSONObject();
		try {
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisDescription.setInputer(thisUser.getId());
			thisDescription.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisDescription.setStatus(1);
			thisDescription.setSynchstatus(0);
			thisDescription.setRefjson(handleReferenceToJson(request).toJSONString());
			String taxonId = (String) request.getSession().getAttribute("taxonId");
			thisDescription.setTaxon(taxonService.findOneById(taxonId));
			
			
			this.descriptionRepository.save(thisDescription);
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
		String countDescriptionReferences = null;
		String descriptionReferencesPageE = null;
		String descriptionReferencesPageS = null;
		String descriptionReferenceId = null;
		String descriptionReferenceTypeId = null;
		String jsonStr = null;
		
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("countDescriptionReferences_") == 0) {
				countDescriptionReferences = request.getParameter(paraName);
			}
			if (paraName.indexOf("descriptionReferences_") == 0) {
				descriptionReferenceId = request.getParameter(paraName);
			}
			
			if (paraName.indexOf("descriptionReferencesId_") == 0) {
				descriptionReferenceTypeId = request.getParameter(paraName);
			}
			
			if (paraName.indexOf("descriptionReferencesPageS_") == 0) {
				descriptionReferencesPageS = request.getParameter(paraName);
			}
			if (paraName.indexOf("descriptionReferencesPageE_") == 0) {
				descriptionReferencesPageE = request.getParameter(paraName);
			}
		}
		for (int i = 1; i <= Integer.parseInt(countDescriptionReferences); i++) {
			if (StringUtils.isNotBlank(descriptionReferenceId) && StringUtils.isNotBlank(descriptionReferenceTypeId) 
					&& StringUtils.isNotBlank(descriptionReferencesPageS) && StringUtils.isNotBlank(descriptionReferencesPageE)) {
				jsonStr = "{"
						+ "\"descriptionReferenceId\"" + ":\"" + descriptionReferenceId + "\","
						+ "\"descriptionReferenceTypeId\"" + ":\"" + descriptionReferenceTypeId + "\"," 
						+ "\"descriptionReferencesPageS\"" + ":\"" + descriptionReferencesPageS + "\","
						+ "\"descriptionReferencesPageE\"" + ":\"" + descriptionReferencesPageE + "\""
						+ "}";
			}
			JSONObject jsonText = JSON.parseObject(jsonStr);
			jsonArray.add(i - 1, jsonText);
		}
		return jsonArray;
	}

	@Override
	public boolean logicRemove(String id) {
		Description thisDescription = this.descriptionRepository.findOneById(id);
		if (null != thisDescription && 1 == thisDescription.getStatus()) {
			thisDescription.setStatus(0);
			this.descriptionRepository.save(thisDescription);
			return true;
		}
		return false;
	}

}
