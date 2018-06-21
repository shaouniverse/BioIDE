package org.big.service;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.big.entity.Traitdata;
import org.big.entity.UserDetail;
import org.big.repository.TraitdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class TraitdataServiceImpl implements TraitdataService {
	@Autowired
	private TraitdataRepository traitdataRepository;
	@Autowired
	private TaxonService taxonService;
	
	@Override
	public JSON findBySelect(HttpServletRequest request) {
		return null;
	}

	@Override
	public JSON addTraitdata(Traitdata thisTraitdata, HttpServletRequest request) {
		// 关系(关系类型)
				Enumeration<String> paraNames = request.getParameterNames();
				String paraName = null;
				while (paraNames.hasMoreElements()) {
					paraName = (String) paraNames.nextElement();
					if (paraName.indexOf("trainsetid_") == 0) {
						thisTraitdata.setId(request.getParameter(paraName));
					}
					
					if (paraName.indexOf("trainsetid_") == 0) {
						thisTraitdata.setTrainsetid(request.getParameter(paraName));
					}
					/*if (paraName.indexOf("trainSourcesid_") == 0) {
						thisTraitdata.setTraitjson(request.getParameter(paraName));
					}*/
					if (paraName.indexOf("desid_") == 0) {
						thisTraitdata.setDesid(request.getParameter(paraName));
					}
				}
				thisTraitdata.setInputtime(new Timestamp(System.currentTimeMillis()));
				
				JSONObject thisResult = new JSONObject();
				try {
					UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					thisTraitdata.setInputer(thisUser.getId());
					thisTraitdata.setSynchdate(new Timestamp(System.currentTimeMillis()));
					thisTraitdata.setStatus(1);
					thisTraitdata.setSynchstatus(0);
					thisTraitdata.setRefjson(handleReferenceToJson(request).toJSONString());
					String taxonId = (String) request.getSession().getAttribute("taxonId");
					thisTraitdata.setTaxon(taxonService.findOneById(taxonId));
					
					
					this.traitdataRepository.save(thisTraitdata);
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
		String countTraitdataReferences = null;
		String traitdataReferencesPageE = null;
		String traitdataReferencesPageS = null;
		String traitdataReferenceId = null;
		String jsonStr = null;
		
		while (paraNames.hasMoreElements()) {
			paraName = (String) paraNames.nextElement();
			if (paraName.indexOf("countTraitdataReferences_") == 0) {
				countTraitdataReferences = request.getParameter(paraName);
			}
			if (paraName.indexOf("traitdataReferences_") == 0) {
				traitdataReferenceId = request.getParameter(paraName);
			}
			
			if (paraName.indexOf("traitdataReferencesPageS_") == 0) {
				traitdataReferencesPageS = request.getParameter(paraName);
			}
			if (paraName.indexOf("traitdataReferencesPageE_") == 0) {
				traitdataReferencesPageE = request.getParameter(paraName);
			}
		}
		for (int i = 1; i <= Integer.parseInt(countTraitdataReferences); i++) {
			if (StringUtils.isNotBlank(traitdataReferenceId) && StringUtils.isNotBlank(traitdataReferencesPageS) 
					&& StringUtils.isNotBlank(traitdataReferencesPageE)) {
				jsonStr = "{"
						+ "\"traitdataReferenceId\"" + ":\"" + traitdataReferenceId + "\","
						+ "\"traitdataReferencesPageS\"" + ":\"" + traitdataReferencesPageS + "\","
						+ "\"traitdataReferencesPageE\"" + ":\"" + traitdataReferencesPageE + "\""
						+ "}";
			}
			JSONObject jsonText = JSON.parseObject(jsonStr);
			jsonArray.add(i - 1, jsonText);
		}
		return jsonArray;
	}

	@Override
	public boolean deleteOne(HttpServletRequest request) {
		String traitdataId = request.getParameter("traitdataId");
		if (StringUtils.isNotBlank(traitdataId)) {
			if (null != this.traitdataRepository.findOneById(traitdataId)) {
				this.traitdataRepository.deleteOneById(traitdataId);
			}
			return true;
		}
		return false;
	}

}
