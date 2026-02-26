package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.LandAcquisitionProcessDAO;
import com.synergizglobal.wrpmis.Iservice.LandAcquisitionProcessService;
import com.synergizglobal.wrpmis.model.LandAcquisitionProcess;

@Service
public class LandAcquisitionProcessServiceImpl implements LandAcquisitionProcessService {

    @Autowired
    private LandAcquisitionProcessDAO dao;

    @Override
    public void insertLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception {
        dao.insertLandAcquisitionProcess(process);
    }

    @Override
    public void updateLandAcquisitionProcess(LandAcquisitionProcess process) throws Exception {
        dao.updateLandAcquisitionProcess(process);
    }

	@Override
	public List<LandAcquisitionProcess> getLandAcquisitionList(String project_id, String la_file_type,String searchValue, int start, int length) {
		 return dao.getLandAcquisitionList(project_id,la_file_type,searchValue,  start,  length);
	}

	@Override
	public long getLandAcquisitionCount(String project_id, String la_file_type,String searchValue) {
		return dao.getLandAcquisitionCount(project_id,la_file_type,searchValue);
	}

	@Override
	public boolean isProjectAlreadyExists(String projectId) throws Exception {
		return dao.isProjectAlreadyExists(projectId);
	}

}
