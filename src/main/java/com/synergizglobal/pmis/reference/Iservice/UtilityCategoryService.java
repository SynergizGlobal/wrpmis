package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;

public interface UtilityCategoryService {

	public List<Safety> getUtilityCategorysList() throws Exception;

	public boolean addUtilityCategory(Safety obj) throws Exception;
}
