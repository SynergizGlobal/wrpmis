package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Design;

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

	public boolean addDesign(Design obj) throws Exception;
	
	public boolean updateDesign(Design obj) throws Exception;

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

}
