package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.big.common.QueryTool;
import org.big.entity.Taxaset;
import org.big.repository.TaxasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class TaxasetServiceImpl implements TaxasetService {
	@Autowired
	private TaxasetRepository taxasetRepository;
	
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
}
