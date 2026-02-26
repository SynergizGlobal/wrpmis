package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface FinancialYearDao {

	public TrainingType getFinancialYearDetails(TrainingType obj) throws Exception;

	public boolean addFinancialYear(Risk obj) throws Exception;

	public boolean updateFinancialYear(TrainingType obj) throws Exception;

	public boolean deleteFinancialYear(TrainingType obj) throws Exception;

}
