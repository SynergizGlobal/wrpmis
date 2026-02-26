package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DeliverableTypeDao {

	public List<TrainingType> getDeliverableTypesList() throws Exception;

	public boolean addDeliverableType(TrainingType obj) throws Exception;

	public TrainingType getDeliverableTypeDetails(TrainingType obj) throws Exception;

	public boolean updateDeliverableType(TrainingType obj) throws Exception;

	public boolean deleteDeliverableType(TrainingType obj) throws Exception;
}
