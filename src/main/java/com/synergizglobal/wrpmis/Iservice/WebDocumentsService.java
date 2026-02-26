package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.WebDocuments;

public interface WebDocumentsService {

	List<WebDocuments> getWebDocumentTypes(WebDocuments obj) throws Exception;
	
	List<WebDocuments> getWebDocuments(WebDocuments obj) throws Exception;

	boolean addWebDocument(WebDocuments obj) throws Exception;

	List<WebDocuments> getWebDocumentCategoriesList(WebDocuments obj) throws Exception;

	boolean updateWebDocument(WebDocuments obj) throws Exception;

	boolean deleteWebDocument(WebDocuments obj) throws Exception;

}
