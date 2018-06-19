package org.big.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.big.common.QueryTool;
import org.big.entity.Keyitem;
import org.big.entity.Taxkey;
import org.big.entity.Taxon;
import org.big.repository.KeyitemRepository;
import org.big.repository.TaxkeyRepository;
import org.big.repository.TaxonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class TaxkeyServiceImpl implements TaxkeyService {
	@Autowired
	private TaxkeyRepository taxkeyRepository;
	@Autowired
	private TaxonRepository taxonRespository;
	@Autowired
	private KeyitemRepository keyitemRepository;
	
	
	@Override
	public JSON findTaxkeyList(HttpServletRequest request) {
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
		List<Taxkey> thisList = new ArrayList<>();
		Page<Taxkey> thisPage = this.taxkeyRepository.searchInfo(searchText, QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
		thisTable.put("total", thisPage.getTotalElements());
		thisList = thisPage.getContent();
		String thisSelect = "";
		String thisEdit = "";
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
	        thisSelect="<input type='checkbox' name='checkbox' id='sel_" + thisList.get(i).getId() + "' />";
	        thisEdit=
	        	 "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('" + thisList.get(i).getId() + "','taxkey')\" >" +
	             "<span class=\"glyphicon glyphicon-edit\"></span>" +
	             "</a>" +
	             "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('" + thisList.get(i).getId() + "','taxkey')\" >" +
	             "<span class=\"glyphicon glyphicon-remove\"></span>" +
	             "</a>";
			row.put("select", thisSelect);
			row.put("keytitle", "<a href=\"console/description/show/" + thisList.get(i).getId() + "\">" + thisList.get(i).getKeytitle() + "</a>");
			row.put("abstraction", thisList.get(i).getAbstraction());
			row.put("edit", thisEdit);
			rows.add(i, row);
		}
		thisTable.put("rows", rows);
		json = thisTable;
		return json;
    }

	@Override
	public JSON addTaxkey(Taxkey thisTaxkey, HttpServletRequest request) {
		Keyitem thisKeyitem = new Keyitem();
		
		Enumeration<String> paraNames = request.getParameterNames();
		String paraName = null;
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("taxkeyId_") == 0) {
				thisTaxkey.setId(request.getParameter(paraName));
			}
			if (paraName.indexOf("keytitle_") == 0) {
				thisTaxkey.setKeytitle(request.getParameter(paraName));
			}
			if (paraName.indexOf("abstraction_") == 0) {
				thisTaxkey.setAbstraction(request.getParameter(paraName));
			}
			if (paraName.indexOf("keytype_") == 0) {
				thisTaxkey.setKeytype(request.getParameter(paraName));
			}
			if (paraName.indexOf("taxonid_") == 0) {
				Taxon thisTaxon = taxonRespository.findOneById(request.getParameter(paraName));
				thisTaxkey.setTaxon(thisTaxon);
				thisKeyitem.setTaxonid(request.getParameter(paraName));
			}
			// -----------------------------------------------
			if (paraName.indexOf("keyitemId_") == 0) {
				thisKeyitem.setId(request.getParameter(paraName));
			}
			if (paraName.indexOf("item_") == 0) {
				thisKeyitem.setItem(request.getParameter(paraName));
			}
			if (paraName.indexOf("innerorder_") == 0) {
				if (StringUtils.isNotBlank(request.getParameter(paraName))) {
					thisKeyitem.setInnerorder(Integer.parseInt(request.getParameter(paraName)));
				}
			}
			if (paraName.indexOf("branchid_") == 0) {
				thisKeyitem.setBranchid(Integer.parseInt(request.getParameter(paraName)));
			}
			if (paraName.indexOf("keytype_") == 0) {
				thisKeyitem.setOrderid(Integer.parseInt(request.getParameter(paraName)));
			}
			
			thisKeyitem.setTaxkey(thisTaxkey);
		}
		
		JSONObject thisResult = new JSONObject();
		try {
			/*String taxonId = (String) request.getSession().getAttribute("taxonId");
			thisTaxkey.setTaxon(taxonRespository.findOneById(taxonId));*/
			
			this.taxkeyRepository.save(thisTaxkey);
			this.keyitemRepository.save(thisKeyitem);
			thisResult.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			thisResult.put("result", false);
		}
		return thisResult;
	}
	

	@Override
	public boolean deleteOneById(String id) {
		if (null != this.taxkeyRepository.findOneById(id)) {
			this.taxkeyRepository.deleteOneById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteOne(HttpServletRequest request) {
		String taxkeyId = request.getParameter("taxkeyId");
		if (StringUtils.isNotBlank(taxkeyId)) {
			if (null != this.taxkeyRepository.findOneById(taxkeyId)) {
				this.taxkeyRepository.deleteOneById(taxkeyId);
			}
			return true;
		}
		return false;
	}


}
