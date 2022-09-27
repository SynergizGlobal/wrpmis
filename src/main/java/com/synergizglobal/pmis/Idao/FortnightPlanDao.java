package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FortnightPlan;

public interface FortnightPlanDao {
	
	List<FortnightPlan> getFortnightPlanList(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightPlanWorkList() throws Exception;

	List<FortnightPlan> getFortnightPlanContractList(FortnightPlan obj) throws Exception;
	
	List<FortnightPlan> getFortnightPlanCategoryList() throws Exception;
	
	List<FortnightPlan> getFortnightPlanCriticalItemList() throws Exception;
	
	List<FortnightPlan> getFortnightPlanPeriodList() throws Exception;

	List<FortnightPlan> getFortnightPlan(FortnightPlan obj) throws Exception;

	boolean updateFortnightPlan(FortnightPlan obj) throws Exception;
	
	boolean updateFortnightlyPlan(FortnightPlan obj) throws Exception;

	int getTotalRecords(FortnightPlan obj, String searchParameter) throws Exception;

	List<FortnightPlan> getFortnightPlanList(FortnightPlan obj, int startIndex, int offset, String searchParameter) throws Exception;
	
	List<FortnightPlan> getWorksListFilter(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getContractListFilter(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getPeriodListFilter(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightPlanManual(FortnightPlan obj) throws Exception;
	List<FortnightPlan> getQuarterlyPlanManual(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightPlanModuleCategoryList() throws Exception;

	boolean refreshExecutionActivities(String userId) throws Exception;

	boolean saveFortnightDataUploadFile(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightQuarterlyPlanItemList() throws Exception;
	List<FortnightPlan> getFortnightQuarterlyPlanPeriodList() throws Exception;

	boolean insertQuarterlyPlan(FortnightPlan obj) throws Exception;
	
	List<FortnightPlan> getWorksListQuarterlyFilter(FortnightPlan obj) throws Exception;
	List<FortnightPlan> getPeriodListQuarterlyFilter(FortnightPlan obj) throws Exception;
	List<FortnightPlan> getItemListQuarterlyFilter(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightQuarterlyPlanList(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightListQuarterlyFilter(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getfortnightActivities(FortnightPlan obj) throws Exception;

	boolean updateQuarterlyPlanActivities(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getTDCRevisions(FortnightPlan obj) throws Exception;

	List<FortnightPlan> getFortnightPlanProjectList() throws Exception;

	List<FortnightPlan> getFortnightPlanContractList() throws Exception;

	FortnightPlan generateFortnightReport(FortnightPlan obj) throws Exception;
}
 