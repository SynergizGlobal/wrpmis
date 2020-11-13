package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SafetyEquipment;

public interface DocumentDao {

	public List<Document> documentList(Document obj) throws Exception;

	public List<Document> getDocumentContractsList(Document obj) throws Exception;

	public List<Document> getDocumentProjectPriorityList(Document obj) throws Exception;

	public List<Document> getDocumentTypesList(Document obj) throws Exception;

	public List<Document> getDocumentResponsibleForApprovalList(Document obj) throws Exception;

	public Document getDocument(Document obj)throws Exception;

	public List<Document> getStatusList()throws Exception;

	public List<Document> getDocumentTypeList() throws Exception;

	public List<Document> getPriorityListList() throws Exception;

	public List<Document> getUserList() throws Exception;

	public List<Document> getProjectsList() throws Exception;

	public List<Document> getContractList() throws Exception;



}
