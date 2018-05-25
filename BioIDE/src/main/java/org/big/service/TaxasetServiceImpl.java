package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.big.common.QueryTool;
import org.big.entity.Taxaset;
import org.big.entity.UserDetail;
import org.big.repository.TaxasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class TaxasetServiceImpl implements TaxasetService {
	@Autowired
	private TaxasetRepository taxasetRepository;
	
	@Override
	public JSON newOne(Taxaset thisTaxaset) {
		JSONObject thisResult = new JSONObject();
		try {
			String id = UUID.randomUUID().toString();
			thisTaxaset.setId(id);
			thisTaxaset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			thisTaxaset.setSynchdate(new Timestamp(System.currentTimeMillis()));
			thisTaxaset.setStatus((byte) 1);

			this.taxasetRepository.save(thisTaxaset);
			thisResult.put("result", true);
			thisResult.put("newId", this.taxasetRepository.findOneById(id).getId());
			thisResult.put("newTsname", this.taxasetRepository.findOneById(id).getTsname());
		} catch (Exception e) {
			thisResult.put("result", false);
		}
		return thisResult;
	}
	
	@Override
	public void saveOne(Taxaset thisTaxaset) {
		thisTaxaset.setId(UUID.randomUUID().toString());
		thisTaxaset.setStatus(1);
		thisTaxaset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		thisTaxaset.setSynchdate(new Timestamp(System.currentTimeMillis()));
		
		this.taxasetRepository.save(thisTaxaset);
	}

	@Override
	public void removeOne(String Id) {
		this.taxasetRepository.deleteOneById(Id);
	}

	@Override
	public boolean logicRemove(String id) {
		Taxaset thisTaxaset = this.taxasetRepository.findOneById(id);
		if (null != thisTaxaset && 1 == thisTaxaset.getStatus()) {
			thisTaxaset.setStatus(0);
			this.taxasetRepository.save(thisTaxaset);
			return true;
		}
		return false;
	}
	
	@Override
	public void updateOneById(Taxaset thisTaxaset) {
		thisTaxaset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		thisTaxaset.setSynchdate(new Timestamp(System.currentTimeMillis()));
		this.taxasetRepository.save(thisTaxaset);
	}

	@Override
	public Taxaset findOneById(String Id) {
		return this.taxasetRepository.findOneById(Id);
	}

	@Override
	public Taxaset findOneByTsname(String tsname) {
		return this.taxasetRepository.findOneByTsname(tsname);
	}

	@Override
	public JSON findTaxasetList(HttpServletRequest request) {
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
			sort = "synchdate";
		}
		if (order == null || order.length() <= 0) {
			order = "desc";
		}
		JSONObject thisTable = new JSONObject();
		JSONArray rows = new JSONArray();
		List<Taxaset> thisList = new ArrayList<>();
		Page<Taxaset> thisPage = this.taxasetRepository.searchInfo(searchText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order), dsId);
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','taxaset')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','taxaset')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("tsname", "<a href=\"console/taxaset/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getTsname() + "</a>");
			row.put("tsinfo", thisList.get(i).getTsinfo());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addTime = "";
			try {
				addTime = formatter.format(thisList.get(i).getCreatedDate());
			} catch (Exception e) {
			}
			row.put("createdDate", addTime);
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }
		
	@Override
	public List<Taxaset> findTaxasetsByDatasetId(String dsId) {
		return this.taxasetRepository.findTaxasetsByDatasetId(dsId);
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
		String sort = "tsname";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<Taxaset> thisList = new ArrayList<>();
		// 获取当前选中Dataset下的Taxaset
		String dsId = (String) request.getSession().getAttribute("datasetID");
		Page<Taxaset> thisPage = this.taxasetRepository.searchByTsname(findText, dsId,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisSelect.put("total_count", thisPage.getTotalElements());
		Boolean incompleteResulte = true;
		if ((thisPage.getTotalElements() / 30) > findPage) {
			incompleteResulte = false;
		}
		thisSelect.put("incompleteResulte", incompleteResulte);
		thisList = thisPage.getContent();
		if (findPage == 1) {
			JSONObject row = new JSONObject();
			row.put("id", "addNew");
			row.put("full_name", "新建一个分类单元集");
			items.add(row);
		}
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
			row.put("id", thisList.get(i).getId());
			row.put("full_name", thisList.get(i).getTsname());
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}
}
