package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;

public interface UtilityStatusDao {

	public List<Safety> getUtilityStatusList() throws Exception;

	public boolean addUtilityStatus(Safety obj) throws Exception;
}
