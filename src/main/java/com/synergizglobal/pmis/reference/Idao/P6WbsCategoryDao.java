package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface P6WbsCategoryDao {

	public List<Safety> getP6WbsCategoryList() throws Exception;

	public boolean addP6WbsCategory(Safety obj) throws Exception;

	public TrainingType getP6WbsCategoryDetails(TrainingType obj) throws Exception;

	public boolean updateP6WbsCategory(TrainingType obj) throws Exception;

	public boolean deleteP6WbsCategory(TrainingType obj) throws Exception;
}
