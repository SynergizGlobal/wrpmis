package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ManualFoldersService {

	public List<Safety> getManualFoldersList() throws Exception;

	public boolean addManualFolders(Safety obj) throws Exception;

	public TrainingType getManualFoldersDetails(TrainingType obj) throws Exception;

	public boolean updateManualFolders(TrainingType obj) throws Exception;

	public boolean deleteManualFolders(TrainingType obj) throws Exception;
}
