package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.SafetyEquipment;

public interface SafetyInstructonsDao {

	public List<SafetyEquipment> getSafetyInstructionsList(SafetyEquipment obj) throws Exception;

	public boolean updateSafetyInstructions(SafetyEquipment obj) throws Exception;

}
