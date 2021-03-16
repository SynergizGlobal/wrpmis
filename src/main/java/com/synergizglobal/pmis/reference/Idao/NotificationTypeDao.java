package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;

public interface NotificationTypeDao {

	public List<Risk> getNotificationTypeList() throws Exception;

	public boolean addNotificationType(Risk obj) throws Exception;
}
