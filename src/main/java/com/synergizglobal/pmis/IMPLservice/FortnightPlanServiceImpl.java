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
	public boolean refreshExecutionActivities(String UserId) throws Exception {
		return fortnightPlanDao.refreshExecutionActivities(UserId);
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
	public List<FortnightPlan> getQuarterlyPlanManual(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getQuarterlyPlanManual(obj);
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
	public boolean updateQuarterlyPlanActivities(FortnightPlan obj) throws Exception{
		return fortnightPlanDao.updateQuarterlyPlanActivities(obj);
	}
	@Override
	public boolean insertQuarterlyPlan(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.insertQuarterlyPlan(obj);
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
	public List<FortnightPlan> getFortnightPlanProjectList() throws Exception {
		return fortnightPlanDao.getFortnightPlanProjectList();
	}
	@Override
	public FortnightPlan generateFortnightReport(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.generateFortnightReport(obj);
	}
	@Override
	public List<FortnightPlan> getFortnightUploadList() throws Exception{
		return fortnightPlanDao.getFortnightUploadList();
	}
	
	@Override
	public List<FortnightPlan> getFortnightPlanWorkList() throws Exception {
		return fortnightPlanDao.getFortnightPlanWorkList();
	}
	
	@Override
	public List<FortnightPlan> getFortnightPlanContractList() throws Exception {
		return fortnightPlanDao.getFortnightPlanContractList();
	}	
	
	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanItemList() throws Exception {
		return fortnightPlanDao.getFortnightQuarterlyPlanItemList();
	}
	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanPeriodList() throws Exception {
		return fortnightPlanDao.getFortnightQuarterlyPlanPeriodList();
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
	public List<FortnightPlan> getTDCRevisions(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getTDCRevisions(obj);
	}	
	@Override
	public List<FortnightPlan> getPeriodListFilter(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getPeriodListFilter(obj);
	}	

	@Override
	public List<FortnightPlan> getContractListFilter(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getContractListFilter(obj);
	}
	
	@Override
	public boolean saveFortnightDataUploadFile(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.saveFortnightDataUploadFile(obj);
	}
	
	public List<FortnightPlan> getWorksListQuarterlyFilter(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.getWorksListQuarterlyFilter(obj);
	}
	
	public List<FortnightPlan> getPeriodListQuarterlyFilter(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.getPeriodListQuarterlyFilter(obj);
	}
	public List<FortnightPlan> getItemListQuarterlyFilter(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.getItemListQuarterlyFilter(obj);
	}

	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanList(FortnightPlan obj) throws Exception {
		return fortnightPlanDao.getFortnightQuarterlyPlanList(obj);
	}
	
	public List<FortnightPlan> getFortnightListQuarterlyFilter(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.getFortnightListQuarterlyFilter(obj);
	}
	
	public List<FortnightPlan> getfortnightActivities(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.getfortnightActivities(obj);
	}
	
	public List<FortnightPlan> contractList(FortnightPlan obj) throws Exception
	{
		return fortnightPlanDao.contractList(obj);
	}
	
	public int uploadFortnightPlans(List<FortnightPlan>FortnightPlansList) throws Exception
	{
		return fortnightPlanDao.uploadFortnightPlans(FortnightPlansList);
	}
	
	public int deleteFortnightsByContractShortName(String ContractShortName) throws Exception
	{
		return fortnightPlanDao.deleteFortnightsByContractShortName(ContractShortName);
	}
	
}
