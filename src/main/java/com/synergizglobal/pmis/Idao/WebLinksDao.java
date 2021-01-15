package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.WebLinks;

public interface WebLinksDao {
	
	List<WebLinks> getWebLinks(WebLinks obj) throws Exception;

	boolean updateWebLinks(WebLinks obj) throws Exception;
	
}
