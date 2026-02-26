package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.SafetyInstructonsDao;
import com.synergizglobal.wrpmis.Iservice.SafetyInstructonsService;
import com.synergizglobal.wrpmis.model.SafetyEquipment;

@Service
public class SafetyInstructonsServiceImpl implements SafetyInstructonsService{
	@Autowired
	SafetyInstructonsDao dao;

	@Override
	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception {
		return dao.getSafetyInstructionsList(obj);
	}

	@Override
	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception {
		return dao.updateSafetyInstructions(obj);
	}

}
