package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.NotificationTypeDao;
import com.synergizglobal.pmis.reference.Iservice.NotificationTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class NotificationTypeServiceImpl implements NotificationTypeService{
	@Autowired
	NotificationTypeDao dao;

	@Override
	public List<Risk> getNotificationTypeList() throws Exception {
		return dao.getNotificationTypeList();
	}

	@Override
	public boolean addNotificationType(Risk obj) throws Exception {
		return dao.addNotificationType(obj);
	}

	@Override
	public TrainingType getNotificationTypeDetails(TrainingType obj) throws Exception {
		return dao.getNotificationTypeDetails(obj);
	}

	@Override
	public boolean updateNotificationType(TrainingType obj) throws Exception {
		return dao.updateNotificationType(obj);
	}

	@Override
	public boolean deleteNotificationType(TrainingType obj) throws Exception {
		return dao.deleteNotificationType(obj);
	}
}
