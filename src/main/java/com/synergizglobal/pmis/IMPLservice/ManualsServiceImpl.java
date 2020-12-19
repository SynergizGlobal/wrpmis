package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ManualsDao;
import com.synergizglobal.pmis.Iservice.ManualsService;
import com.synergizglobal.pmis.model.Manuals;

@Service
public class ManualsServiceImpl implements ManualsService {

	@Autowired
	ManualsDao dao;

	@Override
	public List<Manuals> getFoldersList() throws Exception {
		return dao.getFoldersList();
	}

	@Override
	public Manuals getFoldersDataList(Manuals obj) throws Exception {
		return dao.getFoldersDataList(obj);
	}


}
