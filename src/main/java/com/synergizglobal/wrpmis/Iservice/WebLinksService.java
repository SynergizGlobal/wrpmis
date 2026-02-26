package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.WebLinks;

public interface WebLinksService {

	List<WebLinks> getWebLinks(WebLinks obj) throws Exception;

	boolean updateWebLinks(WebLinks obj) throws Exception;

}
