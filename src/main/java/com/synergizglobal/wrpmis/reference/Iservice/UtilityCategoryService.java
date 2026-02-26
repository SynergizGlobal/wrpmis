package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityCategoryService {

	public List<Safety> getUtilityCategorysList() throws Exception;
	public Safety getUtilityCategorysList(Safety obj) throws Exception;

	public boolean addUtilityCategory(Safety obj) throws Exception;
	public boolean updateUtilityCategory(Safety obj) throws Exception;
	public boolean deleteUtilityCategory(Safety obj) throws Exception;
}
