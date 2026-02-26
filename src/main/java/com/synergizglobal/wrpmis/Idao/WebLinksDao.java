package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.WebLinks;

public interface WebLinksDao {
	
	List<WebLinks> getWebLinks(WebLinks obj) throws Exception;

	boolean updateWebLinks(WebLinks obj) throws Exception;
	
}
