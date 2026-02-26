package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityTypeDao {


	public List<Safety> getUtilityTypesList() throws Exception;
	public Safety getUtilityTypesList(Safety obj) throws Exception;

	public boolean addUtilityType(Safety obj) throws Exception;
	public boolean updateUtilityType(Safety obj) throws Exception;
	public boolean deleteUtilityType(Safety obj) throws Exception;
}

