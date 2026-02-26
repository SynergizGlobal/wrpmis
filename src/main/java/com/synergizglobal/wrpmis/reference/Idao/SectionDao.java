package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface SectionDao {

	public List<TrainingType> getSectionsList() throws Exception;

	public boolean addSection(TrainingType obj) throws Exception;

	public TrainingType getSectionDetails(TrainingType obj) throws Exception;

	public boolean updateSection(TrainingType obj) throws Exception;

	public boolean deleteSection(TrainingType obj) throws Exception;
}
