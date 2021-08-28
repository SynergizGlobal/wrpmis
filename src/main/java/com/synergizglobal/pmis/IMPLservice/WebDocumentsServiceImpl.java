package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WebDocumentsDao;
import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.model.WebDocuments;

@Service
public class WebDocumentsServiceImpl implements WebDocumentsService{
	@Autowired
	WebDocumentsDao dao;
	
	@Override
	public List<WebDocuments> getWebDocumentTypes(WebDocuments obj) throws Exception {
		return dao.getWebDocumentTypes(obj);
	}
	
	@Override
	public List<WebDocuments> getWebDocuments(WebDocuments obj) throws Exception {
		return dao.getWebDocuments(obj);
	}

	@Override
	public boolean addWebDocument(WebDocuments obj) throws Exception {
		return dao.addWebDocument(obj);
	}

	@Override
	public List<WebDocuments> getWebDocumentCategoriesList(WebDocuments obj) throws Exception {
		return dao.getWebDocumentCategoriesList(obj);
	}

	@Override
	public boolean updateWebDocument(WebDocuments obj) throws Exception {
		return dao.updateWebDocument(obj);
	}

	@Override
	public boolean deleteWebDocument(WebDocuments obj) throws Exception {
		return dao.deleteWebDocument(obj);
	}

}
