package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.WebDocuments;

public interface WebDocumentsDao {

	List<WebDocuments> getWebDocumentTypes(WebDocuments obj) throws Exception;
	
	List<WebDocuments> getWebDocuments(WebDocuments obj) throws Exception;

	boolean addWebDocument(WebDocuments obj) throws Exception;

	List<WebDocuments> getWebDocumentCategoriesList(WebDocuments obj) throws Exception;

}
