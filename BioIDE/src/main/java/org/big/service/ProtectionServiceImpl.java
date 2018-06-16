package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.big.common.QueryTool;
import org.big.entity.Protection;
import org.big.entity.Protectstandard;
import org.big.entity.UserDetail;
import org.big.repository.ProtectionRepository;
import org.big.repository.ProtectstandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class ProtectionServiceImpl implements ProtectionService {
	@Autowired
	private ProtectionRepository protectionRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private TaxonService taxonService;
	@Autowired
	private ProtectstandardRepository protectstandardRepository;
	
	@Override
	public JSON findProtectionList(HttpServletRequest request) {
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
		List<Protection> thisList = new ArrayList<>();
		Page<Protection> thisPage = this.protectionRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','distributiondata')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','distributiondata')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("protlevel", "<a href=\"console/distributiondata/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getProtlevel() + "</a>");
			Protectstandard thisProtectstandard = this.protectionRepository.findOneById(thisList.get(i).getId()).getProtectstandard();
			row.put("standardname", thisProtectstandard.getStandardname());
			row.put("version", thisProtectstandard.getVersion());
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
	public JSON addProtection(Protection thisProtection, HttpServletRequest request) {
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("protectionId_") == 0) {
				thisProtection.setId(request.getParameter(paraName));
			}
			if (paraName.indexOf("protlevel_") == 0) {
				thisProtection.setProtlevel(request.getParameter(paraName));
			}
			if (paraName.indexOf("proassessment_") == 0) {
				thisProtection.setProassessment(request.getParameter(paraName));
			}
			if (paraName.indexOf("standardname_") == 0) {
				Protectstandard thisProtectstandard = protectstandardRepository.findOneById(request.getParameter(paraName));
				thisProtection.setProtectstandard(thisProtectstandard);
			}
			if (paraName.indexOf("version_") == 0) {
				thisProtection.setProassessment(request.getParameter(paraName));
			}
		}
		thisProtection.setInputtime(new Timestamp(System.currentTimeMillis()));
		
		JSONObject thisResult = new JSONObject();
		try {
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisProtection.setInputer(thisUser.getId());
			thisProtection.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisProtection.setStatus(1);
			thisProtection.setSynchstatus(0);
			thisProtection.setRefjson(handleReferenceToJson(request).toJSONString());
			String taxonId = (String) request.getSession().getAttribute("taxonId");
			thisProtection.setTaxon(taxonService.findOneById(taxonId));
			
			this.protectionRepository.save(thisProtection);
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
		String countProtectionReferences = null;
		String protectionReferencesPageE = null;
		String protectionReferencesPageS = null;
		String protectionReferenceId = null;
		String jsonStr = null;
		
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("countProtectionReferences_") == 0) {
				System.out.println(request.getParameter(paraName));
				countProtectionReferences = request.getParameter(paraName);
			}
			if (paraName.indexOf("protectionReferences_") == 0) {
				protectionReferenceId = request.getParameter(paraName);
			}
			if (paraName.indexOf("protectionReferencesPageS_") == 0) {
				protectionReferencesPageS = request.getParameter(paraName);
			}
			if (paraName.indexOf("protectionReferencesPageE_") == 0) {
				protectionReferencesPageE = request.getParameter(paraName);
			}
		}
		for (int i = 1; i <= Integer.parseInt(countProtectionReferences); i++) {
			if (StringUtils.isNotBlank(protectionReferenceId) && StringUtils.isNotBlank(protectionReferencesPageS) 
					&& StringUtils.isNotBlank(protectionReferencesPageE)) {
				jsonStr = "{"
						+ "\"descriptionReferenceId\"" + ":\"" + protectionReferenceId + "\","
						+ "\"descriptionReferencesPageS\"" + ":\"" + protectionReferencesPageS + "\","
						+ "\"descriptionReferencesPageE\"" + ":\"" + protectionReferencesPageE + "\""
						+ "}";
			}
			JSONObject jsonText = JSON.parseObject(jsonStr);
			jsonArray.add(i - 1, jsonText);
		}
		System.out.println(jsonArray.toJSONString());
		return jsonArray;
	}
	@Override
	public boolean logicRemove(String id) {
		Protection thisProtection = this.protectionRepository.findOneById(id);
		if (null != thisProtection && 1 == thisProtection.getStatus()) {
			thisProtection.setStatus(0);
			this.protectionRepository.save(thisProtection);
			return true;
		}
		return false;
	}
	
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
		String sort = "version";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<Protection> thisList = new ArrayList<>();
		// 获取当前选中Taxon下的Proteciton
		Page<Protection> thisPage = this.protectionRepository.searchByProtlevel(findText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisSelect.put("total_count", thisPage.getTotalElements());
		Boolean incompleteResulte = true;
		if ((thisPage.getTotalElements() / 30) > findPage) {
			incompleteResulte = false;
		}
		thisSelect.put("incompleteResulte", incompleteResulte);
		thisList = thisPage.getContent();
		/*if (findPage == 1) {
			JSONObject row = new JSONObject();
			row.put("id", "addNew");
			row.put("full_name", "新建一个分类单元集");
			items.add(row);
		}*/
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
			row.put("id", thisList.get(i).getId());
			row.put("full_name", thisList.get(i).getProtlevel());
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}
	@Override
	public boolean deleteOne(HttpServletRequest request) {
		String protectionId = request.getParameter("protectionId");
		System.out.println(protectionId);
		if (StringUtils.isNotBlank(protectionId)) {
			if (null != this.protectionRepository.findOneById(protectionId)) {
				this.protectionRepository.deleteOneById(protectionId);
			}
			return true;
		}
		return false;
	}
}
