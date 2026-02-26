package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.TAFinancials;

public interface TAFinancialsService {

	public List<TAFinancials> taFinancialsList(TAFinancials obj) throws Exception;

	public List<TAFinancials> getTAFinancialsWorksList(TAFinancials obj) throws Exception;

	public List<TAFinancials> getTAFinancialsContractsList(TAFinancials obj) throws Exception;

	public List<TAFinancials> getWorksList() throws Exception;

	public TAFinancials getTAFinancials(TAFinancials obj) throws Exception;

	public boolean addTAFinancials(TAFinancials obj) throws Exception;

	public boolean updateTAFinancials(TAFinancials obj) throws Exception;

	public List<TAFinancials> getContractsList() throws Exception;

	public List<TAFinancials> getWorkListForFinancialsForm(TAFinancials obj) throws Exception;

	public List<TAFinancials> getContractsListForFinancialsForm(TAFinancials obj) throws Exception;

	public int getTotalRecords(TAFinancials obj, String searchParameter) throws Exception;

	public List<TAFinancials> getTAFinancialsList(TAFinancials obj, int startIndex, int offset,
			String searchParameter) throws Exception;

	public List<TAFinancials> getUnitsList() throws Exception;

}
