package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.FortnightPlanDao;
import com.synergizglobal.pmis.Iservice.FortnightPlanService;
import com.synergizglobal.pmis.model.FortnightPlan;

@Service
public class FortnightPlanServiceImpl implements FortnightPlanService {
	@Autowired
	FortnightPlanDao fortnightPlanDao;
	
	@Override
	public List<FortnightPlan> getFortnightPlanList(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getFortnightPlanList(obj);
	}

	@Override
	public List<FortnightPlan> getFortnightPlanCategoryList() throws Exception {
		return fortnightPlanDao.getFortnightPlanCategoryList();
	}

	@Override
	public List<FortnightPlan> getFortnightPlanModuleCategoryList() throws Exception {
		return fortnightPlanDao.getFortnightPlanModuleCategoryList();
	}
	
	@Override
	public List<FortnightPlan> getFortnightPlan(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getFortnightPlan(obj);
	}

	@Override
	public List<FortnightPlan> getFortnightPlanManual(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getFortnightPlanManual(obj);
	}
	
	@Override
	public boolean updateFortnightPlan(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.updateFortnightPlan(obj);
	}
	@Override
	public boolean updateFortnightlyPlan(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.updateFortnightlyPlan(obj);
	}	
	@Override
	public int getTotalRecords(FortnightPlan obj, String searchParameter) throws Exception {
		return fortnightPlanDao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<FortnightPlan> getFortnightPlanList(FortnightPlan obj, int startIndex, int offset, String searchParameter) throws Exception {
		return fortnightPlanDao.getFortnightPlanList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<FortnightPlan> getFortnightPlanWorkList() throws Exception {
		return fortnightPlanDao.getFortnightPlanWorkList();
	}

	@Override
	public List<FortnightPlan> getFortnightPlanContractList(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getFortnightPlanContractList(obj);
	}

	@Override
	public List<FortnightPlan> getFortnightPlanCriticalItemList() throws Exception {
		return fortnightPlanDao.getFortnightPlanCriticalItemList();
	}
 
	@Override
	public List<FortnightPlan> getFortnightPlanPeriodList() throws Exception {
		return fortnightPlanDao.getFortnightPlanPeriodList();
	}

	@Override
	public List<FortnightPlan> getWorksListFilter(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getWorksListFilter(obj);
	}
	
	@Override
	public List<FortnightPlan> getPeriodListFilter(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getPeriodListFilter(obj);
	}	

	@Override
	public List<FortnightPlan> getContractListFilter(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getContractListFilter(obj);
	}
	
}
