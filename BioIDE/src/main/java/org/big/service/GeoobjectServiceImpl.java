package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.QueryTool;
import org.big.common.StrEditer;
import org.big.entity.Geoobject;
import org.big.entity.UserDetail;
import org.big.repository.GeoobjectRepository;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GeoobjectServiceImpl implements GeoobjectService{

	@Autowired
	private GeoobjectRepository geoobjectRepository;
	
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
		String sort = "adcode";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<Geoobject> thisList = new ArrayList<>();

		Page<Geoobject> thisPage = this.geoobjectRepository.searchInfo(findText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order),"");
		thisSelect.put("total_count", thisPage.getTotalElements());
		Boolean incompleteResulte = true;
		if ((thisPage.getTotalElements() / 30) > findPage)
			incompleteResulte = false;
		thisSelect.put("incompleteResulte", incompleteResulte);
		thisList = thisPage.getContent();
//		if (findPage == 1) {
//			JSONObject row = new JSONObject();
//			row.put("id", "addNew");
//			row.put("full_name", "新建一个数据源");
//			items.add(row);
//		}
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
			row.put("id", thisList.get(i).getId());
			row.put("full_name", thisList.get(i).getCngeoname()+"("+thisList.get(i).getEngeoname()+")");
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}

	@Override
	public JSON findAdministrativeBySelect(HttpServletRequest request) {
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
		String sort = "adcode";
		String order = "asc";
		JSONObject thisSelect = new JSONObject();
		JSONArray items = new JSONArray();
		List<Geoobject> thisList = new ArrayList<>();

		Page<Geoobject> thisPage = this.geoobjectRepository.searchInfo(findText,
				QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order),"");
		thisSelect.put("total_count", thisPage.getTotalElements());
		Boolean incompleteResulte = true;
		if ((thisPage.getTotalElements() / 30) > findPage)
			incompleteResulte = false;
		thisSelect.put("incompleteResulte", incompleteResulte);
		thisList = thisPage.getContent();
		for (int i = 0; i < thisList.size(); i++) {
			JSONObject row = new JSONObject();
			//获取adcode
			String adcode=thisList.get(i).getAdcode();
			//判断国家
			String nationCode=adcode.substring(1,2);
			//计算省code
			String provinceCode=adcode.substring(0,2);
			//计算市code
			String cityCode=adcode.substring(2,4);
			//计算县code
			String countyCode=adcode.substring(4,6);

			row.put("id", thisList.get(i).getId());
			//判断级别
			if(countyCode.equals("00")){//市级以上
				row.put("full_name", "&nbsp;&nbsp;&nbsp;&nbsp;<b>"+thisList.get(i).getCngeoname()+"("+thisList.get(i).getEngeoname()+")</b>");
				if(cityCode.equals("00")){//
					if(nationCode.equals(0)){//国家
						row.put("full_name", "<i>"+thisList.get(i).getCngeoname()+"("+thisList.get(i).getEngeoname()+")</i>");
					}
					else{//省级
						row.put("full_name", "&nbsp;&nbsp;<b><i>"+thisList.get(i).getCngeoname()+"("+thisList.get(i).getEngeoname()+")</i></b>");
					}
				}
			}
			else{//县级
				row.put("full_name", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+thisList.get(i).getCngeoname()+"("+thisList.get(i).getEngeoname()+")");
			}
			items.add(row);
		}
		thisSelect.put("items", items);
		return thisSelect;
	}

	@Override
	public Boolean buildPy() {
		List<Geoobject> thisList = this.geoobjectRepository.findAll();
		StrEditer strEditer=new StrEditer();
		for (int i = 0; i < thisList.size(); i++) {
			Geoobject thisGeoobject=thisList.get(i);
			try {
				thisGeoobject.setEngeoname(strEditer.getPY(thisGeoobject.getCngeoname()));
				this.geoobjectRepository.save(thisGeoobject);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
