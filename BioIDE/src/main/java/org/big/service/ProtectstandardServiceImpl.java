package org.big.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.big.common.QueryTool;
import org.big.entity.Protectstandard;
import org.big.repository.ProtectstandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class ProtectstandardServiceImpl implements ProtectstandardService {
	@Autowired
	private ProtectstandardRepository protectstandardRepository;
		
	@Override
	public JSON findProtectstandardList(HttpServletRequest request) {
		return null;
	}

	@Override
	public JSON addProtectstandard(Protectstandard thisProtectstandard, HttpServletRequest request) {
		return null;
	}

	@Override
	public boolean logicRemove(String id) {
		return false;
	}

	@Override
	public JSON findBySelectStandard(HttpServletRequest request) {
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
		String sort = "standardname";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<String> thisList = new ArrayList<>();
		// 获取当前选中Dataset下的Taxaset
		Page<String> thisPage = this.protectstandardRepository.searchByStandardname(findText,
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
			row.put("id", thisList.get(i));
			row.put("full_name", thisList.get(i));
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}

	@Override
	public JSON findBySelectVersion(HttpServletRequest request, String standardname) {
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
		List<String> thisList = new ArrayList<>();
		// 获取当前选中Dataset下的Taxaset
		Page<String> thisPage = this.protectstandardRepository.searchByVersion(findText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order), standardname);
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
			row.put("id", thisList.get(i));
			row.put("full_name", thisList.get(i));
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}

	@Override
	public JSON findBySelectProtlevel(HttpServletRequest request, String version) {
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
		String sort = "protlevel";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<String> thisList = new ArrayList<>();
		// 获取当前选中Dataset下的Taxaset
		Page<String> thisPage = this.protectstandardRepository.searchByProtlevel(findText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order), version);
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
			row.put("id", thisList.get(i));
			row.put("full_name", thisList.get(i));
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}
}
