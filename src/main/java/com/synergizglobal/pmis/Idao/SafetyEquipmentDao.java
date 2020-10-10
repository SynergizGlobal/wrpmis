package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

public interface SafetyEquipmentDao {

	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception;

	public List<SafetyEquipment> getSafetyDetails(SafetyEquipment obj)throws Exception;

	public boolean addSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean deleteSafetyEquipment(SafetyEquipment obj)throws Exception;

	public List<Work> getworkList()throws Exception;

	public List<Project> getProjectsList()throws Exception;

}
