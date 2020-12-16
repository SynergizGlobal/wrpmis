package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.SafetyEquipment;

public interface SafetyInstructonsService {

	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception;

	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception;
	

}
