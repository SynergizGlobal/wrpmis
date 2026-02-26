package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;

public interface UtilityAlignmentService {

	public List<Safety> getUtilityAlignmentsList() throws Exception;

	public boolean addUtilityAlignment(Safety obj) throws Exception;
}
