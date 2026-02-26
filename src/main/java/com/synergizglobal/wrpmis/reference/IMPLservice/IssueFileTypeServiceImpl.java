package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.IssueFileTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.IssueFileTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class IssueFileTypeServiceImpl implements IssueFileTypeService{

	@Autowired
	IssueFileTypeDao dao;

	@Override
	public List<TrainingType> getIssueFileType(TrainingType obj) throws Exception {
		return dao.getIssueFileType(obj);
	}

	@Override
	public boolean addIssueFileType(TrainingType obj) throws Exception {
		return dao.addIssueFileType(obj);
	}

	@Override
	public boolean updateIssueFileType(TrainingType obj) throws Exception {
		return dao.updateIssueFileType(obj);
	}

	@Override
	public boolean deleteIssueFileType(TrainingType obj) throws Exception {
		return dao.deleteIssueFileType(obj);
	}
}
