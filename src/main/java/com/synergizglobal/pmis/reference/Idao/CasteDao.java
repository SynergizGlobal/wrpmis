package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface CasteDao {

	TrainingType getCasteDetails(TrainingType obj) throws Exception;

	boolean addCaste(TrainingType obj) throws Exception;

	boolean updateCaste(TrainingType obj) throws Exception;

	boolean deleteCaste(TrainingType obj) throws Exception;

}
