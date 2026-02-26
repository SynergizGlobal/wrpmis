package com.synergizglobal.wrpmis.IMPLservice;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ExpenditureDao;
import com.synergizglobal.wrpmis.Iservice.ExpenditureService;
import com.synergizglobal.wrpmis.model.Expenditure;

@Service
public class ExpenditureServiceImpl implements ExpenditureService{

	@Autowired
	ExpenditureDao dao;
	

	@Override
	public List<Expenditure> getExpendituresList(Expenditure obj, int startIndex, int offset, String searchParameter) throws Exception {
		return dao.getExpendituresList( obj, startIndex, offset, searchParameter);
	}

	@Override
	public List<Expenditure> getVoucherList() throws Exception {
		return dao.getVoucherList();
	}

	@Override
	public Expenditure getExpenditure(Expenditure obj) throws Exception {
		return dao.getExpenditure(obj);
	}

	@Override
	public boolean addExpenditure(Expenditure obj) throws Exception {
		return dao.addExpenditure(obj);
	}

	@Override
	public boolean updateExpenditure(Expenditure obj) throws Exception {
		return dao.updateExpenditure(obj);
	}

	@Override
	public boolean deleteExpenditure(Expenditure obj) throws Exception {
		return dao.deleteExpenditure(obj);
	}

	@Override
	public List<Expenditure> getWorksFilterList(Expenditure obj) throws Exception {
		return dao.getWorksFilterList(obj);
	}

	@Override
	public List<Expenditure> getContractsFilterList(Expenditure obj) throws Exception {
		return dao.getContractsFilterList(obj);
	}

	@Override
	public List<Expenditure> getledgerAccountsList(Expenditure obj) throws Exception {
		return dao.getledgerAccountsList(obj);
	}

	@Override
	public List<Expenditure> getContractorNamesFilterList(Expenditure obj) throws Exception {
		return dao.getContractorNamesFilterList(obj);
	}

	@Override
	public List<Expenditure> getVoucherTypesFilterList(Expenditure obj) throws Exception {
		return dao.getVoucherTypesFilterList(obj);
	}

	@Override
	public List<Expenditure> getProjectsListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getProjectsListForExpenditureForm(obj);
	}

	@Override
	public List<Expenditure> getWorkListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getWorkListForExpenditureForm(obj);
	}

	@Override
	public List<Expenditure> getContractsListForExpenditureForm(Expenditure obj) throws Exception {
		return dao.getContractsListForExpenditureForm(obj);
	}

	@Override
	public int uploadExpenditures(List<Expenditure> expendituresList) throws Exception {
		return dao.uploadExpenditures(expendituresList);
	}

	@Override
	public int getTotalRecords(Expenditure obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Expenditure> getExpendituresListForExport(Expenditure obj) throws Exception {
		return dao.getExpendituresListForExport(obj);
	}

	@Override
	public List<Expenditure> getUnitsList() throws Exception {
		return dao.getUnitsList();
	}
	
	@Override
	public ResultSet generateExpenditureReportByProject(Expenditure obj)  throws Exception{
		return dao.generateExpenditureReportByProject(obj);
	}
	
	@Override
	public List<Expenditure> generateExpenditureReportByWork(Expenditure obj)  throws Exception
	{
		return dao.generateExpenditureReportByWork(obj);
	}
	public List<Expenditure> generateExpenditureReportByContract(Expenditure obj)  throws Exception
	{
		return dao.generateExpenditureReportByContract(obj);
	}
	
	

}
