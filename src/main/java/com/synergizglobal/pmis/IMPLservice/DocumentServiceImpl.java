package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DocumentDao;
import com.synergizglobal.pmis.Iservice.DocumentService;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SafetyEquipment;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DocumentDao dao;

	@Override
	public List<Document> getDocumentsList(Document obj) throws Exception {
		return dao.getDocumentsList(obj);
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

	@Override
	public Document getDocument(Document obj) throws Exception {
		return dao.getDocument(obj);
	}

	@Override
	public List<Document> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public List<Document> getDocumentTypeList() throws Exception {
		return dao.getDocumentTypeList();
	}

	@Override
	public List<Document> getPriorityList() throws Exception {
		return dao.getPriorityListList();
	}

	@Override
	public List<Document> getUserList() throws Exception {
		return dao.getUserList();
	}

	@Override
	public List<Document> getProjectsListForDocumentForm(Document obj) throws Exception {
		return dao.getProjectsListForDocumentForm(obj);
	}


	@Override
	public boolean addDocument(Document obj) throws Exception {
		return dao.addDocument(obj);
	}

	@Override
	public boolean updateDocument(Document obj) throws Exception {
		return dao.updateDocument(obj);
	}

	@Override
	public boolean deleteDocument(Document obj) throws Exception {
		return dao.deleteDocument(obj);
	}

	@Override
	public List<Document> getWorkListForDocumentForm(Document obj) throws Exception {
		return dao.getWorkListForDocumentForm(obj);
	}

	@Override
	public List<Document> getContractsListForDocumentForm(Document obj) throws Exception {
		return dao.getContractsListForDocumentForm(obj);
	}

	@Override
	public List<Document> getDocumentProjectsList(Document obj) throws Exception {
		return dao.getDocumentProjectsList(obj);
	}

	@Override
	public List<Document> getDocumentWorksList(Document obj) throws Exception {
		return dao.getDocumentWorksList(obj);
	}

	@Override
	public List<Document> getRevisionsList(String id) throws Exception {
		return dao.getDocumentWorksList(id);
	}

}
