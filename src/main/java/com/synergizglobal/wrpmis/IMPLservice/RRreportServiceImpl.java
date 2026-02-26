package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.RRreportDao;
import com.synergizglobal.wrpmis.Iservice.RRreportService;
import com.synergizglobal.wrpmis.model.RandRMain;
@Service
public class RRreportServiceImpl implements RRreportService{
	@Autowired
	 RRreportDao dao;

	@Override
	public List<RandRMain> getWorksFilterListInRRReport(RandRMain obj) throws Exception {
		return dao.getWorksFilterListInRRReport(obj);
	}

	@Override
	public List<RandRMain> getLocationListInRRReport(RandRMain obj) throws Exception {
		return dao.getLocationListInRRReport(obj);
	}

	@Override
	public List<RandRMain> getTypeOfStructuresFilterListInRRReport(RandRMain obj) throws Exception {
		return dao.getTypeOfStructuresFilterListInRRReport(obj);
	}

	@Override
	public RandRMain getRandRMainData(RandRMain obj) throws Exception {
		return dao.getRandRMainData(obj);
	}

	@Override
	public List<RandRMain> getProjectsFilterListInRRReport(RandRMain obj) throws Exception {
		return dao.getProjectsFilterListInRRReport(obj);
	}
}
