package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.WebDocuments;

public interface WebDocumentsService {

	List<WebDocuments> getWebDocumentTypes(WebDocuments obj) throws Exception;
	
	List<WebDocuments> getWebDocuments(WebDocuments obj) throws Exception;

	boolean addWebDocument(WebDocuments obj) throws Exception;

	List<WebDocuments> getWebDocumentCategoriesList(WebDocuments obj) throws Exception;

	boolean updateWebDocument(WebDocuments obj) throws Exception;

	boolean deleteWebDocument(WebDocuments obj) throws Exception;

}
