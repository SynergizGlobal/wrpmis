package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WorkDao;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Year;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	WorkDao workDao;
	
	@Override
	public Work getWork(String workId,Work work)throws Exception{
		return workDao.getWork(workId, work);
	}
	
	@Override
	public boolean updateWork(Work work)throws Exception{
		return workDao.updateWork(work);
	}
	
	@Override
	public List<Work> getWorkStatusList(Work obj) throws Exception {
		return workDao.getWorkStatusList(obj);
	}
	@Override
	public boolean addWork(Work work)throws Exception{
		return workDao.addWork(work);
	}
	@Override
	public List<Railway> getRailwayList()throws Exception{
		return workDao.getRailwayList();
	}
	@Override
	public List<Railway> getExcecuteList()throws Exception{
		return workDao.getExcecuteList();
	}
	@Override
	public boolean deleteRow(String workId,Work work)throws Exception{
		return workDao.deleteRow(workId, work);
	}
	
	@Override
	public List<Year> getYearList()throws Exception{
		return workDao.getYearList();

	}
	@Override
	public List<Work> getWorkList(Work work)throws Exception{
		return workDao.getWorkList(work);

	}
	/**
	@Override
	public int getTotalRecords(Work obj, String searchParameter) throws Exception {
		return workDao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Work> getWorksList(Work obj, int startIndex, int offset, String searchParameter) throws Exception {
		return workDao.getWorksList(obj,startIndex,offset,searchParameter);
	}
*/

	@Override
	public List<Work> getWorkRevisionsList(Work obj) throws Exception {
		return workDao.getWorkRevisionsList(obj);
	}

	@Override
	public List<Work> getWorkFileTypes() throws Exception {
		return workDao.getWorkFileTypes();
	}

	@Override
	public List<Work> getWorksList(Work obj) throws Exception {
		return workDao.getWorksList(obj);
	}

	@Override
	public List<Work> getWorktProjectsList(Work obj) throws Exception {
		return workDao.getWorktProjectsList(obj);
	}

	@Override
	public List<Work> getUnitsList() throws Exception {
		return workDao.getUnitsList();
	}

	@Override
	public List<Work> getWorkTypeList() throws Exception {
		return workDao.getWorkTypeList();
	}

	@Override
	public List<Work> getworkCodeList(Work obj) throws Exception {
		return workDao.getworkCodeList(obj);
	}
}
