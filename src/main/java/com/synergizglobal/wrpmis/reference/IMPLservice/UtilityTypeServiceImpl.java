package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.UtilityTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.UtilityTypeService;
import com.synergizglobal.wrpmis.reference.model.Safety;
@Service
public class UtilityTypeServiceImpl implements UtilityTypeService{

	@Autowired
	UtilityTypeDao dao;

	@Override
	public Safety getUtilityTypesList(Safety obj) throws Exception {
		return dao.getUtilityTypesList(obj);
	}
	@Override
	public List<Safety> getUtilityTypesList() throws Exception {
		return dao.getUtilityTypesList();
	}	

	@Override
	public boolean addUtilityType(Safety obj) throws Exception {
		return dao.addUtilityType(obj);
	}
	@Override
	public boolean updateUtilityType(Safety obj) throws Exception {
		return dao.updateUtilityType(obj);
	}
	@Override
	public boolean deleteUtilityType(Safety obj) throws Exception {
		return dao.deleteUtilityType(obj);
	}
}
