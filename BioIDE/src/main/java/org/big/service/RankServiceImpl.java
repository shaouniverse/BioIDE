package org.big.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.big.common.BuildEntity;
import org.big.common.QueryTool;
import org.big.entity.Rank;
import org.big.entity.UserDetail;
import org.big.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class RankServiceImpl implements RankService {
	@Autowired
	private RankRepository rankRepository;
	
	@Override
	public void saveOne(Rank thisRank) {
		this.rankRepository.save(thisRank);
	}

	@Override
	public void removeOne(String Id) {
		this.rankRepository.deleteOneById(Id);
	}

	@Override
	public void updateOneById(Rank thisRank) {
		this.rankRepository.save(thisRank);
	};
	
	@Override
	public Rank findOneById(String Id) {
		return this.rankRepository.findOneById(Id);
	}

	@Override
	public Rank findOneByEnname(String EnName) {
		return this.rankRepository.findOneByEnname(EnName);
	}
	
//	@Override
//	public JSON findRankSortData(HttpServletRequest request) {
//		String findText = request.getParameter("find");
//		if (findText == null || findText.length() <= 0) {
//			findText = "";
//		}
//		int findPage = 1;
//		try {
//			findPage = Integer.valueOf(request.getParameter("page"));
//		} catch (Exception e) {
//		}
//		int limit_serch = 30;
//		int offset_serch = (findPage - 1) * 30;
//		String sort = "sort";
//		String order = "asc";
//		// 创建JSONObject对象
//		JSONObject thisSelect = new JSONObject();
//		// 创建JSON数组
//		JSONArray items = new JSONArray();
//		List<Object> thisList = new ArrayList<>();
//        // 获取当前登录用户
//		UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // 传入页码起始页、页面大小、排序字段和排序类型关键参数返回SpringData规定排序PageRequest类型
//		Page<Object> thisPage = this.rankRepository.findRankSortData(findText, thisUser.getUserName(), QueryTool.buildPageRequest(offset_serch, limit_serch, sort, order));
//		thisSelect.put("total_count", thisPage.getTotalElements()); // 总记录数
//		Boolean incompleteResulte = true;
//
//		if ((thisPage.getTotalElements() / 30) > findPage) {
//			incompleteResulte = false;
//		}
//		thisSelect.put("incompleteResulte", incompleteResulte);
//		thisList = thisPage.getContent();
//
//       /* AllUsers
//        if(findPage == 1){
//            JSONObject row = new JSONObject();
//            row.put("id","");
//            row.put("full_name", messageSource.getMessage("all_udata", null, LocaleContextHolder.getLocale()));
//            items.add(0,row);
//        }
//        */
//        
//		for (int i = 0; i < thisList.size(); i++) {
//			JSONObject row = new JSONObject();
//			row.put("id", BuildEntity.buildUdataNameString(thisList.get(i)));
//			row.put("full_name", BuildEntity.buildUdataString(thisList.get(i)));
//			items.add(row);
//		}
//		
//		thisSelect.put("items", items);
//		return thisSelect;
//    }
}
