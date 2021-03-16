package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;

public interface NotificationTypeService {


	public List<Risk> getNotificationTypeList() throws Exception;

	public boolean addNotificationType(Risk obj) throws Exception;
}
