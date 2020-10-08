package com.synergizglobal.pmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyEquipmentDao;
import com.synergizglobal.pmis.Iservice.SafetyEquipmentService;

@Service
public class SafetyEquipmentServiceImpl implements SafetyEquipmentService {
	
	@Autowired
	SafetyEquipmentDao safetyEquipmentDao;

}
