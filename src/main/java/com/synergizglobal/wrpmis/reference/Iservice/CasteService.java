package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface CasteService {

	TrainingType getCasteDetails(TrainingType obj) throws Exception;

	boolean addCaste(TrainingType obj) throws Exception;

	boolean updateCaste(TrainingType obj) throws Exception;

	boolean deleteCaste(TrainingType obj) throws Exception;

}
