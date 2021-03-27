package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractorDao;
import com.synergizglobal.pmis.Iservice.ContractorService;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;

@Service
public class ContractorServiceImpl implements ContractorService {
	
	@Autowired
	ContractorDao contractorDao;

	@Override
	public List<Contractor> getContractorsList() throws Exception {
		return contractorDao.getContractorsList();
	}
	
	@Override
	public List<Contractor> getContractorSpecialization()throws Exception{
		return contractorDao.getContractorSpecialization();
	}
	
	@Override
	public Contractor getContractor(Contractor obj)throws Exception{
		return contractorDao.getContractor(obj);
	}
	@Override
	public boolean addContractor(Contractor obj)throws Exception{
		return contractorDao.addContractor(obj);
	}
	
	@Override
	public boolean updateContractor(Contractor obj)throws Exception{
		return contractorDao.updateContractor(obj);
	}
	@Override
	public boolean deleteContractorRow(Contractor obj)throws Exception{
		return contractorDao.deleteContractorRow(obj);

	}

	@Override
	public List<Contractor> getPanNumberList(Contractor obj) throws Exception {
		return contractorDao.getPanNumberList(obj);

	}

	@Override
	public int getTotalRecords(Contractor obj, String searchParameter) throws Exception {
		return contractorDao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Contractor> getContractorsList(Contractor obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return contractorDao.getContractorsList(obj,startIndex,offset,searchParameter);
	}


}
