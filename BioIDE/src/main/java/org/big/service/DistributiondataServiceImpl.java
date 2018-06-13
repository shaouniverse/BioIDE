package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.big.common.QueryTool;
import org.big.entity.Distributiondata;
import org.big.entity.UserDetail;
import org.big.repository.DistributiondataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class DistributiondataServiceImpl implements DistributiondataService {
	@Autowired
	private DistributiondataRepository distributiondataRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private TaxonService taxonService;
	
	@Override
	public JSON findDistributiondataList(HttpServletRequest request) {
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
		List<Distributiondata> thisList = new ArrayList<>();
		Page<Distributiondata> thisPage = this.distributiondataRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
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
			row.put("lng", "<a href=\"console/distributiondata/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getLng() + "</a>");
			row.put("lat", thisList.get(i).getLat());
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
	public JSON addDistributiondata(Distributiondata thisDistributiondata, HttpServletRequest request) {
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("relation_") == 0) {
				thisDistributiondata.setLng(Double.parseDouble(request.getParameter(paraName)));
			}
			if (paraName.indexOf("rightsholder_") == 0) {
				thisDistributiondata.setLat(Double.parseDouble(request.getParameter(paraName)));
			}
		}
		thisDistributiondata.setInputtime(new Timestamp(System.currentTimeMillis()));
		
		JSONObject thisResult = new JSONObject();
		try {
			thisDistributiondata.setId(UUID.randomUUID().toString());
			UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			thisDistributiondata.setInputer(thisUser.getId());
			thisDistributiondata.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisDistributiondata.setStatus(1);
			thisDistributiondata.setSynchstatus(0);
			thisDistributiondata.setRefjson(handleReferenceToJson(request).toJSONString());
			String taxonId = (String) request.getSession().getAttribute("taxonId");
			thisDistributiondata.setTaxon(taxonService.findOneById(taxonId));
			
			this.distributiondataRepository.save(thisDistributiondata);
			thisResult.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			thisResult.put("result", false);
		}
		return thisResult;
	}

	@Override
	public JSON handleReferenceToJson(HttpServletRequest request) {
		return null;
	}

	@Override
	public boolean logicRemove(String id) {
		Distributiondata thisDistributiondata = this.distributiondataRepository.findOneById(id);
		if (null != thisDistributiondata && 1 == thisDistributiondata.getStatus()) {
			thisDistributiondata.setStatus(0);
			this.distributiondataRepository.save(thisDistributiondata);
			return true;
		}
		return false;
	}
}
