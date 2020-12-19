package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Manuals;

public interface ManualsDao {

	public List<Manuals> getFoldersList() throws Exception;

	public Manuals getFoldersDataList(Manuals obj) throws Exception;


}
