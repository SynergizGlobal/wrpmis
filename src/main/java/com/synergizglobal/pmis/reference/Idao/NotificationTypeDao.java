package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface NotificationTypeDao {

	public List<Risk> getNotificationTypeList() throws Exception;

	public boolean addNotificationType(Risk obj) throws Exception;

	public TrainingType getNotificationTypeDetails(TrainingType obj) throws Exception;

	public boolean updateNotificationType(TrainingType obj) throws Exception;

	public boolean deleteNotificationType(TrainingType obj) throws Exception;
}
