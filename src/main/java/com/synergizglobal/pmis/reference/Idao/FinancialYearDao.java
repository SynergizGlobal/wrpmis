package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface FinancialYearDao {

	public TrainingType getFinancialYearDetails(TrainingType obj) throws Exception;

	public boolean addFinancialYear(Risk obj) throws Exception;

	public boolean updateFinancialYear(TrainingType obj) throws Exception;

	public boolean deleteFinancialYear(TrainingType obj) throws Exception;

}
