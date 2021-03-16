package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RRDocumentTypeDao {

	public List<Safety> getRRDocumentTypeList() throws Exception;

	public boolean addRRDocumentType(Safety obj) throws Exception;

	public TrainingType getRRDocumentTypeDetails(TrainingType obj) throws Exception;

	public boolean updateRRDocumentType(TrainingType obj) throws Exception;

	public boolean deleteRRDocumentType(TrainingType obj) throws Exception;
}
