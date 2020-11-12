package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Document;

public interface DocumentDao {

	public List<Document> documentList(Document obj) throws Exception;

	public List<Document> getDocumentContractsList(Document obj) throws Exception;

	public List<Document> getDocumentProjectPriorityList(Document obj) throws Exception;

	public List<Document> getDocumentTypesList(Document obj) throws Exception;

	public List<Document> getDocumentResponsibleForApprovalList(Document obj) throws Exception;

}
