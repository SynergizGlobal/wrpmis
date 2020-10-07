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
	public List<Design> structureList() throws Exception{
		return designDao.structureList();
	}
	
	@Override
	public List<Design> getDesigns(Design obj) throws Exception{
		return designDao.getDesigns(obj);
	}
	
	@Override
	public List<Design> drawingTypeList() throws Exception{
	return designDao.drawingTypeList();
	}
	
	@Override
	public List<Design> getDepartmentList() throws Exception{
		return designDao.getDepartmentList();
	}
	@Override
	public Design getDesignDetails(Design obj) throws Exception{
		return designDao.getDesignDetails(obj);
	}
	@Override
	public List<Design> getContractList() throws Exception{
		return designDao.getContractList();
	}
	
	@Override
	public List<Design> getPreparedByList() throws Exception{
		return designDao.getPreparedByList();
	}
	
	@Override
	public List<Design> getRevisionStatuses() throws Exception{
		return designDao.getRevisionStatuses();
	}

	@Override
	public boolean addDesign(Design obj) throws Exception {
		return designDao.addDesign(obj);
	}

	@Override
	public boolean updateDesign(Design obj) throws Exception {
		return designDao.updateDesign(obj);
	}

}
