package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DesignReportDao;
import com.synergizglobal.pmis.Iservice.DesignReportService;
import com.synergizglobal.pmis.model.DesignReport;

@Service
public class DesignReportServiceImpl implements DesignReportService {
	
	@Autowired
	DesignReportDao dao;
	
	@Override
	public List<DesignReport> getWorksListInDesignReport(DesignReport obj) throws Exception {
		return dao.getWorksListInDesignReport(obj);
	}

	@Override
	public List<DesignReport> getHodListInDesignReport(DesignReport obj) throws Exception {
		return dao.getHodListInDesignReport(obj);
	}

	@Override
	public List<DesignReport> getDepartmentsListInDesignReport(DesignReport obj) throws Exception {
		return dao.getDepartmentsListInDesignReport(obj);
	}

	@Override
	public List<DesignReport> getDesignReportData(DesignReport obj) throws Exception {
		return dao.getDesignReportData(obj);
	}

}
