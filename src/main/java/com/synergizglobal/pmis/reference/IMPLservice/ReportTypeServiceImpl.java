package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.ReportTypeDao;
import com.synergizglobal.pmis.reference.Iservice.ReportTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class ReportTypeServiceImpl implements ReportTypeService{

	@Autowired
	ReportTypeDao dao;

	@Override
	public List<Risk> getReportTypeList() throws Exception {
		return dao.getReportTypeList();
	}

	@Override
	public boolean addReportType(Risk obj) throws Exception {
		return dao.addReportType(obj);
	}

	@Override
	public TrainingType getReportTypeDetails(TrainingType obj) throws Exception {
		return dao.getReportTypeDetails(obj);
	}

	@Override
	public boolean updateReportType(TrainingType obj) throws Exception {
		return dao.updateReportType(obj);
	}

	@Override
	public boolean deleteReportType(TrainingType obj) throws Exception {
		return dao.deleteReportType(obj);
	}
}
