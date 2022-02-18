package com.synergizglobal.pmis.Idao;

import com.synergizglobal.pmis.model.FormHistory;

public interface FormsHistoryDao {
	public boolean saveFormHistory(FormHistory obj) throws Exception;
	
	public boolean saveFormHistoryForWork(FormHistory obj) throws Exception;

	boolean saveValidityFormHistory(FormHistory obj) throws Exception;
}
