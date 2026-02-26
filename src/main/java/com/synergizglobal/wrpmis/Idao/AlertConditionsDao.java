package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.AlertConditions;

public interface AlertConditionsDao {
	
	List<AlertConditions> getAlertTypes() throws Exception;

	List<AlertConditions> getAlertConditions(AlertConditions obj) throws Exception;
	
	List<AlertConditions> getAlertCondition(AlertConditions obj) throws Exception;

	boolean updateAlertCondition(AlertConditions obj) throws Exception;
}
