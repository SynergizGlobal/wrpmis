package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DocumentRevisionStatusDao;
import com.synergizglobal.pmis.reference.Iservice.DocumentRevisionStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class DocumentRevisionStatusServiceImpl implements DocumentRevisionStatusService{

	@Autowired
	DocumentRevisionStatusDao dao;

	@Override
	public List<Safety> getDocumentRevisionStatusList() throws Exception {
		return dao.getDocumentRevisionStatusList();
	}

	@Override
	public boolean addDocumentRevisionStatus(Safety obj) throws Exception {
		return dao.addDocumentRevisionStatus(obj);
	}

	@Override
	public TrainingType getDocumentRevisionStatusDetails(TrainingType obj) throws Exception {
		return dao.getDocumentRevisionStatusDetails(obj);
	}

	@Override
	public boolean updateDocumentRevisionStatus(TrainingType obj) throws Exception {
		return dao.updateDocumentRevisionStatus(obj);
	}

	@Override
	public boolean deleteDocumentRevisionStatus(TrainingType obj) throws Exception {
		return dao.deleteDocumentRevisionStatus(obj);
	}
}
