package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Document;

public interface DocumentDao {

	public List<Document> getDocumentsList(Document obj) throws Exception;

	public List<Document> getDocumentContractsList(Document obj) throws Exception;

	public List<Document> getDocumentProjectPriorityList(Document obj) throws Exception;

	public List<Document> getDocumentTypesList(Document obj) throws Exception;

	public List<Document> getDocumentResponsibleForApprovalList(Document obj) throws Exception;

	public Document getDocument(Document obj)throws Exception;

	public List<Document> getStatusList()throws Exception;

	public List<Document> getDocumentTypeList() throws Exception;

	public List<Document> getPriorityListList() throws Exception;

	public List<Document> getUserList() throws Exception;

	public List<Document> getProjectsListForDocumentForm(Document obj) throws Exception;

	public boolean addDocument(Document obj) throws Exception;

	public boolean updateDocument(Document obj) throws Exception;

	public boolean deleteDocument(Document obj) throws Exception;

	public List<Document> getWorkListForDocumentForm(Document obj) throws Exception;

	public List<Document> getContractsListForDocumentForm(Document obj) throws Exception;

	public List<Document> getDocumentProjectsList(Document obj) throws Exception;

	public List<Document> getDocumentWorksList(Document obj) throws Exception;

	public List<Document> getDocumentWorksList(String id) throws Exception;

	public int getTotalRecords(Document obj, String searchParameter) throws Exception;

	public List<Document> getDocumentsList(Document obj, int startIndex, int offset, String searchParameter) throws Exception;




}
