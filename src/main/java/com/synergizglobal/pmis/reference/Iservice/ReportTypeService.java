package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ReportTypeService {

	public List<Risk> getReportTypeList() throws Exception;

	public boolean addReportType(Risk obj) throws Exception;

	public TrainingType getReportTypeDetails(TrainingType obj) throws Exception;

	public boolean updateReportType(TrainingType obj) throws Exception;

	public boolean deleteReportType(TrainingType obj) throws Exception;
}
