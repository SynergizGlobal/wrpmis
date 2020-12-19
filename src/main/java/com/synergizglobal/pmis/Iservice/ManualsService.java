package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Manuals;

public interface ManualsService {

	public List<Manuals> getFoldersList() throws Exception;

	public Manuals getFoldersDataList(Manuals obj) throws Exception;



}
