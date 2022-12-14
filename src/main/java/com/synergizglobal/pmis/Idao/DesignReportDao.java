package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.DesignReport;

public interface DesignReportDao {

	List<DesignReport> getWorksListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getHodListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getDepartmentsListInDesignReport(DesignReport obj) throws Exception;

	List<DesignReport> getDesignReportData(DesignReport obj) throws Exception;

}
