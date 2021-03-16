package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.ManualFoldersDao;
import com.synergizglobal.pmis.reference.Iservice.ManualFoldersService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class ManualFoldersServiceImpl implements ManualFoldersService{

	@Autowired
	ManualFoldersDao dao;

	@Override
	public List<Safety> getManualFoldersList() throws Exception {
		return dao.getManualFoldersList();
	}

	@Override
	public boolean addManualFolders(Safety obj) throws Exception {
		return dao.addManualFolders(obj);
	}

	@Override
	public TrainingType getManualFoldersDetails(TrainingType obj) throws Exception {
		return dao.getManualFoldersDetails(obj);
	}

	@Override
	public boolean updateManualFolders(TrainingType obj) throws Exception {
		return dao.updateManualFolders(obj);
	}

	@Override
	public boolean deleteManualFolders(TrainingType obj) throws Exception {
		return dao.deleteManualFolders(obj);
	}
}
