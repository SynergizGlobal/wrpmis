package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.WebLinksDao;
import com.synergizglobal.wrpmis.Iservice.WebLinksService;
import com.synergizglobal.wrpmis.model.WebLinks;

@Service
public class WebLinksServiceImpl implements WebLinksService{
	@Autowired
	WebLinksDao dao;
	
	@Override
	public List<WebLinks> getWebLinks(WebLinks obj) throws Exception {
		return dao.getWebLinks(obj);
	}

	@Override
	public boolean updateWebLinks(WebLinks obj) throws Exception {
		return dao.updateWebLinks(obj);
	}

}
