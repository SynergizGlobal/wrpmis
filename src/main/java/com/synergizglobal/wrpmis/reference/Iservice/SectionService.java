package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SectionService {
	
	public List<TrainingType> getSectionsList() throws Exception;

	public boolean addSection(TrainingType obj) throws Exception;

	public TrainingType getSectionDetails(TrainingType obj) throws Exception;

	public boolean updateSection(TrainingType obj) throws Exception;

	public boolean deleteSection(TrainingType obj) throws Exception;

}
