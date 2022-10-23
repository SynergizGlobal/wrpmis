package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.RandRMain;

public interface RandRMainService {

	List<RandRMain> getWorksFilterListInRR(RandRMain obj) throws Exception;

	List<RandRMain> getStatusFilterListInRR(RandRMain obj) throws Exception;

	List<RandRMain> getLocationsFilterListInRR(RandRMain obj) throws Exception;

	List<RandRMain> getTypeofUseFilterListInRR(RandRMain obj) throws Exception;

	List<RandRMain> getStructuresFilterListInRR(RandRMain obj) throws Exception;

	List<RandRMain> getPhasesFilterListInRR(RandRMain obj) throws Exception;

	int getTotalRecords(RandRMain obj, String searchParameter) throws Exception;

	List<RandRMain> getRRList(RandRMain obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<RandRMain> getProjectsListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getWorkListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getDocTypeListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getPhaseListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getStructureListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getLocationListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getSubLocationListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getTypeofUseListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getVerificationByListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getUnitsListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getStatusListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getOccupancyStatusListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getGenderListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getTenureStatusListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getCasteListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getMotherTongueListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getTypeofFamilyListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getMaritualStatusListForRRForm(RandRMain obj) throws Exception;

	RandRMain getRandRMainForm(RandRMain rr) throws Exception;

	boolean addRR(RandRMain obj) throws Exception;

	boolean updateRR(RandRMain obj) throws Exception;

	List<RandRMain> getRRIdListForRRForm(RandRMain obj) throws Exception;

	List<RandRMain> getRandRMainList(RandRMain dObj) throws Exception;

	List<RandRMain> gecommercialList(String rr_id) throws Exception;

	List<RandRMain> getComDetailsListList(String rr_id) throws Exception;

	List<RandRMain> getResidentialList(String rr_id) throws Exception;

	List<RandRMain> getRDetailsList(String rr_id) throws Exception;

	String[] uploadRRData(List<RandRMain> rrsList, RandRMain rr) throws Exception;

	String checkLAIdMethod(RandRMain rr, String check) throws Exception;

	List<RandRMain> getRRUploadsList(RandRMain obj) throws Exception;

	boolean saveRRDataUploadFile(RandRMain obj) throws Exception;
	
	List<RandRMain> getRRCoordinates(RandRMain obj) throws Exception;

	List<RandRMain> getBSESAgencyNamesList(RandRMain obj) throws Exception;
	
	String getRRId(String action) throws Exception;


}
