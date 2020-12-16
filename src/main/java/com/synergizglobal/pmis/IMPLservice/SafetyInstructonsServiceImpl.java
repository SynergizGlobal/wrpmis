package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyInstructonsDao;
import com.synergizglobal.pmis.Iservice.SafetyInstructonsService;
import com.synergizglobal.pmis.model.SafetyEquipment;

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
