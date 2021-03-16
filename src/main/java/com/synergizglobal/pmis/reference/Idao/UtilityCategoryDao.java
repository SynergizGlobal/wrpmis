package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;

public interface UtilityCategoryDao {
	
	public List<Safety> getUtilityCategorysList() throws Exception;

	public boolean addUtilityCategory(Safety obj) throws Exception;
}
