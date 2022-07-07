package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.FortnightPlan;

public interface FortnightPlanService {
	
	List<FortnightPlan> getFortnightPlanList(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightPlanWorkList() throws Exception;

	List<FortnightPlan> getFortnightPlanContractList(FortnightPlan obj) throws Exception;
	
	List<FortnightPlan> getFortnightPlanCategoryList() throws Exception;
	
	List<FortnightPlan> getFortnightPlanCriticalItemList() throws Exception;
	
	List<FortnightPlan> getFortnightPlanPeriodList() throws Exception;

	FortnightPlan getFortnightPlan(FortnightPlan obj) throws Exception;

	boolean updateFortnightPlan(FortnightPlan obj) throws Exception;

	int getTotalRecords(FortnightPlan obj, String searchParameter) throws Exception;

	List<FortnightPlan> getFortnightPlanList(FortnightPlan obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<FortnightPlan> getWorksListFilter(FortnightPlan obj) throws Exception;
	
	public List<FortnightPlan> getContractListFilter(FortnightPlan obj) throws Exception;

}
