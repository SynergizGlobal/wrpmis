package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

public interface SafetyEquipmentService {

	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception;

	public SafetyEquipment getSafetyEquipmentDetails(SafetyEquipment obj)throws Exception;

	public boolean addSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean deleteSafetyEquipment(SafetyEquipment obj)throws Exception;

	public List<Work> getworkList()throws Exception;

	public List<SafetyEquipment> getProjectsList()throws Exception;

	public List<SafetyEquipment> contractListFilterInSafetyEquipment()throws Exception;

}
