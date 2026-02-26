package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityStatusDao {

	public List<Safety> getUtilityStatusList() throws Exception;
	public Safety getUtilityStatusList(Safety obj) throws Exception;

	public boolean addUtilityStatus(Safety obj) throws Exception;
	public boolean updateUtilityStatus(Safety obj) throws Exception;
	public boolean deleteUtilityStatus(Safety obj) throws Exception;
}
