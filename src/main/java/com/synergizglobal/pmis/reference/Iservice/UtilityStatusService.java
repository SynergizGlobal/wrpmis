package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;

public interface UtilityStatusService {

	public List<Safety> getUtilityStatusList() throws Exception;

	public boolean addUtilityStatus(Safety obj) throws Exception;
}
