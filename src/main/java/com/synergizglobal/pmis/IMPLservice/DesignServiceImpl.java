package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DesignDao;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Safety;

@Service
public class DesignServiceImpl implements DesignService{
	
	@Autowired
	DesignDao designDao;
	
	@Override
	public List<Design> structureList()throws Exception{
		return designDao.structureList();
	}
	
	@Override
	public List<Design> design(Design obj)throws Exception{
		return designDao.design(obj);
	}
	
	@Override
	public List<Design> drawingTypeList()throws Exception{
	return designDao.drawingTypeList();
	}
	
	@Override
	public List<Design> getDepartmentList()throws Exception{
		return designDao.getDepartmentList();
	}

}
