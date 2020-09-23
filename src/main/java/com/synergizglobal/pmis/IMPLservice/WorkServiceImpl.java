package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WorkDao;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	WorkDao workDao;
	
	@Override
	public List<Work> getworkList()throws Exception{
		return workDao.getworkList();
	}
	
	@Override
	public Work editWork(String workId,Work work)throws Exception{
		return workDao.editWork(workId, work);
	}
	
	@Override
	public boolean updateWork(Work work)throws Exception{
		return workDao.updateWork(work);
	}
	
	@Override
	public boolean addWork(Work work)throws Exception{
		return workDao.addWork(work);
	}
	@Override
	public List<Railway> getRailwayList()throws Exception{
		return workDao.getRailwayList();
	}

}
