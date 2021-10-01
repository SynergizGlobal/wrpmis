package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.FOBDao;
import com.synergizglobal.pmis.Iservice.FOBService;
import com.synergizglobal.pmis.model.Contract;
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

	/*
	 * @Override public List<FOB> contractListFromFOB() throws Exception { return
	 * fobDao.contractListFromFOB(); }
	 */
	@Override
	public List<FOB> getWorkStatusList(FOB obj) throws Exception {
		return fobDao.getWorkStatusList(obj);
	}

	@Override
	public List<FOB> getWorksListForFilter(FOB obj) throws Exception {
		return fobDao.getWorksListForFilter(obj);
	}
	
	@Override
	public List<FOB> getContractsListForFilter(FOB obj) throws Exception {
		return fobDao.getContractsListForFilter(obj);
	}

	@Override
	public List<FOB> getProjectsListForFOBForm(FOB obj) throws Exception {
		return fobDao.getProjectsListForFOBForm(obj);
	}

	@Override
	public List<FOB> getWorkListForFOBForm(FOB obj) throws Exception {
		return fobDao.getWorkListForFOBForm(obj);
	}

	@Override
	public List<FOB> getContractsListForFOBForm(FOB obj) throws Exception {
		return fobDao.getContractsListForFOBForm(obj);
	}
	/**
		@Override
		public List<FOB> getFOBsList(FOB obj, int startIndex, int offset, String searchParameter) throws Exception {
			return fobDao.getFOBsList(obj,startIndex,offset,searchParameter);
		}
	
		@Override
		public int getTotalRecords(FOB obj, String searchParameter) throws Exception {
			return fobDao.getTotalRecords(obj,searchParameter);
		}
	*/
	@Override
	public List<FOB> getFobDetailsList(FOB obj) throws Exception {
		return fobDao.getFobDetailsList(obj);
	}

	@Override
	public List<FOB> getFOBDetails(FOB fob) throws Exception {
		return fobDao.getFOBDetails(fob);
	}

	@Override
	public List<FOB> getFobFileTypesList(FOB obj) throws Exception {
		return fobDao.getFobFileTypesList(obj);
	}

	@Override
	public List<FOB> getFobIdCheck(FOB obj) throws Exception {
		return fobDao.getFobIdCheck(obj);
	}

	@Override
	public List<FOB> getFobDetailsLocations(FOB obj) throws Exception {
		return fobDao.getFobDetailsLocations(obj);
	}

	@Override
	public List<FOB> getFobDetailsTypes(FOB obj) throws Exception {
		return fobDao.getFobDetailsTypes(obj);
	}

	@Override
	public List<FOB> getResponsiblePeopleListForFOBForm(FOB obj) throws Exception {
		return fobDao.getResponsiblePeopleListForFOBForm(obj);
	}

	@Override
	public List<FOB> getUnitsList(FOB obj) throws Exception {
		return fobDao.getUnitsList(obj);
	}

	@Override
	public List<FOB> getFobFileTypeList(FOB obj) throws Exception {
		return fobDao.getFobFileTypeList(obj);
	}
	
	@Override
	public List<FOB> getResponsibleExecutives(FOB obj) throws Exception {
		return fobDao.getResponsibleExecutives(obj);
	}	

}
