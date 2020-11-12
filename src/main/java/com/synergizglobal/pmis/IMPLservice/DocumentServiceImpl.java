package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DocumentDao;
import com.synergizglobal.pmis.Iservice.DocumentService;
import com.synergizglobal.pmis.model.Document;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DocumentDao dao;

	@Override
	public List<Document> documentList(Document obj) throws Exception {
		return dao.documentList(obj);
	}

	@Override
	public List<Document> getDocumentContractsList(Document obj) throws Exception {
		return dao.getDocumentContractsList(obj);
	}

	@Override
	public List<Document> getDocumentProjectPriorityList(Document obj) throws Exception {
		return dao.getDocumentProjectPriorityList(obj);
	}

	@Override
	public List<Document> getDocumentTypesList(Document obj) throws Exception {
		return dao.getDocumentTypesList(obj);
	}

	@Override
	public List<Document> getDocumentResponsibleForApprovalList(Document obj) throws Exception {
		return dao.getDocumentResponsibleForApprovalList(obj);
	}

}
