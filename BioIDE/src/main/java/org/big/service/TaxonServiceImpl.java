package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.common.QueryTool;
import org.big.entity.Taxon;
import org.big.entity.UserDetail;
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
			row.put("scientificname", "<a href=\"console/taxon/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getScientificname() + "</a>");
			row.put("authorstr", thisList.get(i).getAuthorstr());
			row.put("epithet", thisList.get(i).getEpithet());
			row.put("inputer", thisList.get(i).getInputer());
			row.put("rankid", thisList.get(i).getRankid());
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
	public JSON addTaxonBaseInfo(@Valid Taxon thisTaxon) {
		JSONObject thisResult = new JSONObject();
		try {
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisTaxon.setInputer(thisUser.getId());
			thisTaxon.setInputtime(new Timestamp(System.currentTimeMillis()));
			thisTaxon.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisTaxon.setStatus(1);
			thisTaxon.setSynchstatus(0);
			this.taxonRepository.save(thisTaxon);
			
			thisResult.put("result", true);
		} catch (Exception e) {
			thisResult.put("result", false);
		}
		return thisResult;
	}

	
	
}
