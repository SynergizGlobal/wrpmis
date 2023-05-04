package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DesignDao;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;

@Service
public class DesignServiceImpl implements DesignService{
	
	@Autowired
	DesignDao designDao;
	
	@Override
	public List<Design> structureList() throws Exception{
		return designDao.structureList();
	}

	@Override
	public List<Design> getDesigns(Design obj) throws Exception {
		return designDao.getDesigns(obj);
	}

	@Override
	public List<Design> getDesignRevisions(Design obj) throws Exception
	{
		return designDao.getDesignRevisions(obj);
	}
	
	@Override
	public List<Design> getDesignsList(Design obj, int startIndex, int offset, String searchParameter) throws Exception {
		return designDao.getDesignsList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public int getTotalRecords(Design obj, String searchParameter) throws Exception {
		return designDao.getTotalRecords(obj,searchParameter);
	}
	
	@Override
	public List<Design> drawingTypeList() throws Exception{
	return designDao.drawingTypeList();
	}
	
	@Override
	public List<Design> getDepartmentList() throws Exception{
		return designDao.getDepartmentList();
	}
	@Override
	public Design getDesignDetails(Design obj) throws Exception{
		return designDao.getDesignDetails(obj);
	}
	@Override
	public List<Design> getContractList() throws Exception{
		return designDao.getContractList();
	}
	
	@Override
	public List<Design> getPreparedByList() throws Exception{
		return designDao.getPreparedByList();
	}
	
	@Override
	public List<Design> getRevisionStatuses() throws Exception{
		return designDao.getRevisionStatuses();
	}
	
	@Override
	public List<Design> getAsBuiltStatuses()throws Exception{
		return designDao.getAsBuiltStatuses();
	}

	@Override
	public String addDesign(Design obj) throws Exception {
		return designDao.addDesign(obj);
	}

	@Override
	public String updateDesign(Design obj) throws Exception {
		return designDao.updateDesign(obj);
	}

	@Override
	public int uploadDesigns(List<Design> designsList) throws Exception {
		return designDao.uploadDesigns(designsList);
	}

	@Override
	public List<Design> getHodListFilter(Design obj) throws Exception {
		return designDao.getHodListFilter(obj);
	}

	@Override
	public List<Design> getDepartmentListFilter(Design obj) throws Exception {
		return designDao.getDepartmentListFilter(obj);
	}

	@Override
	public List<Design> getContractListFilter(Design obj) throws Exception {
		return designDao.getContractListFilter(obj);
	}

	@Override
	public List<Design> getStructureListFilter(Design obj) throws Exception {
		return designDao.getStructureListFilter(obj);
	}

	@Override
	public List<Design> getDrawingTypeListFilter(Design obj) throws Exception {
		return designDao.getDrawingTypeListFilter(obj);
	}

	@Override
	public List<Design> getWorksListFilter(Design obj) throws Exception {
		return designDao.getWorksListFilter(obj);
	}

	@Override
	public List<Design> getProjectsListForDesignForm(Design obj) throws Exception {
		return designDao.getProjectsListForDesignForm(obj);
	}

	@Override
	public List<Design> getWorkListForDesignForm(Design obj) throws Exception {
		return designDao.getWorkListForDesignForm(obj);
	}
	
	@Override
	public List<Design> getComponentsforDesign(Design obj) throws Exception{
		return designDao.getComponentsforDesign(obj);
	}

	@Override
	public List<Design> getContractsListForDesignForm(Design obj) throws Exception {
		return designDao.getContractsListForDesignForm(obj);
	}

	@Override
	public boolean saveDesignDataUploadFile(Design design) throws Exception {
		return designDao.saveDesignDataUploadFile(design);
	}

	@Override
	public List<Design> getDesignUploadsList(Design obj) throws Exception {
		return designDao.getDesignUploadsList(obj);
	}

	@Override
	public List<Design> getHodList(Design obj) throws Exception {
		return designDao.getHodList(obj);
	}

	@Override
	public List<Design> getDyHodList(Design obj) throws Exception {
		return designDao.getDyHodList(obj);
	}

	@Override
	public List<Design> getApprovingRailwayList() throws Exception {
		return designDao.getApprovingRailwayList();
	}

	@Override
	public List<Design> getApprovalAuthority() throws Exception {
		return designDao.getApprovalAuthority();
	}

	@Override
	public List<Design> getStage() throws Exception {
		return designDao.getStage();
	}

	@Override
	public List<Design> getSubmitted() throws Exception {
		return designDao.getSubmitted();
	}

	@Override
	public List<Design> getSubmssionpurpose() throws Exception {
		return designDao.getSubmssionpurpose();
	}

	@Override
	public List<Design> getDesignFileType() throws Exception {
		return designDao.getDesignFileType();
	}

	@Override
	public List<Design> getStructureId() throws Exception {
		return designDao.getStructureId();
	}

	@Override
	public int uploadDesignsNew(List<Design> designsList) throws Exception {
		return designDao.uploadDesignsNew(designsList);
	}
	
	@Override
	public List<Design> componentList() throws Exception{
		return designDao.componentList();
	}	
	
	@Override
	public List<Design> getStructureIdsforDesign(Design obj) throws Exception{
		return designDao.getStructureIdsforDesign(obj);
	}
	
	

}
