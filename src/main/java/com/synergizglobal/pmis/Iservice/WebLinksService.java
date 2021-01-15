package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.WebLinks;

public interface WebLinksService {

	List<WebLinks> getWebLinks(WebLinks obj) throws Exception;

	boolean updateWebLinks(WebLinks obj) throws Exception;

}
