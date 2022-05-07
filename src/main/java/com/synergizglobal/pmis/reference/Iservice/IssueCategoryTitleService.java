package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssueCategoryTitleService {

	List<TrainingType> gtIssueCategoryDetails(TrainingType obj) throws Exception;

	List<TrainingType> getIssueCategoryTitle(TrainingType obj) throws Exception;

	boolean addIssueCategoryTitle(TrainingType obj) throws Exception;

	boolean updateIssueCategoryTitle(TrainingType obj) throws Exception;

	boolean deleteIssueCategoryTitle(TrainingType obj) throws Exception;

	List<TrainingType> getTitles(TrainingType obj) throws Exception;

}
