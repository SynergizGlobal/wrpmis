package com.synergizglobal.wrpmis.Idao;

import com.synergizglobal.wrpmis.model.LandAcquisitionProcess;
import java.util.List;
import java.util.Map;

public interface LandAcquisitionProcessDAO {

    boolean insertLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception;

    boolean updateLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception;

	List<LandAcquisitionProcess> getLandAcquisitionList(String project_id, String la_file_type,String searchValue, int start, int length);

	long getLandAcquisitionCount(String project_id, String la_file_type, String searchValue);

	boolean isProjectAlreadyExists(String projectId) throws Exception;
}
