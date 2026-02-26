package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.SafetyEquipment;

public interface SafetyInstructonsService {

	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception;

	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception;
	

}
