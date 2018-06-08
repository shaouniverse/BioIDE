package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.big.common.QueryTool;
import org.big.entity.Rank;
import org.big.entity.Taxon;
import org.big.entity.UserDetail;
import org.big.repository.RankRepository;
import org.big.repository.TaxonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Service
public class TaxonServiceImpl implements TaxonService {
	@Autowired
	private TaxonRepository taxonRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private RankRepository rankRepository;
	
	@Override
	public JSON findTaxonList(HttpServletRequest request) {
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
		List<Taxon> thisList = new ArrayList<>();
		Page<Taxon> thisPage = this.taxonRepository.searchInfo(searchText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','taxon')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','taxon')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("scientificname", "<a href=\"console/taxon/show/" + thisList.get(i).getId() + "\"><em>" + thisList.get(i).getScientificname() + "</em></a>");
			row.put("authorstr", thisList.get(i).getAuthorstr());
			row.put("epithet", thisList.get(i).getEpithet());
			row.put("inputer", this.userService.findbyID(thisList.get(i).getInputer()).getNickname());
			String rankId = thisList.get(i).getRank().getId();
			Rank thisRank = this.rankRepository.findOneById(rankId);
			row.put("rankid", thisRank.getEnname() + " | " + thisRank.getChname());
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
	public JSON addTaxonBaseInfo(@Valid Taxon thisTaxon, HttpServletRequest request) {
		JSONObject thisResult = new JSONObject();
		try {
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisTaxon.setInputer(thisUser.getId());
			thisTaxon.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisTaxon.setStatus(1);
			thisTaxon.setSynchstatus(0);
			thisTaxon.setRefjson(handleReferenceToJson(request).toJSONString());
			this.taxonRepository.save(thisTaxon);
			
			thisResult.put("result", true);
		} catch (Exception e) {
			thisResult.put("result", false);
		}
		return thisResult;
	}

	@Override
	public Taxon findOneById(String id) {
		return this.taxonRepository.findOneById(id);
	}

	@Override
	public JSON handleReferenceToJson(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
    	String countReferences = (String) request.getParameter("countReferences");
		String referencesId = null;
		String referencesPageS = null;
		String referencesPageE = null;
		String referencesType = null;
		String jsonStr = null;
		for (int i = 1; i <= Integer.parseInt(countReferences); i++) {
			referencesId = (String) request.getParameter("references_" + i);
			referencesPageS = (String) request.getParameter("referencesPageS_" + i);
			referencesPageE = (String) request.getParameter("referencesPageE_" + i);
			referencesType = (String) request.getParameter("referencesType_" + i);
			if (StringUtils.isNotBlank(referencesId) && StringUtils.isNotBlank(referencesPageS)
					&& StringUtils.isNotBlank(referencesPageE) && StringUtils.isNotBlank(referencesType)) {
				jsonStr = "{"
						+ "\"referencesId\"" + ":\"" + referencesId + "\","
						+ "\"referencesPageS\"" + ":\"" + referencesPageS + "\"," 
						+ "\"referencesPageE\"" + ":\"" + referencesPageE + "\","
						+ "\"referencesType\"" + ":\"" + referencesType + "\""
						+ "}";
			}
			JSONObject jsonText = JSON.parseObject(jsonStr);
			jsonArray.add(i - 1, jsonText);
		}
		return jsonArray;
	}

	@Override
	public boolean logicRemove(String id) {
		Taxon thisTaxon = this.taxonRepository.findOneById(id);
		if (null != thisTaxon && 1 == thisTaxon.getStatus()) {
			thisTaxon.setStatus(0);
			this.taxonRepository.save(thisTaxon);
			return true;
		}
		return false;
	}

	@Override
	public void updateOneById(@Valid Taxon thisTaxon) {
		thisTaxon.setSynchdate(new Timestamp(System.currentTimeMillis()));
		this.taxonRepository.save(thisTaxon);
	}

}
