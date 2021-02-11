package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;

public interface ContractorService {

	public List<Contractor> getContractorsList() throws Exception;

	public List<Contractor> getContractorSpecialization()throws Exception;

	public Contractor getContractor(Contractor obj)throws Exception;

	public boolean addContractor(Contractor obj)throws Exception;

	public boolean updateContractor(Contractor obj)throws Exception;

	public boolean deleteContractorRow(Contractor obj)throws Exception;

	public List<Contractor> getPanNumberList(Contractor obj) throws Exception;
}
