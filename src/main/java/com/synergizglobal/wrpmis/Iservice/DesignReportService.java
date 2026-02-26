package com.synergizglobal.wrpmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.wrpmis.model.DesignReport;

public interface DesignReportService {

	List<DesignReport> getWorksListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getHodListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getDepartmentsListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getDesignReportData(DesignReport obj) throws Exception;

}
