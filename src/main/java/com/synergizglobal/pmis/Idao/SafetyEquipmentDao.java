package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

public interface SafetyEquipmentDao {

	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception;

	public SafetyEquipment getSafetyEquipmentDetails(SafetyEquipment obj)throws Exception;

	public boolean addSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception;

	public boolean deleteSafetyEquipment(SafetyEquipment obj)throws Exception;

	public List<Work> getworkList()throws Exception;

	public List<SafetyEquipment> getProjectsListForSafetyEquipmentForm(SafetyEquipment obj)throws Exception;

	public List<SafetyEquipment> contractListFilterInSafetyEquipment()throws Exception;

	public List<SafetyEquipment> getSafetyEquipmentExportList(SafetyEquipment obj) throws Exception;

	public List<SafetyEquipment> getWorkListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception;

	public List<SafetyEquipment> getContractsListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception;


}
