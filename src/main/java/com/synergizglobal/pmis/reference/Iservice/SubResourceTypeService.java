package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface SubResourceTypeService {

	List<TrainingType> getRiskSubResourceType() throws Exception;

	List<TrainingType> getRiskResourceType() throws Exception;

	TrainingType getSubResourceTypeDetails(TrainingType obj) throws Exception;

	boolean addsubResourceType(Risk obj) throws Exception;

	boolean updatesubResourceType(TrainingType obj) throws Exception;

	boolean deletesubResourceType(TrainingType obj) throws Exception;

}
