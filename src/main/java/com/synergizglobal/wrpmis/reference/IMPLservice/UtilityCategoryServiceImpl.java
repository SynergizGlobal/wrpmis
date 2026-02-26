package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.UtilityCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.UtilityCategoryService;
import com.synergizglobal.wrpmis.reference.model.Safety;
@Service
public class UtilityCategoryServiceImpl implements UtilityCategoryService{

	@Autowired
	 UtilityCategoryDao dao;

	@Override
	public Safety getUtilityCategorysList(Safety obj) throws Exception {
		return dao.getUtilityCategorysList(obj);
	}
	@Override
	public List<Safety> getUtilityCategorysList() throws Exception {
		return dao.getUtilityCategorysList();
	}	

	@Override
	public boolean addUtilityCategory(Safety obj) throws Exception {
		return dao.addUtilityCategory(obj);
	}
	@Override
	public boolean updateUtilityCategory(Safety obj) throws Exception {
		return dao.updateUtilityCategory(obj);
	}
	@Override
	public boolean deleteUtilityCategory(Safety obj) throws Exception {
		return dao.deleteUtilityCategory(obj);
	}	
}
