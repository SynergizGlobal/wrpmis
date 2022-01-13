package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.LeftMenueDao;
import com.synergizglobal.pmis.reference.Iservice.LeftMenuService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class LeftMenuServiceImpl implements LeftMenuService{
	@Autowired
	LeftMenueDao dao;

	@Override
	public List<TrainingType> getLeftMenuList(TrainingType obj) throws Exception {
		return dao.getLeftMenuList(obj);
	}

	@Override
	public List<TrainingType> getStatusFilterList(TrainingType obj) throws Exception {
		return dao.getStatusFilterList(obj);
	}

	@Override
	public List<TrainingType> getParentFilterList(TrainingType obj) throws Exception {
		return dao.getParentFilterList(obj);
	}

	@Override
	public boolean addLeftMenu(TrainingType obj) throws Exception {
		return dao.addLeftMenu(obj);
	}

	@Override
	public boolean updateLeftMenu(TrainingType obj) throws Exception {
		return dao.updateLeftMenu(obj);
	}

	@Override
	public boolean deleteLeftMenu(TrainingType obj) throws Exception {
		return dao.deleteLeftMenu(obj);
	}

	@Override
	public List<TrainingType> getParentList() throws Exception {
		return dao.getParentList();
	}

	@Override
	public List<TrainingType> getStatusList() throws Exception {
		return dao.getStatusList();
	}
}
