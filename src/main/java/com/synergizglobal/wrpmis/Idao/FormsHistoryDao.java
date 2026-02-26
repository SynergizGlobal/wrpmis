package com.synergizglobal.wrpmis.Idao;

import com.synergizglobal.wrpmis.model.FormHistory;

public interface FormsHistoryDao {
	public boolean saveFormHistory(FormHistory obj) throws Exception;

	boolean saveValidityFormHistory(FormHistory obj) throws Exception;
}
