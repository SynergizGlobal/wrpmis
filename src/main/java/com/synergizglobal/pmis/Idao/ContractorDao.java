package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contractor;

public interface ContractorDao {

	public List<Contractor> getContractorsList() throws Exception;

	public List<Contractor> getContractorSpecialization()throws Exception;

	public Contractor getContractor(Contractor obj)throws Exception;

	public boolean addContractor(Contractor obj)throws Exception;

	public boolean updateContractor(Contractor obj)throws Exception;

	public boolean deleteContractorRow(Contractor obj)throws Exception;

	public List<Contractor> getPanNumberList(Contractor obj) throws Exception;

	public int getTotalRecords(Contractor obj, String searchParameter) throws Exception;

	public List<Contractor> getContractorsList(Contractor obj, int startIndex, int offset, String searchParameter) throws Exception;

}
