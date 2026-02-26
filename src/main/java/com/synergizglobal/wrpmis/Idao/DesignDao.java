package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Design;

public interface DesignDao {

	public List<Design> structureList() throws Exception;
	
	public List<Design> getDesigns(Design obj) throws Exception;

	public List<Design> getDesignsList(Design obj, int startIndex, int offset, String searchParameter) throws Exception;
	
	public int getTotalRecords(Design obj, String searchParameter) throws Exception;

	public List<Design> drawingTypeList() throws Exception;

	public List<Design> getDepartmentList() throws Exception;

	public Design getDesignDetails(Design obj) throws Exception;

	public List<Design> getContractList() throws Exception;

	public List<Design> getPreparedByList() throws Exception;

	public List<Design> getRevisionStatuses() throws Exception;
	
	public List<Design> getAsBuiltStatuses()throws Exception;

	public String addDesign(Design obj) throws Exception;
	
	public String updateDesign(Design obj) throws Exception;

	public int uploadDesigns(List<Design> designsList) throws Exception;
	
	public List<Design> getHodListFilter(Design obj) throws Exception;

	public List<Design> getDepartmentListFilter(Design obj) throws Exception;

	public List<Design> getContractListFilter(Design obj) throws Exception;

	public List<Design> getStructureListFilter(Design obj) throws Exception;

	public List<Design> getDrawingTypeListFilter(Design obj) throws Exception;

	public List<Design> getWorksListFilter(Design obj) throws Exception;

	List<Design> getProjectsListForDesignForm(Design obj) throws Exception;

	List<Design> getWorkListForDesignForm(Design obj) throws Exception;

	List<Design> getContractsListForDesignForm(Design obj) throws Exception;

	public boolean saveDesignDataUploadFile(Design design) throws Exception;

	public List<Design> getDesignUploadsList(Design obj) throws Exception;
	
	public List<Design> getHodList(Design obj) throws Exception;
	
	public List<Design> getDyHodList(Design obj) throws Exception;

	public List<Design> getApprovingRailwayList() throws Exception;

	public List<Design> getApprovalAuthority() throws Exception;

	public List<Design> getStage() throws Exception;

	public List<Design> getSubmitted() throws Exception;

	public List<Design> getSubmssionpurpose() throws Exception;

	public List<Design> getDesignFileType() throws Exception;

	public List<Design> getStructureId() throws Exception;

	public int uploadDesignsNew(List<Design> designsList) throws Exception;

	public List<Design> componentList() throws Exception;

	public List<Design> getComponentsforDesign(Design obj) throws Exception;
	
	public List<Design> getStructureIdsforDesign(Design obj) throws Exception;

	public List<Design> getDesignRevisions(Design obj)  throws Exception;

	public List<Design> getP6ActivitiesData(Design obj) throws Exception;

	public boolean updateDesignStatusBulk(Design obj) throws Exception;

	public List<Design> getDesignUpdateStructures(Design obj)  throws Exception;

	public List<Design> getStructureTypesforDesign(Design obj) throws Exception;

	public List<Design> getStructureTypeListFilter(Design obj)  throws Exception;

	public List<Design> getStructureIdsListFilter(Design obj)  throws Exception;

	public int getTotalDrawingRepositoryRecords(Design obj, String searchParameter) throws Exception;

	public List<Design> getDrawingRepositoryDesignsList(Design obj, int startIndex, int offset, String searchParameter) throws Exception;

}
