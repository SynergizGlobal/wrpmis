package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.UtilityStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.UtilityStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
@Service
public class UtilityStatusServiceImpl implements UtilityStatusService{


	@Autowired
	UtilityStatusDao dao;

	@Override
	public Safety getUtilityStatusList(Safety obj) throws Exception {
		return dao.getUtilityStatusList(obj);
	}
	@Override
	public List<Safety> getUtilityStatusList() throws Exception {
		return dao.getUtilityStatusList();
	}	

	@Override
	public boolean addUtilityStatus(Safety obj) throws Exception {
		return dao.addUtilityStatus(obj);
	}
	@Override
	public boolean updateUtilityStatus(Safety obj) throws Exception {
		return dao.updateUtilityStatus(obj);
	}
	@Override
	public boolean deleteUtilityStatus(Safety obj) throws Exception {
		return dao.deleteUtilityStatus(obj);
	}
}
