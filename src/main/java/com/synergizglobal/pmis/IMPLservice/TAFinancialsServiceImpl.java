package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.TAFinancialsDao;
import com.synergizglobal.pmis.Iservice.TAFinancialsService;
import com.synergizglobal.pmis.model.TAFinancials;

@Service
public class TAFinancialsServiceImpl implements TAFinancialsService{

	@Autowired
	TAFinancialsDao dao;

	@Override
	public List<TAFinancials> taFinancialsList(TAFinancials obj) throws Exception {
		return dao.taFinancialsList(obj);
	}

	@Override
	public List<TAFinancials> getTAFinancialsWorksList(TAFinancials obj) throws Exception {
		return dao.getTAFinancialsWorksList(obj);
	}

	@Override
	public List<TAFinancials> getTAFinancialsContractsList(TAFinancials obj) throws Exception {
		return dao.getTAFinancialsContractsList(obj);
	}

	@Override
	public List<TAFinancials> getWorksList() throws Exception {
		return dao.getWorksList();
	}

	@Override
	public TAFinancials getTAFinancials(TAFinancials obj) throws Exception {
		return dao.getTAFinancials(obj);
	}

	@Override
	public boolean addTAFinancials(TAFinancials obj) throws Exception {
		return dao.addTAFinancials(obj);
	}

	@Override
	public boolean updateTAFinancials(TAFinancials obj) throws Exception {
		return dao.updateTAFinancials(obj);
	}

	@Override
	public List<TAFinancials> getContractsList() throws Exception {
		return dao.getContractsList();
	}

	@Override
	public List<TAFinancials> getWorkListForFinancialsForm(TAFinancials obj) throws Exception {
		return dao.getWorkListForFinancialsForm(obj);
	}

	@Override
	public List<TAFinancials> getContractsListForFinancialsForm(TAFinancials obj) throws Exception {
		return dao.getContractsListForFinancialsForm(obj);
	}

	@Override
	public int getTotalRecords(TAFinancials obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<TAFinancials> getTAFinancialsList(TAFinancials obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getTAFinancialsList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<TAFinancials> getUnitsList() throws Exception {
		return dao.getUnitsList();
	}
}
