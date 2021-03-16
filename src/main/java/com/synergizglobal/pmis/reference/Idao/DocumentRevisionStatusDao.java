package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface DocumentRevisionStatusDao {

	public List<Safety> getDocumentRevisionStatusList() throws Exception;

	public boolean addDocumentRevisionStatus(Safety obj) throws Exception;

	public TrainingType getDocumentRevisionStatusDetails(TrainingType obj) throws Exception;

	public boolean updateDocumentRevisionStatus(TrainingType obj) throws Exception;

	public boolean deleteDocumentRevisionStatus(TrainingType obj) throws Exception;
}
