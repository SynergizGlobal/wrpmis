package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.SafetyEquipment;

public interface SafetyInstructonsDao {

	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception;

	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception;

}
