package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.LandAcquisitionProcess;

public interface LandAcquisitionProcessService {

    void insertLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception;

    void updateLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception;

	List<LandAcquisitionProcess> getLandAcquisitionList(String project_id, String la_file_type,String searchValue, int start, int length);

	long getLandAcquisitionCount(String project_id, String la_file_type,String searchValue);

	boolean isProjectAlreadyExists(String projectId) throws Exception;
}
