package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.FOBDao;
import com.synergizglobal.pmis.Iservice.FOBService;
import com.synergizglobal.pmis.model.FOB;

@Service
public class FOBServiceImpl implements FOBService {
	
	@Autowired
	FOBDao fobDao;
	
	@Override
	public List<FOB> getFOBList(FOB obj) throws Exception {
		return fobDao.getFOBList(obj);
	}

	@Override
	public boolean addFOB(FOB obj) throws Exception {
		return fobDao.addFOB(obj);
	}

	@Override
	public FOB getFOB(FOB obj) throws Exception {
		return fobDao.getFOB(obj);
	}

	@Override
	public boolean updateFOB(FOB obj) throws Exception {
		return fobDao.updateFOB(obj);
	}

	@Override
	public boolean deleteFOB(FOB obj) throws Exception {
		return fobDao.deleteFOB(obj);
	}

}
